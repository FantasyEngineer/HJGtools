package com.hjg.hjgtools.activity.animation;

import android.graphics.Matrix;
import android.icu.number.Scale;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.hjg.base.base.HJGBaseFragment;
import com.hjg.base.base.HJGDatabindingBaseFragment;
import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.animation.translate.trans.view.GestureDetectorView;
import com.hjg.hjgtools.databinding.FragmentTweenBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.RELATIVE_TO_PARENT;
import static android.view.animation.Animation.RELATIVE_TO_SELF;
import static android.view.animation.Animation.RESTART;
import static android.view.animation.Animation.REVERSE;

/**
 * 补间动画
 * <p>
 * <p>
 * <p>
 * <p>
 * android：interpolator="@android:anim/decelerate_interpolator"是什么含义，
 * 文档里说的也不太清楚，其实很简单，看下面：
 * interpolator定义一个动画的变化率（the rate of change）。
 * 这使得基本的动画效果(alpha, scale, translate, rotate）得以加速，减速，重复等。
 * -->
 * <p>
 * accelerate_decelerate_interpolator 在动画开始与介绍的地方速率改变比较慢，在中间的时侯加速
 * accelerate_interpolator 在动画开始的地方速率改变比较慢，然后开始加速
 * decelerate_interpolator 在动画开始的地方速率改变比较慢，然后开始减速
 * cycle_interpolator  动画循环播放特定的次数，速率改变沿着正弦曲线
 * linear_interpolator 在动画的以均匀的速率改变(变化率是个常数，即 f (x) = x.)
 * overshoot_interpolator 向前甩一定值后再回到原来位置
 * bounce_interpolator 动画结束的时候弹起
 * -->
 * <p>
 */
public class TweenFragment extends HJGDatabindingBaseFragment<FragmentTweenBinding> {
    int width, height;


    @Override
    protected int getContentID() {
        return R.layout.fragment_tween;
    }

    @Override
    protected void initViewAction(View view) {
        /*目标控件*/
        TextView tvTarget = view.findViewById(R.id.tvTarget);
        tvTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                D.showShort("确认点击位置");
            }
        });

//        setanimation不会启动动画，只有当view进行invalidate才会。
        Animation animationx = AnimationUtils.loadAnimation(getActivity(), R.anim.trans_right);
        animationx.start();
