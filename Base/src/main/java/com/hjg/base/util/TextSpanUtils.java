package com.hjg.base.util;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.ColorInt;

public class TextSpanUtils {

    /**
     * 给字添加颜色
     *
     * @param data       总的数据
     * @param changeData 需要变色的数据
     * @param color      颜色
     * @return settext（返回值）
     */
    public static SpannableStringBuilder setTextColor(String data, String changeData, @ColorInt int color) {
        if (StrUtil.isEmpty(data) || StrUtil.isEmpty(changeData)) {
            return null;
        }
        SpannableStringBuilder builder = new SpannableStringBuilder(data);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        int start = data.indexOf(changeData);
        int end = start + changeData.length();
        builder.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
