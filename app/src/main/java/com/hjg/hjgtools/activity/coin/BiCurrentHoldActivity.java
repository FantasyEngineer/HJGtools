package com.hjg.hjgtools.activity.coin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.EmptyUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.base.view.dialog.LoadingDialog;
import com.hjg.base.view.flyco.dialog.listener.OnBtnClickL;
import com.hjg.base.view.flyco.dialog.listener.OnOperItemClickL;
import com.hjg.base.view.flyco.dialog.widget.MaterialDialog;
import com.hjg.base.view.flyco.dialog.widget.NormalListDialog;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.coin.bean.CoinBean;
import com.hjg.hjgtools.activity.coin.bean.CoinBeanHistory;
import com.hjg.hjgtools.databinding.ActivityBiCurrentHoldBinding;
import com.hjg.hjgtools.view.dialog.CoinBuyDialog;
import com.hjg.hjgtools.view.roundview.RoundTextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/*当前持仓数量的数量*/
public class BiCurrentHoldActivity extends HJGDatabindingBaseActivity<ActivityBiCurrentHoldBinding> implements View.OnClickListener {

    private BaseQuickAdapter<CoinBean, BaseViewHolder> biAdapter;
    private LoadingDialog loadingDialog;
    private String platFormflag;

    @Override
    protected int getContentID() {
        return R.layout.activity_bi_current_hold;
    }

    @Override
    protected boolean isShowActionBar() {
        return false;
    }

    @Override
    protected void initViewAction() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        platFormflag = bundle.getString(TITLE);

        /*titlebar*/
        setSupportActionBar(databinding.toolBar);
        databinding.left.setOnClickListener(this);
        databinding.right.setOnClickListener(this);
        databinding.title.setText(platFormflag + "当前持仓");
        databinding.title.setOnClickListener(this);


        loadingDialog = new LoadingDialog(this);

        databinding.recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
        databinding.recyclerView.setAdapter(biAdapter = new BaseQuickAdapter<CoinBean, BaseViewHolder>(R.layout.item_current_coin, searchBi()) {
            @Override
            protected void convert(@NonNull com.chad.library.adapter.base.BaseViewHolder helper, CoinBean item) {
                helper.setText(R.id.coinName, new TextSpanUtils.Builder("币名称：").setBold().append(item.getCoinName()).create());
                helper.setText(R.id.coinNum, new TextSpanUtils.Builder("持有数量：").setBold().append(item.getCoinNum()).create());
                helper.setText(R.id.coinPrice, new TextSpanUtils.Builder("当前均价：").setBold().append(item.getCoinPriceAverage()).create());
                helper.setText(R.id.coinPriceTotal, new TextSpanUtils.Builder("占用的总U数(约)：").setBold().append(item.getCoinPriceTotal() + "").create());
                helper.setText(R.id.coinButLastTime, new TextSpanUtils.Builder("最后操作时间：").setBold().append(item.getCoinBuyLastTime()).create());
            }
        });

