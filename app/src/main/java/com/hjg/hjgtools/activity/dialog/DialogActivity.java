package com.hjg.hjgtools.activity.dialog;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.base.view.HorizontalLoadingDialog;
import com.hjg.base.view.LoadingDialog;
import com.hjg.base.view.SpecialHorizontalLoadingDialog;
import com.hjg.base.view.flyco.dialog.widget.NormalDialog;
import com.hjg.hjgtools.MainActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class DialogActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        switch (recyclerListBean.getTitle()) {
            case "NormalDialog":
                NormalDialog normalDialog = new NormalDialog(activity);
                normalDialog.title("温馨提示");
                normalDialog.titleTextColor(ResUtils.getColor(R.color.purple_500));
                normalDialog.dividerColor(ResUtils.getColor(R.color.purple_500));
                normalDialog.content("欢迎用户进入本系统");
                normalDialog.btnNum(2);
                normalDialog.btnText("我放弃了", "确认");
                normalDialog.style(NormalDialog.STYLE_TWO);
                normalDialog.show();
                normalDialog.setOnBtnClickL(() -> {
                    D.showShort("点击了左边按钮");
                    normalDialog.dismiss();
                }, () -> {
                    D.showShort("点击了右边按钮");
                    normalDialog.dismiss();
                });
                break;
            case "LoadingDialog":
                LoadingDialog loadingDialog = new LoadingDialog(activity);
                loadingDialog.show();
                break;
            case "HorizontalLoadingDialog":
                HorizontalLoadingDialog horizontalLoadingDialog = new HorizontalLoadingDialog(activity);
                horizontalLoadingDialog.show();
                break;
            case "SpecialHorizontalLoadingDialog":
                SpecialHorizontalLoadingDialog specialHorizontalLoadingDialog = new SpecialHorizontalLoadingDialog(activity, R.drawable.progress_horizon_special);
                specialHorizontalLoadingDialog.show();
                break;
        }
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "双单按钮实现"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalDialog"));

        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "底部按钮弹出"));


        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "等待层"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "LoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "HorizontalLoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "SpecialHorizontalLoadingDialog"));
        return listBeans;
    }
}