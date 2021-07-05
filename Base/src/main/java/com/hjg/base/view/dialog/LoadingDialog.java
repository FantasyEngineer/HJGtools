package com.hjg.base.view.dialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hjg.base.R;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.view.flyco.dialog.utils.CornerUtils;
import com.hjg.base.view.flyco.dialog.widget.internal.BaseAlertDialog;

/**
 * 等待框
 * 使用方案如下：添加动画等等
 * LoadingDialog loadingDialog = new LoadingDialog(this);
 * loadingDialog.showAnim(new FadeEnter());
 * loadingDialog.dismissAnim(new FadeExit());
 * loadingDialog.show();
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
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        float radius = dp2px(10);
        linearLayout.setBackgroundDrawable(CornerUtils.cornerDrawable(mBgColor, radius));//添加圆角
        linearLayout.setGravity(Gravity.CENTER);//居中
        linearLayout.setPadding(dp2px(25), dp2px(18), dp2px(25), dp2px(18));
        linearLayout.setOrientation(LinearLayout.VERTICAL);//纵向排列
        mLlContainer.addView(linearLayout);

        //圆形滚动条
        ProgressBar progressBar = new ProgressBar(mContext);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(dp2px(60), dp2px(60)));
        progressBar.setProgressDrawable(ResUtils.getDrawable(android.R.drawable.progress_horizontal));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //两种方式
            progressBar.setIndeterminateTintList(ColorStateList.valueOf(ResUtils.getColor(R.color.black)));
//            progressBar.setIndeterminateTintList(ContextCompat.getColorStateList(getContext(), R.color.black));
        }
        progressBar.setIndeterminate(false);//设置不确定，就变成了圆形
        progressBar.setProgressDrawable(ResUtils.getDrawable(R.drawable.progressbar_bg));
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
