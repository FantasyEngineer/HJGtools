package com.hjg.base.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.hjg.base.R;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.view.flyco.dialog.utils.CornerUtils;
import com.hjg.base.view.flyco.dialog.widget.internal.BaseAlertDialog;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 横向等待框
 */
public class HorizontalLoadingDialog extends BaseAlertDialog<HorizontalLoadingDialog> {

    private int textColor = Color.parseColor("#383838");
    private String tip = "加载中..";
    private int textSize = 15;//默认的字体的大小
    private long periodMilliseconds = 50;//周期进度的时间，单位是
    private ProgressBar progressBar;
    private int progressDrawable = android.R.drawable.progress_horizontal;


    public HorizontalLoadingDialog(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param periodMilliseconds 周期进度时间
     */
    public HorizontalLoadingDialog(Context context, long periodMilliseconds) {
        super(context);
        this.periodMilliseconds = periodMilliseconds;
    }

    /**
     * @param context
     * @param periodMilliseconds 周期进度时间
     * @param progressDrawable   进度条的样式
     */
    public HorizontalLoadingDialog(Context context, long periodMilliseconds, @DrawableRes int progressDrawable) {
        super(context);
        this.periodMilliseconds = periodMilliseconds;
        this.progressDrawable = progressDrawable;
    }

    @Override
    public View onCreateView() {

        //内部布局
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        float radius = dp2px(10);
        linearLayout.setBackgroundDrawable(CornerUtils.cornerDrawable(mBgColor, radius));//添加圆角
        linearLayout.setGravity(Gravity.CENTER);//居中
        linearLayout.setPadding(dp2px(25), dp2px(18), dp2px(25), dp2px(18));
        linearLayout.setOrientation(LinearLayout.VERTICAL);//纵向排列
        mLlContainer.addView(linearLayout);

        //圆形滚动条
        progressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp2px(60), dp2px(25));
        params.bottomMargin = dp2px(10);
        progressBar.setLayoutParams(params);
        progressBar.setProgressDrawable(ResUtils.getDrawable(progressDrawable));

        progressBar.setIndeterminate(false);//设置不确定，就变成了圆形
        linearLayout.addView(progressBar);

        //加载的提示语
        TextView tvTip = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvTip.setMaxWidth(dp2px(100));
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

    private int i = 0;
    private Timer timer;

    @Override
    public void setUiBeforShow() {

        //需要进度不听的前进，然后归零，轮训，达到一种加载的效果
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                progressBar.setProgress(i);
                i = i + 5;
                if (i >= 100) {
                    i = 0;
                }
            }
        }, 100, periodMilliseconds);

    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * 设置提示语
     *
     * @param tip
     * @return
     */
    public HorizontalLoadingDialog setTip(String tip) {
        this.tip = tip;
        return this;
    }

    /**
     * 设置字体大小
     *
     * @param textSize
     * @return
     */
    public HorizontalLoadingDialog setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    /**
     * 设置字体大小
     *
     * @param textColor
     * @return
     */
    public HorizontalLoadingDialog setTextColor(String textColor) {
        this.textColor = Color.parseColor(textColor);
        return this;
    }
}