        /*监听点击事件*/
        biAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showFunction(biAdapter.getData().get(position));
            }
        });
    }

    /*展示功能区*/
    private void showFunction(CoinBean coinBean) {
        String[] function = new String[]{"补仓", "减仓", "清仓", "查看记录", "删除本次记录"};
        NormalListDialog normalListDialog = new NormalListDialog(activity, function);
        normalListDialog.layoutAnimation(null);
        normalListDialog.show();
        normalListDialog.setItemExtraPadding(0, 10, 0, 10);
        normalListDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                normalListDialog.dismiss();
                switch (function[position]) {
                    case "补仓":
                        buyNewCoin(CoinBuyDialog.ADD, coinBean);
                        break;
                    case "减仓":
                        buyNewCoin(CoinBuyDialog.SUB, coinBean);
                        break;
                    case "清仓":
                        buyNewCoin(CoinBuyDialog.ALL_CLEAR, coinBean);
                        break;
                    case "查看记录":
                        Bundle bundle = new Bundle();
                        bundle.putString("title", coinBean.getCoinName() + "交易记录");
                        bundle.putString("coinName", coinBean.getCoinName());
                        bundle.putString("platform", platFormflag);
                        ActivityUtils.startActivity(CoinRecordActivity.class, bundle);
                        break;
                    case "删除本次记录":
                        MaterialDialog materialDialog = new MaterialDialog(activity);
                        materialDialog.titleTextColor(ResUtils.getColor(R.color.black));
                        materialDialog.setTitle("警告");
                        materialDialog.content("是否确认无痕清除该条记录？");
                        materialDialog.show();
                        materialDialog.setOnBtnClickL(new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                materialDialog.dismiss();
                            }
                        }, new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                materialDialog.dismiss();
                                deleteCoin(coinBean.getId());
                                notifyChangeAll();
                            }
                        });
                        break;
                }

            }
        });
    }

    /**
     * 更新全部数据
     */
    private void notifyChangeAll() {
        runOnUiThread(() -> biAdapter.setNewData(searchBi()));
    }

    /*购买币*/
    public void bugCoin(View view) {
        buyNewCoin(CoinBuyDialog.NEW, null);
    }

    private void buyNewCoin(String type, CoinBean coinBean1) {
        CoinBuyDialog coinBuyDialog = new CoinBuyDialog(activity, type, coinBean1);
        coinBuyDialog.show();
        coinBuyDialog.setOnCoinBuyListner(coinBean -> runOnUiThread(new Runnable() {
            @Override
            public void run() {
                coinBean.setPlatForm(platFormflag);
                insert(coinBean, type);
                notifyChangeAll();
            }
        }));
    }


    /**
     * 加仓，购买，减仓，清仓操作
     *
     * @param coinBean
     * @param type1
     */
    public void insert(CoinBean coinBean, String type1) {
        String[] type = new String[]{type1};
        loadingDialog.show();
        Observable.just(coinBean).doOnNext(new Consumer<CoinBean>() {
            @Override
            public void accept(CoinBean coinBean) throws Exception {
                /*每次的操作过来之后，需要先保存一份到历史数据中*/
                //这里的售出的数量和价格是负数，需要处理一下，才能保存到数据库中。、
                CoinBeanHistory coinBeanHistory = new CoinBeanHistory(coinBean.getPlatForm(), coinBean.getCoinName()
                        , coinBean.getCoinNum().replace("-", ""), type1, coinBean.getCoinPriceAverage(), coinBean.getCoinPriceTotal().replace("-", ""), coinBean.getCoinBuyLastTime());
                LiteOrmDBUtil.insert(coinBeanHistory);
            }
        }).map(coinBean1 -> {
            /*获取所有的数据*/
            List<CoinBean> coinBeans = searchBi();
            if (EmptyUtils.isNotEmpty(coinBeans)) {
                for (int i = 0; i < coinBeans.size(); i++) {
                    //判定是否相等，有相同的币，需要计算均价
                    CoinBean dnCoinBean = coinBeans.get(i);
                    if (dnCoinBean.getCoinName().trim().toUpperCase().equals(coinBean1.getCoinName().trim().toString().toUpperCase())) {
                        /*数据库中币总数*/
                        BigDecimal dbNum = new BigDecimal(dnCoinBean.getCoinNum());
                        /*币平均价格*/
                        BigDecimal dbpriceAver = new BigDecimal(dnCoinBean.getCoinPriceAverage());
                        /*数据库中总价*/
                        BigDecimal dbPriceTotal = new BigDecimal(dnCoinBean.getCoinPriceTotal());
                        L.d("数量：" + dbNum + "===============均价：" + dbpriceAver + "==============总价：" + dbPriceTotal);

                        /*新购买的*/
                        BigDecimal newNum = new BigDecimal(coinBean1.getCoinNum());
                        BigDecimal newPriceAver = new BigDecimal(coinBean1.getCoinPriceAverage());
                        BigDecimal newPriceTotal = new BigDecimal(coinBean1.getCoinPriceTotal());
                        L.d("新数量：" + newNum + "===============均价：" + newPriceAver + "==============总价：" + newPriceTotal);

                        /*该币总花费u数*/
                        BigDecimal priceTotal = dbPriceTotal.add(newPriceTotal);
                        /*该币购买的总数量*/
                        BigDecimal num = dbNum.add(newNum);

                        /*说明是减仓操作*/
                        if (type[0].equals(CoinBuyDialog.SUB)) {
                            if (num.toString().equals("0")) {//减了全仓，也就是变成了清仓操作*/
                                type[0] = CoinBuyDialog.ALL_CLEAR;
                            } else {
                                D.showLong("减仓操作");

                            }
                        }


                        /*说明是清空操作，计算均价时，除数是0*/
                        if (type[0].equals(CoinBuyDialog.ALL_CLEAR)) {//这里需要计算盈利了，加到盈利的数据库中
                            D.showLong("清仓");
                            deleteCoin(dnCoinBean.getId());//删除当前
                            return dnCoinBean;
                        }




                        /*该币的均价是*/
                        BigDecimal priceAver = priceTotal.divide(num, 10, BigDecimal.ROUND_HALF_UP);
                        L.d("计算的新数量：" + num + "===============均价：" + priceAver + "==============总价：" + priceTotal);


                        /*更改总数*/
                        dnCoinBean.setCoinNum(num.toString());
                        /*更改新的平均价*/
                        dnCoinBean.setCoinPriceAverage(priceAver.toString());
                        dnCoinBean.setCoinPriceTotal(priceTotal.toString());
                        /*添加最后一次的时间*/
                        dnCoinBean.setCoinBuyLastTime(coinBean1.getCoinBuyLastTime());
                        //更新
                        LiteOrmDBUtil.update(dnCoinBean);
                        return dnCoinBean;
                    }
                }
            }
            //添加
            LiteOrmDBUtil.insert(coinBean1);

            return coinBean1;
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CoinBean>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull CoinBean coinBean) {
                //更新
                notifyChangeAll();
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                D.showLong(e.getMessage());
                loadingDialog.dismiss();

            }

            @Override
            public void onComplete() {
                loadingDialog.dismiss();

            }
        });
    }


    /*查找所有数据*/
    public List<CoinBean> searchBi() {
        List<CoinBean> queryAll = LiteOrmDBUtil.getQueryByWhere(CoinBean.class, "platForm", new String[]{platFormflag});
        L.d(queryAll);
        Collections.reverse(queryAll);
        return queryAll;
    }

    /**
     * 根据id删除数据
     *
     * @param id
     */
    public void deleteCoin(int id) {
        LiteOrmDBUtil.deleteWhere(CoinBean.class, "id", new Object[]{id});
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left://返回删除
                finish();
                break;
            case R.id.right://查看历史记录
                Bundle bundle = new Bundle();
                bundle.putString("title", platFormflag + "交易记录");
                bundle.putString("platform", platFormflag);
                ActivityUtils.startActivity(CoinRecordActivity.class, bundle);
                break;
            case R.id.title://查看账户信息
                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@io.reactivex.annotations.NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> options = new ArrayList<>();
                        options.add(platFormflag + "账户总估值");
                        Double total = 0d;
                        List<CoinBean> coinBeans = searchBi();
                        for (CoinBean coinBean : coinBeans) {
                            double v1 = Double.parseDouble(coinBean.getCoinPriceTotal());
                            total = total + v1;
                        }

                        options.add(total.toString());

                        emitter.onNext(options);
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull List<String> strings) {
                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity, R.style.BottomSheetDialog);//这个样式可以保证背景透明
                        View inflate = getLayoutInflater().inflate(R.layout.dialog_select_bottom, null, false);
                        RecyclerView recyclerViewBottom = inflate.findViewById(R.id.recyclerView);
                        recyclerViewBottom.setLayoutManager(new LinearLayoutManager(activity));
                        recyclerViewBottom.setAdapter(new BaseAdapter<String>(activity, R.layout.item_select, strings) {
                            @Override
                            public void convert(com.hjg.base.adapter.BaseViewHolder holder, String s, int position) {
                                RoundTextView rtvName = holder.getView(R.id.rtvName);
                                rtvName.setText(s);
                                holder.setOnClickListener(R.id.rtvName, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        D.showShort(s);
                                    }
                                });
                            }
                        });
                        bottomSheetDialog.setContentView(inflate);
                        bottomSheetDialog.show();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });


                break;
        }
    }
}