package com.hjg.hjgtools.activity.share;

import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class ShareActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("分享到短信"));
        recyclerListBeans.add(new RecyclerListBean("分享到邮箱"));
        recyclerListBeans.add(new RecyclerListBean("分享到QQ"));
        recyclerListBeans.add(new RecyclerListBean("分享到微信好友"));
        recyclerListBeans.add(new RecyclerListBean("分享到微信朋友圈"));
        recyclerListBeans.add(new RecyclerListBean("分享到微博"));
        return recyclerListBeans;
    }
}