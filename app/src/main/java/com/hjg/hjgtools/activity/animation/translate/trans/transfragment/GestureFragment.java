package com.hjg.hjgtools.activity.animation.translate.trans.transfragment;

import android.view.View;

import com.hjg.base.base.HJGBaseFragment;
import com.hjg.base.util.D;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.animation.translate.trans.view.GestureDetectorView;

/**
 * 使用GestureDetectorView来操作手势
 * 既可以滑动，也可以触发点击事件
 */
public class GestureFragment extends HJGBaseFragment {


    @Override
    protected int getContentID() {
        return R.layout.fragment_gesture;
    }

    @Override
    protected void initViewAction(View view) {
        GestureDetectorView gestureDetectorView = view.findViewById(R.id.gestureDetectorView);
        gestureDetectorView.setOnGestureClickListener(new GestureDetectorView.OnGestureClickListener() {
            @Override
            public void onClick() {
                D.showShort("点击事件");
            }
        });


    }

}