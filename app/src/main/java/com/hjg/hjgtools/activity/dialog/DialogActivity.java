package com.hjg.hjgtools.activity.dialog;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.view.dialog.CustomBaseDialog;
import com.hjg.base.view.dialog.HorizontalLoadingDialog;
import com.hjg.base.view.dialog.IOSTaoBaoDialog;
import com.hjg.base.view.dialog.LoadingDialog;
import com.hjg.base.view.dialog.ShareBottomDialog;
import com.hjg.base.view.dialog.ShareTopDialog;
import com.hjg.base.view.dialog.SpecialHorizontalLoadingDialog;
import com.hjg.base.view.flyco.dialog.entity.DialogMenuItem;
import com.hjg.base.view.flyco.dialog.widget.ActionSheetDialog;
import com.hjg.base.view.flyco.dialog.widget.MaterialDialog;
import com.hjg.base.view.flyco.dialog.widget.NormalDialog;
import com.hjg.base.view.flyco.dialog.widget.NormalListDialog;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;
import com.hjg.hjgtools.view.dialog.SelectBottomDialog;
import com.hjg.hjgtools.view.roundview.RoundTextView;

import java.util.ArrayList;

public class DialogActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        ArrayList<DialogMenuItem> dialogMenuItems = new ArrayList<>();
        dialogMenuItems.add(new DialogMenuItem("版本更新", R.drawable.ic_dialog));
        dialogMenuItems.add(new DialogMenuItem("帮助与反馈", R.drawable.ic_dialog));
        dialogMenuItems.add(new DialogMenuItem("退出", R.drawable.ic_dialog));

        ArrayList options = ArrayListUtils.newArrayList("选择1", "选择2", "选择3", "选择4", "选择2", "选择3", "选择4", "选择2", "选择3", "选择4", "选择2", "选择3", "选择4", "选择2", "选择3", "选择4", "选择2", "选择3", "选择4", "选择2", "选择3", "选择4");

        switch (recyclerListBean.getTitle()) {
            case "NormalDialog默认双按钮居中":
                NormalDialog normalDialog = new NormalDialog(activity);
                normalDialog.style(NormalDialog.STYLE_TWO);
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


            case "MaterialDialog默认":
                MaterialDialog materialDialog = new MaterialDialog(activity);
                materialDialog.titleTextColor(ResUtils.getColor(R.color.black));
                materialDialog.setTitle("温馨提示");
                materialDialog.content("确认是否退出程序");
                materialDialog.show();
                break;
            case "MaterialDialog单按钮":
                MaterialDialog materialDialogSingle = new MaterialDialog(activity);
                materialDialogSingle.titleTextColor(ResUtils.getColor(R.color.black));
                materialDialogSingle.setTitle("温馨提示");
                materialDialogSingle.btnNum(1);
                materialDialogSingle.content("确认是否退出程序");
                materialDialogSingle.show();
                break;
            case "MaterialDialog三按钮":
                MaterialDialog materialDialogStyle = new MaterialDialog(activity);
                materialDialogStyle.titleTextColor(ResUtils.getColor(R.color.black));
                materialDialogStyle.setTitle("温馨提示");
                materialDialogStyle.btnNum(3);
                materialDialogStyle.content("确认是否退出程序");
                materialDialogStyle.show();
                break;


            case "ActionSheetDialog":
                ActionSheetDialog ActionSheetDialog = new ActionSheetDialog(activity, dialogMenuItems, null);
                ActionSheetDialog.setTitle("选择群消息\n该群在电脑的设置，接受并提醒");
                ActionSheetDialog.itemTextColor(ResUtils.getColor(R.color.black));
                ActionSheetDialog.mCancelTextColor(ResUtils.getColor(R.color.black));
                ActionSheetDialog.show();
                break;

            case "ActionSheetDialogNOTitle":
                ActionSheetDialog ActionSheetDialogNOTitle = new ActionSheetDialog(activity, dialogMenuItems, null);
                ActionSheetDialogNOTitle.isTitleShow(false);
                ActionSheetDialogNOTitle.itemTextColor(ResUtils.getColor(R.color.black));
                ActionSheetDialogNOTitle.mCancelTextColor(ResUtils.getColor(R.color.black));
                ActionSheetDialogNOTitle.show();
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


            case "CustomBaseDialog":
                CustomBaseDialog customBaseDialog = new CustomBaseDialog(activity);
                customBaseDialog.show();
                break;
            case "IOSTaoBaoDialog":
                IOSTaoBaoDialog iosTaoBaoDialog = new IOSTaoBaoDialog(activity);
                iosTaoBaoDialog.show();
                break;
            case "ShareBottomDialog":
                ShareBottomDialog shareBottomDialog = new ShareBottomDialog(activity);
                shareBottomDialog.show();
                break;
            case "ShareTopDialog":
                ShareTopDialog shareTopDialog = new ShareTopDialog(activity);
                shareTopDialog.show();
                break;

            case "SelectBottomDialog":
                SelectBottomDialog selectBottomDialog = new SelectBottomDialog(activity);
                selectBottomDialog.setOptions(options);
                selectBottomDialog.show();
                break;

            case "系统的BottomSheetDialog可以下拉消失":
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity, R.style.BottomSheetDialog);//这个样式可以保证背景透明
                View inflate = getLayoutInflater().inflate(R.layout.dialog_select_bottom, null, false);
                RecyclerView recyclerViewBottom = inflate.findViewById(R.id.recyclerView);
                recyclerViewBottom.setLayoutManager(new LinearLayoutManager(activity));
                recyclerViewBottom.setAdapter(new BaseAdapter<String>(activity, R.layout.item_select, options) {

                    @Override
                    public void convert(BaseViewHolder holder, String s, int position) {
                        RoundTextView rtvName = holder.getView(R.id.rtvName);
                        rtvName.setText(s);
                        holder.setOnClickListener(R.id.rtvName, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                D.showShort(s);
                            }
                        });
                    }
                });

                bottomSheetDialog.setContentView(inflate);
                //这里的设置是滑动到了哪里，可以dismiss窗口
                BottomSheetBehavior<View> mDialogBehavior = BottomSheetBehavior.from((View) inflate.getParent());
                mDialogBehavior.setPeekHeight(getWindowHeight());

                bottomSheetDialog.show();

                break;

            case "自定义SpringBackBottomSheetDialog":


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
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalDialog默认双按钮居中"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalDialog默认单按钮"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalDialog默认三按钮"));

        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "仿系统样式弹窗"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "MaterialDialog默认"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "MaterialDialog单按钮"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "MaterialDialog三按钮"));


        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "中部列表形式"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalListDialog有title"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "NormalListDialog无title"));

        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "底部按钮弹出"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "ActionSheetDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "ActionSheetDialogNOTitle"));

        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "自定义弹窗"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "CustomBaseDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "IOSTaoBaoDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "ShareBottomDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "ShareTopDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "SelectBottomDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "系统的BottomSheetDialog可以下拉消失"));


        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "自定义等待层"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "LoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "HorizontalLoadingDialog"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "SpecialHorizontalLoadingDialog"));
        return listBeans;
    }


    /**
     * 弹窗高度，默认为屏幕高度的四分之三
     * 子类可重写该方法返回peekHeight
     *
     * @return height
     */
    protected int getPeekHeight() {
        int peekHeight = getResources().getDisplayMetrics().heightPixels;
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight - peekHeight / 3;
    }

    private int getWindowHeight() {
        Resources res = getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

}