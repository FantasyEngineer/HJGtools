package com.hjg.hjgtools.activity.dialog;

import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.view.dialog.HorizontalLoadingDialog;
import com.hjg.base.view.dialog.LoadingDialog;
import com.hjg.base.view.dialog.SpecialHorizontalLoadingDialog;
import com.hjg.base.view.flyco.dialog.entity.DialogMenuItem;
import com.hjg.base.view.flyco.dialog.widget.ActionSheetDialog;
import com.hjg.base.view.flyco.dialog.widget.NormalDialog;
import com.hjg.base.view.flyco.dialog.widget.NormalListDialog;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class DialogActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        ArrayList<DialogMenuItem> dialogMenuItems = new ArrayList<>();
        dialogMenuItems.add(new DialogMenuItem("版本更新", R.drawable.ic_dialog));
        dialogMenuItems.add(new DialogMenuItem("帮助与反馈", R.drawable.ic_dialog));
        dialogMenuItems.add(new DialogMenuItem("退出", R.drawable.ic_dialog));
        switch (recyclerListBean.getTitle()) {
            case "NormalDialog默认双按钮":
                NormalDialog normalDialog = new NormalDialog(activity);
                normalDialog.titleTextColor(ResUtils.getColor(R.color.black));
                normalDialog.titleLineColor(ResUtils.getColor(R.color.black));
                normalDialog.setTitle("温馨提示");
                normalDialog.content("确认是否退出程序");
                normalDialog.show();
                break;
            case "NormalDialog默认单按钮":
                NormalDialog normalDialogSingle = new NormalDialog(activity);
                normalDialogSingle.titleTextColor(ResUtils.getColor(R.color.black));
                normalDialogSingle.titleLineColor(ResUtils.getColor(R.color.black));
                normalDialogSingle.setTitle("温馨提示");
                normalDialogSingle.content("确认是否退出程序");
                normalDialogSingle.btnNum(1);
                normalDialogSingle.btnText("确认");
                normalDialogSingle.show();
                break;
            case "NormalDialog默认三按钮":
                NormalDialog normalDialogThree = new NormalDialog(activity);
                normalDialogThree.titleTextColor(ResUtils.getColor(R.color.black));
                normalDialogThree.titleLineColor(ResUtils.getColor(R.color.black));
                normalDialogThree.setTitle("温馨提示");
                normalDialogThree.content("确认是否退出程序");
                normalDialogThree.btnNum(3);
                normalDialogThree.btnText("确认", "取消", "等等看");
                normalDialogThree.show();
                break;


            case "ActionSheetDialog":
                ActionSheetDialog ActionSheetDialog = new ActionSheetDialog(activity, dialogMenuItems, null);
                ActionSheetDialog.setTitle("选择群消息\n该群在电脑的设置，接受并提醒");
                ActionSheetDialog.itemTextColor(ResUtils.getColor(R.color.black));
                ActionSheetDialog.mCancelTextColor(ResUtils.getColor(R.color.black));
                ActionSheetDialog.show();
                break;
            case "NormalListDialog无title":
                NormalListDialog normalListDialog = new NormalListDialog(activity, dialogMenuItems);
                normalListDialog.isTitleShow(false);//是否展示头部
                normalListDialog.show();
                break;

            case "NormalListDialog有title":
                NormalListDialog normalListDialogWithoutTitle = new NormalListDialog(activity, dialogMenuItems);
                normalListDialogWithoutTitle.isTitleShow(true);//是否展示头部
                normalListDialogWithoutTitle.setTitle("这里是标题");
                normalListDialogWithoutTitle.show();

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
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalDialog默认双按钮"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalDialog默认单按钮"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalDialog默认三按钮"));

        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "中部列表形式"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalListDialog有title"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalListDialog无title"));

        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "底部按钮弹出"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "ActionSheetDialog"));


        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "等待层"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "LoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "HorizontalLoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "SpecialHorizontalLoadingDialog"));
        return listBeans;
    }
}