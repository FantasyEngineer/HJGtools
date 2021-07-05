package com.hjg.hjgtools.activity.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.hjg.base.base.HJGBaseFragment;
import com.hjg.base.base.HJGDatabindingBaseFragment;
import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.FragmentFrameBinding;

/**
 * 帧动画
 */
public class FrameFragment extends HJGDatabindingBaseFragment<FragmentFrameBinding> {


    @Override
    protected int getContentID() {
        return R.layout.fragment_frame;
    }

    private AnimationDrawable drawable = null;
    private AnimationDrawable animationDrawable;

    @Override
    protected void initViewAction(View view) {


        databinding.start.setOnClickListener(v -> {
            /*使用xml的方式*/
            if (databinding.useXml.isChecked()) {
                /*当不等于空，并且不在运行中的时候，才能启动*/
                if (drawable == null || !drawable.isRunning()) {
                    drawable = (AnimationDrawable) databinding.iv.getDrawable();
                    drawable.setVisible(true, true);
                    drawable.start();
                }
            } else {//使用AnimationDrawable添加帧的形式
                /*动态创建一帧一帧*/
                animationDrawable = new AnimationDrawable();
                animationDrawable.addFrame(ResUtils.getDrawable(R.drawable.ic_icon_animation), 200);
                animationDrawable.addFrame(ResUtils.getDrawable(R.drawable.ic_icon_bound), 200);
                animationDrawable.addFrame(ResUtils.getDrawable(R.drawable.ic_icon_broadcast), 200);
                animationDrawable.addFrame(ResUtils.getDrawable(R.drawable.ic_icon_buy), 200);
                animationDrawable.addFrame(ResUtils.getDrawable(R.drawable.ic_icon_encryption), 200);
                databinding.iv2.setBackground(animationDrawable);
                animationDrawable.setVisible(true, true);
                animationDrawable.start();
            }


        });

        databinding.end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databinding.useXml.isChecked()) {
                    if (drawable != null && drawable.isRunning()) {
                        drawable.stop();
                        drawable = null;

                    }
                } else {
                    if (animationDrawable != null && animationDrawable.isRunning()) {
                        animationDrawable.stop();
                        animationDrawable = null;
                    }
                }

            }
        });


    }


}