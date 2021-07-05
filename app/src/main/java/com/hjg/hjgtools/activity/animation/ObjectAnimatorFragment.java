package com.hjg.hjgtools.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Path;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.hjg.base.base.HJGDatabindingBaseFragment;
import com.hjg.base.util.D;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.FragmentObjectAnimationBinding;

public class ObjectAnimatorFragment extends HJGDatabindingBaseFragment<FragmentObjectAnimationBinding> {
    @Override
    protected int getContentID() {
        return R.layout.fragment_object_animation;
    }

    @Override
    protected void initViewAction(View view) {
        databinding.tvTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                D.showShort("位移的过程可以点击");
            }
        });
        databinding.transRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*参数是不定长的,每个参数的位置都是相对于原来的位置0来计算的*/
                /*往右边位移300像素，然后再返回原位置*/
                ObjectAnimator.ofFloat(databinding.tvTarget, View.TRANSLATION_X, 0, 300, 0)
                        .setDuration(3000)
                        .start();

                /*往下位移300像素，然后再返回原来位置*/
                ObjectAnimator.ofFloat(databinding.tvTarget, View.TRANSLATION_Y, 0, 300, 0)
                        .setDuration(3000)
                        .start();

            }
        });

        databinding.transLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*往左边位移300像素，然后再返回原位置*/
                ObjectAnimator.ofFloat(databinding.tvTarget, View.TRANSLATION_X, 0, -300, 0)
                        .setDuration(3000)
                        .start();

            }
        });

        databinding.scaleBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*宽度的放大，从原大小编程2倍然后再返回0再返回到原来大小*/
                ObjectAnimator.ofFloat(databinding.tvTarget, "scaleX", 1, 2, 1, 3)
                        .setDuration(3000)
                        .start();
                ObjectAnimator.ofFloat(databinding.tvTarget, "scaleY", 1, 2, 1, 3)
                        .setDuration(3000)
                        .start();
            }
        });

        /*缩小*/
        databinding.scaleSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*组合*/
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(databinding.tvTarget, "scaleY", 1, 0.5F);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(databinding.tvTarget, "scaleX", 1, 0.5F);

                animatorSet.playTogether(objectAnimator, objectAnimator1);
                animatorSet.setDuration(3000);
                animatorSet.start();
            }
        });


        databinding.rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(databinding.tvTarget, "rotation", 0, 360)
                        .setDuration(2000);
                objectAnimator.setInterpolator(new AccelerateInterpolator());
                objectAnimator.start();


            }
        });
        databinding.rotateX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(databinding.tvTarget, View.ROTATION_X, 0, 30, 0)
                        .setDuration(1600);

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(databinding.tvTarget, View.TRANSLATION_Y, 0, 100)
                        .setDuration(800);

                ObjectAnimator objectAnimatorScalex = ObjectAnimator.ofFloat(databinding.tvTarget, View.SCALE_X, 1, 0.8f)
                        .setDuration(800);
                ObjectAnimator objectAnimatorScaley = ObjectAnimator.ofFloat(databinding.tvTarget, View.SCALE_Y, 1, 0.8f)
                        .setDuration(800);

//                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(databinding.tvTarget, View.ROTATION_X, 0, 0)
//                        .setDuration(800);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimator1, objectAnimator, objectAnimatorScalex, objectAnimatorScaley);

                animatorSet.start();

//                AnimatorSet animatorSet1 = new AnimatorSet();
//                animatorSet1.playSequentially(animatorSet, objectAnimator2);
//
//
//                animatorSet1.start();


            }
        });


        /*绕着y轴旋转*/
        databinding.rotateY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(databinding.tvTarget, View.ROTATION_Y, 0, -360)
                        .setDuration(2000);
                objectAnimator.setInterpolator(new AccelerateInterpolator());
                objectAnimator.start();

            }
        });

        databinding.alphaTo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(databinding.tvTarget, "alpha", 0, 1)
                        .setDuration(3000)
                        .start();

            }
        });

        databinding.alphaTo0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(databinding.tvTarget, "alpha", 1, 0)
                        .setDuration(3000)
                        .start();
            }
        });


        databinding.transMerge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet = new AnimatorSet();

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(databinding.tvTarget, "alpha", 0, 1).setDuration(3000);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(databinding.tvTarget, "scaleY", 1, 0.5F).setDuration(3000);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(databinding.tvTarget, "translationX", 0, 300).setDuration(3000);

                /*顺序播放，先显示出来再位移*/
//                animatorSet.playSequentially(objectAnimator, objectAnimator1, objectAnimator2);
                /*三组动画一起播放*/
                animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2);
                animatorSet.start();

            }
        });


        /*双属性*/
        databinding.twoProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                path的x为属性1的变化值，y为属性2的变化值
                Path path = new Path();
                path.moveTo(0, 0);
                path.lineTo(50, 1);
                path.lineTo(100, 2);
                path.lineTo(300, 1);
                ObjectAnimator.ofFloat(databinding.tvTarget, "translationX", "scaleX", path)
                        .setDuration(3000)
                        .start();


            }
        });


        /*自定义的ObjectAnimator*/
        databinding.testCustomAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(databinding.customObjectorAnimator, "radius", 100, 300);
                objectAnimator.setRepeatCount(-1);
                objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofInt(databinding.customObjectorAnimator, "color", Color.GREEN, Color.RED);
                objectAnimator1.setRepeatCount(-1);
                objectAnimator1.setRepeatMode(ValueAnimator.RESTART);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofInt(databinding.customObjectorAnimator, "angle", 0, 10000);
                objectAnimator2.setRepeatCount(-1);
                objectAnimator2.setRepeatMode(ValueAnimator.REVERSE);
                animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2);
                animatorSet.setDuration(10000);
                animatorSet.setInterpolator(new LinearInterpolator());
                animatorSet.start();

            }
        });

    }
}
