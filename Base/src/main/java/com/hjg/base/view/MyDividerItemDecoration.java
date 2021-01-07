package com.hjg.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Size;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.R;
import com.hjg.base.util.DrawableUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.SizeUtils;


/**
 * RecyclerVIew的 分割线
 */

public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable drawable;


    public MyDividerItemDecoration() {
        drawable = ResUtils.getDrawable(R.drawable.dividerline);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent);
    }

    /**
     * 绘制间隔
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();//获取一个页面的item的数量
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);//获取子view在ViewGroup的确切位置
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }
}
