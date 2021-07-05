package com.hjg.hjgtools.activity.coin;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.coin.bean.CoinBeanHistory;
import com.hjg.hjgtools.databinding.ActivityCoinRecordBinding;
import com.hjg.hjgtools.view.dialog.CoinBuyDialog;
import com.hjg.hjgtools.view.roundview.RoundTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*单个币的记录*/
public class CoinRecordActivity extends HJGDatabindingBaseActivity<ActivityCoinRecordBinding> {

    private String platFormflag;/*平台*/
    private String coinName;/*哪个币的交易记录*/
    private BaseQuickAdapter<CoinBeanHistory, BaseViewHolder> biHistoryAdapter;

    @Override
    protected int getContentID() {
        return R.layout.activity_coin_record;
    }

    @Override
    protected void initViewAction() {
        databinding.recyclerView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
        databinding.recyclerView.setAdapter(biHistoryAdapter = new BaseQuickAdapter<CoinBeanHistory, BaseViewHolder>(R.layout.item_current_coin) {
            @Override
            protected void convert(@NonNull com.chad.library.adapter.base.BaseViewHolder helper, CoinBeanHistory item) {

                helper.setText(R.id.coinName, new TextSpanUtils.Builder("币名称：").setBold().append(item.getCoinName()).create());
                helper.setText(R.id.coinButLastTime, new TextSpanUtils.Builder("操作时间：").setBold().append(item.getCoinBuyLastTime()).create());


                RoundTextView roundTextView = helper.getView(R.id.type);
                if (StrUtil.isNotEmpty(item.getOperation())) {
                    String operation = "";
                    switch (item.getOperation()) {
                        case CoinBuyDialog.NEW:
                            showByOperation(helper, item, roundTextView, R.color.green, R.id.coinPriceTotal, "购买花费总U数(约)：", item.getCoinPriceTotal() + "", R.id.coinPrice, "购入单价：", item.getCoinPriceAverage(), "购买数量：");
                            operation = "建仓";
                            break;
                        case CoinBuyDialog.ADD:
                            showByOperation(helper, item, roundTextView, R.color.green, R.id.coinPriceTotal, "购买花费总U数(约)：", item.getCoinPriceTotal() + "", R.id.coinPrice, "购入单价：", item.getCoinPriceAverage(), "购买数量：");
                            operation = "加仓";
                            break;
                        case CoinBuyDialog.SUB:
                            showByOperation(helper, item, roundTextView, R.color.red, R.id.coinPrice, "售出单价：", item.getCoinPriceAverage(), R.id.coinPriceTotal, "卖出总u数(约)：", item.getCoinPriceTotal() + "", "售出数量：");
                            operation = "减仓";
                            break;
                        case CoinBuyDialog.ALL_CLEAR:
                            showByOperation(helper, item, roundTextView, R.color.red, R.id.coinPriceTotal, "卖出总u数(约)：", item.getCoinPriceTotal() + "", R.id.coinPrice, "售出单价：", item.getCoinPriceAverage(), "售出数量：");
                            operation = "清仓";
                            break;
                    }
                    roundTextView.setText(operation);
                    roundTextView.setVisibility(View.VISIBLE);
                }

            }

            /**
             * 根据操作展示数据
             * @param helper
             * @param item
             * @param roundTextView
             * @param p
             * @param p2
             * @param s
             * @param s2
             * @param p3
             * @param s3
             * @param coinPriceAverage
             * @param s4
             */
            private void showByOperation(@NonNull BaseViewHolder helper, CoinBeanHistory item, RoundTextView roundTextView, int p, int p2, String s, String s2, int p3, String s3, String coinPriceAverage, String s4) {
                roundTextView.getDelegate().setBackgroundColor(ResUtils.getColor(p));
                helper.setText(p2, new TextSpanUtils.Builder(s).setBold().append(s2).create());
                helper.setText(p3, new TextSpanUtils.Builder(s3).setBold().append(coinPriceAverage).create());
                helper.setText(R.id.coinNum, new TextSpanUtils.Builder(s4).setBold().append(item.getCoinNum()).create());
            }
        });

        /*监听点击事件*/
//        biHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                showFunction(biAdapter.getData().get(position));
//            }
//        });

        Bundle bundle = getIntent().getBundleExtra("bundle");
        platFormflag = bundle.getString("platform");
        coinName = bundle.getString("coinName");


        List<CoinBeanHistory> coinBeanHistorys = searchBi();
        //说明是查询整个火币网的交易记录
        if (StrUtil.isEmpty(coinName)) {
            biHistoryAdapter.setNewData(coinBeanHistorys);
        } else {//说明是查询单个币在火币网的交易记录
            /*获取全部数据*/
            /*开始筛选当前的币的数据*/
            List<CoinBeanHistory> curentCoinList = new ArrayList<>();
            for (int i = 0; i < coinBeanHistorys.size(); i++) {
                if (coinName.equals(coinBeanHistorys.get(i).getCoinName())) {
                    curentCoinList.add(coinBeanHistorys.get(i));
                }
            }
            /*展示数据*/
            biHistoryAdapter.setNewData(curentCoinList);
        }
    }

    /*查找所有数据*/
    public List<CoinBeanHistory> searchBi() {
        List<CoinBeanHistory> queryAll = LiteOrmDBUtil.getQueryByWhere(CoinBeanHistory.class, "platForm", new String[]{platFormflag});
        Collections.reverse(queryAll);
        return queryAll;
    }

}