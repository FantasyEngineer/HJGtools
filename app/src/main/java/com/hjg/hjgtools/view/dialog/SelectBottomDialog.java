package com.hjg.hjgtools.view.dialog;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.listener.OnItemClickListener;
import com.hjg.base.util.D;
import com.hjg.base.util.ScreenUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.base.view.MyDividerItemDecoration;
import com.hjg.base.view.flyco.dialog.widget.base.BottomBaseDialog;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.view.roundview.RoundTextView;

import java.util.List;

/**
 * 底部单选和多选的弹出框
 */
public class SelectBottomDialog extends BottomBaseDialog<SelectBottomDialog> {
    private SpannableStringBuilder title = new TextSpanUtils.Builder("请选择").create();
    private int titleGravity = Gravity.LEFT;
    private int titleVisiable = View.VISIBLE;

    private List<String> options;

    private RecyclerView recyclerView;
    private TextView tvTitle;
    private BaseAdapter<String> optionsAdapter;

    public SelectBottomDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(getContext(), R.layout.dialog_select_bottom, null);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        tvTitle = inflate.findViewById(R.id.tvTitle);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        if (options == null) {
            throw new IllegalArgumentException("pls fill in some default Listdata！！");
        }
        tvTitle.setText(title);
        tvTitle.setVisibility(titleVisiable);
        tvTitle.setGravity(titleGravity);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(optionsAdapter = new BaseAdapter<String>(getContext(), R.layout.item_select, options) {

            @Override
            public void convert(BaseViewHolder holder, String s, int position) {
                RoundTextView rtvName = holder.getView(R.id.rtvName);
                rtvName.setText(s);
                holder.setOnClickListener(R.id.rtvName, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        D.showShort(s);
                        dismiss();
                    }
                });
            }
        });
    }


    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getTitleVisiable() {
        return titleVisiable;
    }

    public void setTitleVisiable(int titleVisiable) {
        this.titleVisiable = titleVisiable;
    }

    public int getTitleGravity() {
        return titleGravity;
    }

    public void setTitleGravity(int titleGravity) {
        this.titleGravity = titleGravity;
    }

    public void setTitle(SpannableStringBuilder title) {
        this.title = title;
    }

}
