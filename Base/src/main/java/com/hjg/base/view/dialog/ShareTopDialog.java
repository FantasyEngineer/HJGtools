package com.hjg.base.view.dialog;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.hjg.base.R;
import com.hjg.base.view.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.hjg.base.view.flyco.dialog.widget.base.TopBaseDialog;


public class ShareTopDialog extends TopBaseDialog<ShareTopDialog> {
    LinearLayout mLlWechatFriendCircle;
    LinearLayout mLlWechatFriend;
    LinearLayout mLlQq;
    LinearLayout mLlSms;

    public ShareTopDialog(Context context, View animateView) {
        super(context, animateView);
    }

    public ShareTopDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        showAnim(new FlipVerticalSwingEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_share, null);
        mLlWechatFriendCircle = inflate.findViewById(R.id.ll_wechat_friend_circle);
        mLlWechatFriend = inflate.findViewById(R.id.ll_wechat_friend);
        mLlQq = inflate.findViewById(R.id.ll_qq);
        mLlSms = inflate.findViewById(R.id.ll_sms);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mLlWechatFriendCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mLlWechatFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mLlQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mLlSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
