package com.hjg.base.view.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.hjg.base.R;
import com.hjg.base.util.ResUtils;
import com.hjg.base.view.flyco.dialog.widget.base.BaseDialog;
import com.hjg.base.view.flyco.dialog.widget.base.TopBaseDialog;
import com.hjg.base.view.flyco.dialog.widget.internal.BaseAlertDialog;

public class FullScreenDialog extends BaseDialog<FullScreenDialog> {
    public FullScreenDialog(Context context) {
        super(context);
        setCanceledOnTouchOutside(false);

    }

    @Override
    public View onCreateView() {
        mLlTop.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        mLlTop.setBackgroundColor(ResUtils.getColor(R.color.white));
        mLlTop.setGravity(Gravity.TOP);

        View view = getLayoutInflater().inflate(R.layout.dialog_fullscreen, null);

        return view;
    }

    @Override
    public void setUiBeforShow() {

    }
}
