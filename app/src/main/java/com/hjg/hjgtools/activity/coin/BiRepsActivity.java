package com.hjg.hjgtools.activity.coin;

import com.hjg.hjgtools.R;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class BiRepsActivity extends HJGBaseRecyclerMulItemActivity {
    /*平台*/
    public static final String PLATFORM_HUOBI = "火币";
    public static final String PLATFORM_BIAN = "币安";
    public static final String PLATFORM_OK = "OkEX";

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();

        listBeans.add(new RecyclerListBean("火币", BiCurrentHoldActivity.class, "", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("币安", BiCurrentHoldActivity.class, "", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("OkEX", BiCurrentHoldActivity.class, "", R.drawable.ic_icon_dialog));

        return listBeans;
    }
}