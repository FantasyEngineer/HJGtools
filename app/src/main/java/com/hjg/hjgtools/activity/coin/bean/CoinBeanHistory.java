package com.hjg.hjgtools.activity.coin.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import static com.hjg.hjgtools.activity.coin.BiRepsActivity.PLATFORM_HUOBI;

/*详细操作目录*/
public class CoinBeanHistory {
    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String platForm = PLATFORM_HUOBI;
    private String coinName;/*当前币名称*/
    private String coinNum;/*操作币数量*/
    private String operation;//操作，加仓或者减仓
    private String coinPriceAverage;/*币单价*/
    private String coinPriceTotal;//单次币总价
    private String coinBuyLastTime;/*币最后购买时间*/


    public CoinBeanHistory(String platForm, String coinName, String coinNum, String operation, String coinPriceAverage, String coinPriceTotal, String coinBuyLastTime) {
        this.platForm = platForm;
        this.coinName = coinName;
        this.coinNum = coinNum;
        this.operation = operation;
        this.coinPriceAverage = coinPriceAverage;
        this.coinPriceTotal = coinPriceTotal;
        this.coinBuyLastTime = coinBuyLastTime;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(String coinNum) {
        this.coinNum = coinNum;
    }

    public String getCoinPriceAverage() {
        return coinPriceAverage;
    }

    public void setCoinPriceAverage(String coinPriceAverage) {
        this.coinPriceAverage = coinPriceAverage;
    }

    public String getCoinBuyLastTime() {
        return coinBuyLastTime;
    }

    public void setCoinBuyLastTime(String coinBuyLastTime) {
        this.coinBuyLastTime = coinBuyLastTime;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public String getCoinPriceTotal() {
        return coinPriceTotal;
    }

    public void setCoinPriceTotal(String coinPriceTotal) {
        this.coinPriceTotal = coinPriceTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
