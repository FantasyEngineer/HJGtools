package com.hjg.hjgtools.activity.dialog;

import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.view.flyco.dialog.widget.NormalDialog;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class DialogActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
//        NormalDialog normalDialog = new NormalDialog(activity);
//        normalDialog.title("温馨提示");
//        normalDialog.titleTextColor(ResUtils.getColor(R.color.purple_500));
//        normalDialog.dividerColor(ResUtils.getColor(R.color.purple_500));
//        normalDialog.content("欢迎用户进入本系统");
//        normalDialog.btnNum(2);
//        normalDialog.btnText("我放弃了", "确认");
//        normalDialog.style(NormalDialog.STYLE_TWO);
//        normalDialog.show();
//        normalDialog.setOnBtnClickL(() -> {
//            D.showShort("点击了左边按钮");
//            normalDialog.dismiss();
//        }, () -> {
//            D.showShort("点击了右边按钮");
//            normalDialog.dismiss();
//        });
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "双单按钮实现"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalDialog"));


        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "等待层"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "LoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "HorizontalLoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "SpecialHorizontalLoadingDialog"));
        return listBeans;
    }
}