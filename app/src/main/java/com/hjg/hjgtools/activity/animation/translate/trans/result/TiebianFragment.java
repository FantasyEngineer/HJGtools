package com.hjg.hjgtools.activity.animation.translate.trans.result;

import android.view.View;

import com.hjg.base.base.HJGBaseFragment;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.animation.translate.trans.view.TiebianViewAuto;


/**
 * 贴边的view，只能上下滑动，自动贴边
 */
public class TiebianFragment extends HJGBaseFragment {

    @Override
    protected int getContentID() {
        return R.layout.fragment_tiebian;
    }


    @Override
    protected void initViewAction(View view) {
        TiebianViewAuto tbLeftView = view.findViewById(R.id.tbLeftView);
        tbLeftView.setTieBian(TiebianViewAuto.LEFT);
        TiebianViewAuto tbRightView = view.findViewById(R.id.tbRightView);
        tbRightView.setTieBian(TiebianViewAuto.RIGHT);

    }
}