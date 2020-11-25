package com.hjg.base.view.flyco.animation.FadeExit;

import android.animation.ObjectAnimator;
import android.view.View;

import com.hjg.base.view.flyco.animation.BaseAnimatorSet;

public class FadeExit extends BaseAnimatorSet {
    protected int duration = 200;

    @Override
    public void setAnimation(View view) {
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(duration));
    }
}
