package com.hjg.base.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.widget.ContentLoadingProgressBar;

import com.hjg.base.R;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.SizeUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.view.flyco.animation.BaseAnimatorSet;
import com.hjg.base.view.flyco.dialog.utils.CornerUtils;
import com.hjg.base.view.flyco.dialog.widget.base.BaseDialog;
import com.hjg.base.view.flyco.dialog.widget.internal.BaseAlertDialog;

/**
 * 等待框
 */
public class LoadingDialog extends BaseAlertDialog<LoadingDialog> {

    private int textColor = Color.parseColor("#383838");
    private String tip = "加载中..";
    private int textSize = 15;//默认的字体的大小


    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {

        //内部布局
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(dp2px(110), dp2px(110)));
        float radius = dp2px(10);
        linearLayout.setBackgroundDrawable(CornerUtils.cornerDrawable(mBgColor, radius));//添加圆角
        linearLayout.setGravity(Gravity.CENTER);//居中
        linearLayout.setOrientation(LinearLayout.VERTICAL);//纵向排列
        mLlContainer.addView(linearLayout);

        //圆形滚动条
        ProgressBar progressBar = new ProgressBar(mContext);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(dp2px(60), dp2px(60)));
        progressBar.setProgressDrawable(ResUtils.getDrawable(android.R.drawable.progress_horizontal));
        progressBar.setIndeterminate(false);//设置不确定，就变成了圆形
        linearLayout.addView(progressBar);

        //加载的提示语
        TextView tvTip = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = dp2px(3);
        tvTip.setLayoutParams(layoutParams);
        tvTip.setText(tip);
        tvTip.setVisibility(StrUtil.isEmpty(tip) ? View.GONE : View.VISIBLE);
        tvTip.setTextSize(textSize);//设置字体大小
        tvTip.setTextColor(textColor);//设置颜色
        linearLayout.addView(tvTip);

        mLlContainer.setGravity(Gravity.CENTER);

        return mLlContainer;
    }

    @Override
    public void setUiBeforShow() {

    }

    /**
     * 设置提示语
     *
     * @param tip
     * @return
     */
    public LoadingDialog setTip(String tip) {
        this.tip = tip;
        return this;
    }

    /**
     * 设置字体大小
     *
     * @param textSize
     * @return
     */
    public LoadingDialog setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    /**
     * 设置字体大小
     *
     * @param textColor
     * @return
     */
    public LoadingDialog setTextColor(String textColor) {
        this.textColor = Color.parseColor(textColor);
        return this;
    }
}