//        tvTarget.setAnimation(animationx);
//        tvTarget.invalidate();

        /*----------------------------------------位移----------------------------------------*/
        Button transRight = view.findViewById(R.id.transRight);
        transRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databinding.useXml.isChecked()) {
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.trans_right);
                    animation.start();
                    tvTarget.startAnimation(animation);
                } else {
                    /*增量偏移，默认是ABSOLUTE（绝对位置）类型,从-100，0的坐标移动到100，0的坐标*/
//                    TranslateAnimation translateAnimation = new TranslateAnimation(-100, 100, 0, 0);
                    /*百分比坐标位移，相对于父布局的宽高，这里的输入都是百分比，1代表父布局的100%*/
//                    TranslateAnimation translateAnimation = new TranslateAnimation(RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0.8f,
//                            Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
                    /*百分比坐标位移，相对于自身布局的宽高，这里的输入都是百分比，1代表自身的100%位置，这里是从当前位置移动到相当与自身宽度2倍的距离处*/
                    transRight(databinding.tvTarget);
                }
            }
        });

        Button transLeft = view.findViewById(R.id.transLeft);
        transLeft.setOnClickListener(v -> {
            if (databinding.useXml.isChecked()) {
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.trans_left);
                tvTarget.startAnimation(animation);
            } else {
                TranslateAnimation translateAnimation = new TranslateAnimation(100, -100, 0, 0);
                startAnimation(translateAnimation);
            }
        });


        tvTarget.post(new Runnable() {
            @Override
            public void run() {
                width = tvTarget.getWidth();
                height = tvTarget.getHeight();
            }
        });

        /*----------------------------------------缩放----------------------------------------*/
        /*放大zoom in*/
        databinding.scaleBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databinding.useXml.isChecked()) {
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_big);
                    tvTarget.startAnimation(animation);
                } else {
                    /*以view左上角位置进行放大2倍，放大的中心点是0，0，当view改变时，唯有这个点是不变的*/
//                    ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 2);
                    /**/

                    /*以自身中心的位置进行放大2倍*/
//                    ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 2, width / 2, height / 2);
//                    startAnimation(scaleAnimation);

                    /*循环往复*/
                    AnimationSet scaleanimationSet = new AnimationSet(true);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
                    /*设置无限循环*/
                    scaleAnimation.setRepeatCount(INFINITE);
                    scaleAnimation.setRepeatMode(2);
                    scaleanimationSet.addAnimation(scaleAnimation);
                    scaleanimationSet.setDuration(2000);
                    scaleanimationSet.setFillAfter(true);
                    tvTarget.startAnimation(scaleanimationSet);

                }
            }
        });
        /*缩小zoom out*/
        databinding.scaleSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databinding.useXml.isChecked()) {
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_small);
                    tvTarget.startAnimation(animation);
                } else {

                    ScaleAnimation scaleAnimation = new ScaleAnimation(2, 0.5f, 2, 0.5f);
//                    ScaleAnimation animationSetscaleAnimation = new ScaleAnimation(2, 0.5f, 2, 0.5f, width / 2, height / 2);
                    startAnimation(scaleAnimation);


                }
            }
        });


        /*----------------------------------------旋转----------------------------------------*/
        databinding.rotateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databinding.useXml.isChecked()) {
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_right);
                    tvTarget.startAnimation(animation);
                } else {
                    /*以左上角位置开始旋转*/
//                    RotateAnimation rotateAnimation = new RotateAnimation(0, 360);
                    /*以中心点位置开始*/

                    AnimationSet animationSet = new AnimationSet(false);
                    RotateAnimation rotateAnimation = new RotateAnimation(0, 360, width / 2, height / 2);

                    /*给具体动画设置成循环次数设置无限循环*/
                    rotateAnimation.setRepeatCount(INFINITE);
                    rotateAnimation.setRepeatMode(2);

                    /*给set设置属性*/
                    animationSet.addAnimation(rotateAnimation);
                    startAnimation(animationSet);
                }

            }
        });
        /*左边旋转*/
        databinding.rotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (databinding.useXml.isChecked()) {
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_left);
                    tvTarget.startAnimation(animation);

                } else {
                    RotateAnimation rotateAnimation = new RotateAnimation(0, -360, 0, 0);
                    startAnimation(rotateAnimation);

                }
            }
        });


        /*----------------------------------------渐变----------------------------------------*/

        databinding.alphaTo0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databinding.useXml.isChecked()) {
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_0);
                    tvTarget.startAnimation(animation);
                } else {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.5f);
                    startAnimation(alphaAnimation);

                }
            }
        });
        databinding.alphaTo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databinding.useXml.isChecked()) {
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_1);
                    tvTarget.startAnimation(animation);
                } else {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1);
                    startAnimation(alphaAnimation);

                }

            }
        });

        /*----------------------------------------组合----------------------------------------*/
        Button transMerge = view.findViewById(R.id.transMerge);
        transMerge.setOnClickListener(v -> transLeft.post(() -> {
            if (databinding.useXml.isChecked()) {
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.merge);
                tvTarget.startAnimation(animation);
            } else {
                /*创建动画set,不共享差值器*/
                AnimationSet animationSet = new AnimationSet(false);

                /*创建平移动画a*/
                TranslateAnimation translateAnimationX = new TranslateAnimation(RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0.8f,
                        Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
                translateAnimationX.setInterpolator(new LinearInterpolator());
                translateAnimationX.setRepeatCount(INFINITE);
                translateAnimationX.setRepeatMode(2);

                /*创建竖直动画b*/
                TranslateAnimation translateAnimationY = new TranslateAnimation(RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0.8f);
                translateAnimationY.setInterpolator(new AccelerateInterpolator(2));
                translateAnimationY.setRepeatCount(INFINITE);
                translateAnimationY.setRepeatMode(2);

                /*创建动画b*/
                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.5f, 1, 0.5f, width / 2, height / 2);
                scaleAnimation.setRepeatCount(INFINITE);
                scaleAnimation.setRepeatMode(2);

                /*添加动画*/
                animationSet.addAnimation(translateAnimationX);
                animationSet.addAnimation(translateAnimationY);
                animationSet.addAnimation(scaleAnimation);

                /*添加set属性*/
                animationSet.setDuration(2000);
                animationSet.setFillAfter(true);

                /*view执行动画r*/
                databinding.tvTarget.startAnimation(animationSet);

            }


        }));


        databinding.test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databinding.test.setEnabled(false);
                Observable<Long> interval = Observable.interval(2, TimeUnit.SECONDS);
                interval.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        TweenFragment.this.d = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        //这里10分钟之后停止，10*60/2
                        L.d("aLong.intValue()" + aLong.intValue());
                        if (aLong.intValue() == 300) {
                            if (!TweenFragment.this.d.isDisposed()) {
                                TweenFragment.this.d.dispose();
                            }
                        }

                        try {
                            //执行大量的动画看看内存消耗
                            transRight(databinding.transRight);
                            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.trans_left);
                            databinding.transLeft.startAnimation(animation);

                            Animation animationScaleBig = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_big);
                            databinding.scaleBig.startAnimation(animationScaleBig);
                            Animation animationScaleSmall = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_small);
                            databinding.scaleSmall.startAnimation(animationScaleSmall);

                            Animation animationRotate = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_right);
                            databinding.rotateRight.startAnimation(animationRotate);

                            Animation animationLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_left);
                            databinding.rotateLeft.startAnimation(animationLeft);

                        } catch (Exception e) {
                            D.showShort("e" + e.getMessage());
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

            }
        });


    }


    /*右边移动*/
    private void transRight(View v) {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 2,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        animationSet.setRepeatCount(2);
        animationSet.setRepeatMode(REVERSE);

        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(200);
        animationSet.setFillAfter(true);

        v.startAnimation(animationSet);
    }

    Disposable d;

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*关闭循环*/
        if (TweenFragment.this.d != null) {
            if (!TweenFragment.this.d.isDisposed()) {
                TweenFragment.this.d.dispose();
            }
        }
    }

    /*开始动画*/
    private void startAnimation(Animation scaleAnimation) {
        scaleAnimation.setDuration(2000);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        scaleAnimation.setFillAfter(true);
        databinding.tvTarget.startAnimation(scaleAnimation);
    }


}