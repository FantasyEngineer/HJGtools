package com.hjg.hjgtools.activity.dialog;

import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.view.dialog.HorizontalLoadingDialog;
import com.hjg.base.view.dialog.LoadingDialog;
import com.hjg.base.view.dialog.SpecialHorizontalLoadingDialog;
import com.hjg.base.view.flyco.dialog.entity.DialogMenuItem;
import com.hjg.base.view.flyco.dialog.widget.ActionSheetDialog;
import com.hjg.base.view.flyco.dialog.widget.NormalDialog;
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


            case "ActionSheetDialog":
                ArrayList<DialogMenuItem> dialogMenuItems = new ArrayList<>();
                dialogMenuItems.add(new DialogMenuItem("版本更新", 0));
                dialogMenuItems.add(new DialogMenuItem("帮助与反馈", 0));
                dialogMenuItems.add(new DialogMenuItem("退出", 0));
                ActionSheetDialog ActionSheetDialog = new ActionSheetDialog(activity, dialogMenuItems, null);
                ActionSheetDialog.setTitle("选择群消息\n该群在电脑的设置，接受并提醒");
                ActionSheetDialog.itemTextColor(ResUtils.getColor(R.color.black));
                ActionSheetDialog.mCancelTextColor(ResUtils.getColor(R.color.black));
                ActionSheetDialog.show();
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

        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "ActionSheetDialog"));


        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "等待层"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "LoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "HorizontalLoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "SpecialHorizontalLoadingDialog"));
        return listBeans;
    }
}