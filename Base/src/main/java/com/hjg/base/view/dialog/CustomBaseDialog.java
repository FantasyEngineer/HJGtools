package com.hjg.base.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.hjg.base.R;
import com.hjg.base.util.DrawableUtils;
import com.hjg.base.util.SizeUtils;
import com.hjg.base.view.flyco.animation.Attention.Swing;
import com.hjg.base.view.flyco.dialog.widget.base.BaseDialog;


public class CustomBaseDialog extends BaseDialog<CustomBaseDialog> {
    TextView mTvCancel;
    TextView mTvExit;

    public CustomBaseDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        showAnim(new Swing());

        // dismissAnim(this, new ZoomOutExit());
        View inflate = View.inflate(getContext(), R.layout.dialog_custom_base, null);
        mTvCancel = inflate.findViewById(R.id.tv_cancel);
        mTvExit = inflate.findViewById(R.id.tv_exit);
        inflate.setBackgroundDrawable(
                DrawableUtils.cornerDrawable(Color.parseColor("#ffffff"), SizeUtils.dp2px(5)));

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
