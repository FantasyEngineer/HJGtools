package com.hjg.hjgtools.activity.animation.translate.trans.result;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjg.base.base.HJGBaseFragment;
import com.hjg.base.util.D;
import com.hjg.base.view.GeneralNestScrollView;
import com.hjg.hjgtools.R;


/**
 * 真实项目头部位移展示，使用transx
 */
public class TransTopFragmentTransX extends HJGBaseFragment {

    private TextView tvTitle2;
    private boolean isShow = true;
    private int imageWidth;

    @Override
    protected int getContentID() {
        return R.layout.fragment_tran_result_transx;
    }

    int lHeight = 150;

    @Override
    protected void initViewAction(View view) {
        GeneralNestScrollView nestedScrollView = view.findViewById(R.id.scrollView);
        ImageView image = view.findViewById(R.id.image);
        ImageView image2 = view.findViewById(R.id.image2);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                D.showLong("点击事件的位置有没有变化");
            }
        });
        image.post(new Runnable() {
            @Override
            public void run() {
                imageWidth = image.getWidth();
            }
        });
        tvTitle2 = view.findViewById(R.id.tvTitle2);
        /*activity中获取宽高的方式*/
        tvTitle2.post(() -> lHeight = tvTitle2.getMeasuredHeight());
        nestedScrollView.setOnGeneralScrollChangedListener(new GeneralNestScrollView.OnGeneralScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                nestedScrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (t - oldt > 0 && isShow) {
                            isShow = false;
                            //将Y属性变为底部栏高度  (相当于隐藏了)
                            tvTitle2.animate().translationY(-150);
                            tvTitle2.animate().start();
                        } else if (t - oldt < 0 && !isShow) {
                            isShow = true;
                            tvTitle2.animate().translationY(0);
                            tvTitle2.animate().start();
                        }
                    }
                });


            }

            @Override
            public void onUpOpt() {
                super.onUpOpt();
                image.animate().translationX(imageWidth / 2);
                image.animate().start();

                image2.animate().translationX(imageWidth);
                image2.animate().start();
            }

            @Override
            public void onDownOpt() {
                super.onDownOpt();
                //0是归位
                image.animate().translationX(0);
                image.animate().start();


                image2.animate().translationX(0);
                image2.animate().start();
            }
        });
    }
}