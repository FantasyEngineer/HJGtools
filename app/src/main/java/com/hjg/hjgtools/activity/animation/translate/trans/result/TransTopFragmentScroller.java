package com.hjg.hjgtools.activity.animation.translate.trans.result;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjg.base.base.HJGBaseFragment;
import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.base.view.GeneralNestScrollView;
import com.hjg.hjgtools.R;


/**
 *
 */
public class TransTopFragmentScroller extends HJGBaseFragment {

    private TextView tvTitle2;
    private boolean isShow = true;

    @Override
    protected int getContentID() {
        return R.layout.fragment_tran_result_scroller;
    }

    int lHeight = 150;

    @Override
    protected void initViewAction(View view) {
        GeneralNestScrollView nestedScrollView = view.findViewById(R.id.scrollView);
        tvTitle2 = view.findViewById(R.id.tvTitle2);
        /*activity中获取宽高的方式*/
        tvTitle2.post(() -> lHeight = tvTitle2.getMeasuredHeight());
        nestedScrollView.setOnGeneralScrollChangedListener(new GeneralNestScrollView.OnGeneralScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                nestedScrollView.post(() -> {
                    int i = t - oldt;
                    ViewGroup.LayoutParams layoutParams = tvTitle2.getLayoutParams();
                    if (layoutParams.height < 0) {
                        tvTitle2.setVisibility(View.GONE);
                        return;
                    } else {
                        tvTitle2.setVisibility(View.VISIBLE);
                    }
                    layoutParams.height = (int) (layoutParams.height - i / 5);
                    if (layoutParams.height < 0) {
                        layoutParams.height = 0;
                    }
                    if (layoutParams.height > lHeight) {
                        layoutParams.height = lHeight;
                    }

                    tvTitle2.setLayoutParams(layoutParams);
                });
            }

            @Override
            public void onUpOpt() {
                super.onUpOpt();
                D.showShort("上滑");
            }

            @Override
            public void onDownOpt() {
                super.onDownOpt();
                D.showShort("下滑");
            }
        });
    }
}