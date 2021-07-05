package com.hjg.base.view.flyco.animation.FadeEnter;

import android.animation.ObjectAnimator;
import android.view.View;

import com.hjg.base.view.flyco.animation.BaseAnimatorSet;

public class FadeEnter extends BaseAnimatorSet {
    protected int duration = 200;

    @Override
    public void setAnimation(View view) {
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(duration));
    }
}
