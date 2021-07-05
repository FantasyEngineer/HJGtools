package com.hjg.hjgtools.activity.animation.translate.trans.transfragment;

import android.view.View;

import com.hjg.base.base.HJGBaseFragment;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.animation.translate.trans.view.ScrollerFrameLayout;


public class ScrollerFragment extends HJGBaseFragment {


    @Override
    protected int getContentID() {
        return R.layout.fragment_scroller;
    }

    @Override
    protected void initViewAction(View view) {
        ScrollerFrameLayout scrollerFrameLayout = view.findViewById(R.id.scrollerFrameLayout);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollerFrameLayout.smoothScrollTo(-200, -300);
            }
        });


    }

}