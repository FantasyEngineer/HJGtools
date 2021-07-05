package com.hjg.hjgtools.activity.share;

import android.content.Intent;

import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class ShareActivity extends HJGBaseRecyclerMulItemActivity {


    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        switch (recyclerListBean.getTitle()) {
            case "文本分享到短信":
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
                startActivity(Intent.createChooser(textIntent, "分享"));
                break;
            case "分享到邮箱":
                break;
//            case "分享到短信":
//                break;
//            case "分享到短信":
//                break;
//            case "分享到短信":
//                break;
//            case "分享到短信":
//                break;
        }
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("文本分享到短信"));
        recyclerListBeans.add(new RecyclerListBean("分享到邮箱"));
        recyclerListBeans.add(new RecyclerListBean("分享到QQ"));
        recyclerListBeans.add(new RecyclerListBean("分享到微信好友"));
        recyclerListBeans.add(new RecyclerListBean("分享到微信朋友圈"));
        recyclerListBeans.add(new RecyclerListBean("分享到微博"));
        return recyclerListBeans;
    }
}