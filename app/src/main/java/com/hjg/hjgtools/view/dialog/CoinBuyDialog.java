package com.hjg.hjgtools.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hjg.base.util.D;
import com.hjg.base.util.DrawableUtils;
import com.hjg.base.util.SizeUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.TimeUtils;
import com.hjg.base.view.flyco.dialog.widget.base.BaseDialog;
import com.hjg.base.view.roundview.RoundEditText;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.coin.bean.CoinBean;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class CoinBuyDialog extends BaseDialog<CoinBuyDialog> {
    private OnCoinBuyListner onCoinBuyListner;
    private RoundEditText etCoinName, etCoinNum, etCoinPrice;
    private Button btnCancel, btnConfirm;
    public static final String NEW = "添加一个全新的币";
    public static final String ADD = "对一个币进行补仓";
    public static final String SUB = "对一个币进行减仓";
    public static final String ALL_CLEAR = "对一个币进行清仓";
    private TextView title;
    private String type = NEW;
    private CoinBean coinBean;

    public CoinBuyDialog(Context context, String type, CoinBean coinBean) {
        super(context);
        this.type = type;
        this.coinBean = coinBean;
    }

    public CoinBuyDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        View inflate = View.inflate(getContext(), R.layout.dialog_coin_buy, null);

        title = inflate.findViewById(R.id.title);
        etCoinName = inflate.findViewById(R.id.etCoinName);
        etCoinNum = inflate.findViewById(R.id.etCoinNum);
        etCoinPrice = inflate.findViewById(R.id.etCoinPrice);
        btnCancel = inflate.findViewById(R.id.btnCancel);
        btnConfirm = inflate.findViewById(R.id.btnConfirm);

        switch (type) {
            case NEW:
                title.setText("输入购买新币的信息");
                break;
            case ADD:
                title.setText("加仓" + coinBean.getCoinName());
                etCoinName.setText(coinBean.getCoinName());
                etCoinName.setEnabled(false);
                break;
            case SUB:
                title.setText("减仓" + coinBean.getCoinName());
                etCoinName.setText(coinBean.getCoinName());
                etCoinName.setEnabled(false);
                etCoinNum.setHint("减仓数量不能大于" + coinBean.getCoinNum());
                break;
            case ALL_CLEAR:
                title.setText("清仓" + coinBean.getCoinName());
                etCoinName.setText(coinBean.getCoinName());
                etCoinName.setEnabled(false);
                etCoinNum.setText(coinBean.getCoinNum());
                etCoinNum.setEnabled(false);
                break;

        }

        inflate.setBackgroundDrawable(
                DrawableUtils.cornerDrawable(Color.parseColor("#ffffff"), SizeUtils.dp2px(5)));

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        btnCancel.setOnClickListener(v -> {
            dismiss();
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onCoinBuyListner) {
                    String name = etCoinName.getText().toString();
                    String num = etCoinNum.getText().toString();


                    String price = etCoinPrice.getText().toString();
                    if (StrUtil.isNotEmpty(name)
                            && StrUtil.isNotEmpty(num)
                            && StrUtil.checkPointNum(num) < 2
                            && StrUtil.isNotEmpty(price)
                            && StrUtil.checkPointNum(price) < 2) {
                        /*准备售出的数量*/
                        BigDecimal newNum = new BigDecimal(num);
                        if (!judgeNumLegal(newNum)) {
                            D.showLong("售出数量大于总量");
                        } else {
                            dismiss();
                            //如果是减仓的话，这儿的数量应该是负数
                            if (type.equals(SUB) || type.equals(ALL_CLEAR)) {
                                newNum = new BigDecimal(0).subtract(newNum);
                            }
                            CoinBean coinBean = new CoinBean(name.toUpperCase(), newNum.toString(), price, TimeUtils.getNowTimeString());
                            /*得到当次我们的总金额*/
                            BigDecimal newPriceAver = new BigDecimal(price);
                            BigDecimal newPriceTotal = newNum.multiply(newPriceAver, new MathContext(10,
                                    RoundingMode.HALF_UP));
                            coinBean.setCoinPriceTotal(newPriceTotal.toString());
                            onCoinBuyListner.onBuy(coinBean);
                        }
                    } else {
                        D.showLong("非法字段");
                    }
                }

            }
        });
    }


    /**
     * 判断清仓或者减仓数量是否小于总数量
     *
     * @return
     */
    public boolean judgeNumLegal(BigDecimal newNum) {
        if (type.equals(SUB) || type.equals(ALL_CLEAR)) {
            if (newNum.doubleValue() > Double.parseDouble(coinBean.getCoinNum())) {
                return false;
            }
        }
        return true;
    }

    public void setOnCoinBuyListner(OnCoinBuyListner onCoinBuyListner) {
        this.onCoinBuyListner = onCoinBuyListner;
    }

    public interface OnCoinBuyListner {
        void onBuy(CoinBean coinBean);
    }
}
