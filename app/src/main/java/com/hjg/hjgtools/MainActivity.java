package com.hjg.hjgtools;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.util.ActivityUtils;
import com.hjg.hjgtools.activity.asynchronous.AsyncActivity;
import com.hjg.hjgtools.activity.coin.BiRepsActivity;
import com.hjg.hjgtools.activity.animation.AnimationActivity;
import com.hjg.hjgtools.activity.titleBar.TitleBarActivity;
import com.hjg.hjgtools.activity.compress.CompressActivity;
import com.hjg.hjgtools.activity.TestActivity;
import com.hjg.hjgtools.activity.widget.bound.BoundActivity;
import com.hjg.hjgtools.activity.camera.CameraActivity;
import com.hjg.hjgtools.activity.conflict.ConflictActivity;
import com.hjg.hjgtools.activity.dialog.DialogActivity;
import com.hjg.hjgtools.activity.encrytion.EncryptionActivity;
import com.hjg.hjgtools.activity.file.FileActivity;
import com.hjg.hjgtools.activity.fragment.FragmentActivity;
import com.hjg.hjgtools.activity.jetpack.JetPackActivity;
import com.hjg.hjgtools.activity.jni.JniActivity;
import com.hjg.hjgtools.activity.mvvm.MvvmActivity;
import com.hjg.hjgtools.activity.notification.NotificationActivity;
import com.hjg.hjgtools.activity.permission.PermissionActivity;
import com.hjg.hjgtools.activity.receiver.ReceiverActivity;
import com.hjg.hjgtools.activity.share.ShareActivity;
import com.hjg.hjgtools.activity.system.SystemActivity;
import com.hjg.hjgtools.activity.jetpack.task.TaskActivity;
import com.hjg.hjgtools.activity.touchtest.TouchActivity;
import com.hjg.hjgtools.activity.widget.WidgetActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;


public class MainActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        fab.setVisibility(View.VISIBLE);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) < 30) {//??????????????????
                    return;
                }
                if (dy < 0 && fab.isHidden()) { // ????????????????????????
                    fab.show(true);
                } else if (dy > 0 && fab.isShown()) { // ????????????????????????
                    fab.hide(true);
                }
            }
        });

        fab.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(TITLE, "????????????");
            ActivityUtils.startActivity(SystemActivity.class, bundle);
        });

//        ActivityUtils.startActivity(TwoWayActivity.class);//????????????
//        ActivityUtils.startActivity(DatabindingActivity.class);
//        ActivityUtils.startActivity(TitleBarActivity.class);
//        ActivityUtils.startActivity(MvvmActivity.class);
//        ActivityUtils.startActivity(ViewTransActivity.class);
//        ActivityUtils.startActivity(AnimationActivity.class);


    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean("??????", AnimationActivity.class, "?????????????????????", R.drawable.ic_icon_animation));
        listBeans.add(new RecyclerListBean("????????????", BiRepsActivity.class, "", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("JetPack", JetPackActivity.class, "", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("?????????????????????", CompressActivity.class, "????????????", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("????????????", TouchActivity.class, "?????????????????????", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("??????", DialogActivity.class, "?????????????????????????????????", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("??????", ReceiverActivity.class, "?????????????????????", R.drawable.ic_icon_broadcast));
        listBeans.add(new RecyclerListBean("??????", NotificationActivity.class, "???????????????????????????????????????", R.drawable.ic_icon_notification));
        listBeans.add(new RecyclerListBean("?????????", EncryptionActivity.class, "base64???RSA???????????????,??????????????????", R.drawable.ic_icon_encryption));
        listBeans.add(new RecyclerListBean("??????", WidgetActivity.class, "???????????????????????????", R.drawable.ic_icon_view));
        listBeans.add(new RecyclerListBean("????????????", ShareActivity.class, "??????????????????????????????", R.drawable.ic_icon_share));
        listBeans.add(new RecyclerListBean("????????????", FileActivity.class, "", R.drawable.ic_icon_file));
        listBeans.add(new RecyclerListBean("????????????", BoundActivity.class, "", R.drawable.ic_icon_bound));
        listBeans.add(new RecyclerListBean("????????????", ConflictActivity.class, "", R.drawable.ic_icon_conflict));
        listBeans.add(new RecyclerListBean("????????????", PermissionActivity.class, "??????????????????", R.drawable.ic_icon_permission));
        listBeans.add(new RecyclerListBean("?????????Fragment", FragmentActivity.class, "fragment?????????", R.drawable.ic_icon_fragment));
        listBeans.add(new RecyclerListBean("??????", TaskActivity.class, "?????????????????????job????????????", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("???????????????", CameraActivity.class, "???????????????????????????", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("??????", TestActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("Mvvm??????", MvvmActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("JNI??????", JniActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("WebView??????", TestActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("?????????", TitleBarActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("?????????????????????????????????", AsyncActivity.class, "aidl???handler???rxjava", R.drawable.ic_icon_task));
        return listBeans;
    }


    @Override
    protected boolean isShowBackArrow() {
        return false;
    }
}