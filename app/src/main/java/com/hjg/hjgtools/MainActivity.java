package com.hjg.hjgtools;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.P;
import com.hjg.base.util.StrUtil;
import com.hjg.base.view.HorizontalLoadingDialog;
import com.hjg.base.view.SpecialHorizontalLoadingDialog;
import com.hjg.hjgtools.activity.dialog.DialogActivity;
import com.hjg.hjgtools.activity.edittext.EdittextActivity;
import com.hjg.hjgtools.activity.encrytion.EncryptionActivity;
import com.hjg.hjgtools.activity.file.FileActivity;
import com.hjg.hjgtools.activity.notification.NotificationActivity;
import com.hjg.hjgtools.activity.share.ShareActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;
import com.hjg.hjgtools.receiver.ReceiverActivity;

import java.util.ArrayList;


public class MainActivity extends HJGBaseRecyclerActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String loginStatus = P.getString(Config.LOGIN_STATUS);
        if (StrUtil.isEmpty(loginStatus)) {
            ActivityUtils.startActivity(LoginActivity.class);
        }
    }

    @Override
    protected void onItemClick(int position, RecyclerListBean recyclerListBean) {
//        ActivityUtils.startActivity(recyclerListBean.getaClass());
        SpecialHorizontalLoadingDialog horizontalLoadingDialog = new SpecialHorizontalLoadingDialog(activity, 50, R.drawable.progress_horizon_special);
        horizontalLoadingDialog.show();
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        RecyclerListBean bean1 = new RecyclerListBean("弹窗", DialogActivity.class, "各种已经实现的弹窗方案", R.drawable.ic_icon_dialog);
        RecyclerListBean bean2 = new RecyclerListBean("广播测试", ReceiverActivity.class, "静态与动态广播", R.drawable.ic_icon_broadcast);
        RecyclerListBean bean3 = new RecyclerListBean("通知测试", NotificationActivity.class, "适配了低版本和高版本的通知", R.drawable.ic_icon_notification);
        RecyclerListBean bean4 = new RecyclerListBean("加解密", EncryptionActivity.class, "base64，RSA非对称加密", R.drawable.ic_icon_encryption);
        RecyclerListBean bean5 = new RecyclerListBean("控件", EdittextActivity.class, "各种控件的展示方案", R.drawable.ic_icon_view);
        RecyclerListBean bean6 = new RecyclerListBean("分享功能", ShareActivity.class, "集成了各种平台的分享", R.drawable.ic_icon_share);
        RecyclerListBean bean7 = new RecyclerListBean("文件操作", FileActivity.class, "", R.drawable.ic_icon_file);

        listBeans.add(bean1);
        listBeans.add(bean2);
        listBeans.add(bean3);
        listBeans.add(bean4);
        listBeans.add(bean5);
        listBeans.add(bean6);
        listBeans.add(bean7);
        return listBeans;
    }

}