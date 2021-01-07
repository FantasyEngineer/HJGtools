package com.hjg.hjgtools.activity.dialog;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.listener.OnItemClickListener;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.view.MyDividerItemDecoration;
import com.hjg.base.view.flyco.dialog.widget.NormalDialog;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityDialogBinding;

import java.util.ArrayList;

public class DialogActivity extends HJGDatabindingBaseActivity<ActivityDialogBinding> {

    private ArrayList dataList = ArrayListUtils.newArrayList("普通dialog", "NormalDialog");
    private BaseAdapter<String> dialogAdapter;

    @Override
    protected int getContentID() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void initViewAction() {
        databinding.recyclerView.setAdapter(dialogAdapter = new BaseAdapter<String>(this, R.layout.item_textview, dataList) {

            @Override
            public void convert(BaseViewHolder holder, String s, int position) {
                TextView textView = holder.getView(R.id.tvContent);
                textView.setText(s);
            }
        });

        databinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databinding.recyclerView.setHasFixedSize(false);
        databinding.recyclerView.addItemDecoration(new MyDividerItemDecoration());


        dialogAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    String title = textView.getText().toString();

                    switch (title) {
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

                    }

                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

    }
}