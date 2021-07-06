package com.hjg.hjgtools;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.util.ActivityUtils;
import com.hjg.hjgtools.activity.animation.translate.ViewTransActivity;
import com.hjg.hjgtools.activity.asynchronous.AsyncActivity;
import com.hjg.hjgtools.activity.coin.BiRepsActivity;
import com.hjg.hjgtools.activity.animation.AnimationActivity;
import com.hjg.hjgtools.activity.handler.HandlerTestActivity;
import com.hjg.hjgtools.activity.titleBar.TitleBarActivity;
import com.hjg.hjgtools.activity.compress.CompressActivity;
import com.hjg.hjgtools.activity.handler.HandlerActivity;
import com.hjg.hjgtools.activity.TestActivity;
import com.hjg.hjgtools.activity.ipc.aidl.AIdlServerActivity;
import com.hjg.hjgtools.activity.bound.BoundActivity;
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
import com.hjg.hjgtools.activity.rx.RxActivity;
import com.hjg.hjgtools.activity.share.ShareActivity;
import com.hjg.hjgtools.activity.system.SystemActivity;
import com.hjg.hjgtools.activity.task.TaskActivity;
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
                if (Math.abs(dy) < 30) {//手指颤抖距离
                    return;
                }
                if (dy < 0 && fab.isHidden()) { // 当前处于上滑状态
                    fab.show(true);
                } else if (dy > 0 && fab.isShown()) { // 当前处于下滑状态
                    fab.hide(true);
                }
            }
        });

        fab.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(TITLE, "系统详情");
            ActivityUtils.startActivity(SystemActivity.class, bundle);
        });

//        ActivityUtils.startActivity(TwoWayActivity.class);//双向绑定
//        ActivityUtils.startActivity(DatabindingActivity.class);
//        ActivityUtils.startActivity(TitleBarActivity.class);
//        ActivityUtils.startActivity(MvvmActivity.class);
//        ActivityUtils.startActivity(ViewTransActivity.class);
//        ActivityUtils.startActivity(AnimationActivity.class);

    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean("原理测试", HandlerTestActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("动画", AnimationActivity.class, "各种动画的使用", R.drawable.ic_icon_animation));
        listBeans.add(new RecyclerListBean("币圈仓库", BiRepsActivity.class, "", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("JetPack", JetPackActivity.class, "", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("压缩，内存优化", CompressActivity.class, "压缩图片", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("触摸测试", TouchActivity.class, "触摸事件的实践", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("弹窗", DialogActivity.class, "各种已经实现的弹窗方案", R.drawable.ic_icon_dialog));
        listBeans.add(new RecyclerListBean("广播", ReceiverActivity.class, "静态与动态广播", R.drawable.ic_icon_broadcast));
        listBeans.add(new RecyclerListBean("通知", NotificationActivity.class, "适配了低版本和高版本的通知", R.drawable.ic_icon_notification));
        listBeans.add(new RecyclerListBean("加解密", EncryptionActivity.class, "base64，RSA非对称加密,中文加码解码", R.drawable.ic_icon_encryption));
        listBeans.add(new RecyclerListBean("控件", WidgetActivity.class, "各种控件的展示方案", R.drawable.ic_icon_view));
        listBeans.add(new RecyclerListBean("分享功能", ShareActivity.class, "集成了各种平台的分享", R.drawable.ic_icon_share));
        listBeans.add(new RecyclerListBean("文件操作", FileActivity.class, "", R.drawable.ic_icon_file));
        listBeans.add(new RecyclerListBean("弹性布局", BoundActivity.class, "", R.drawable.ic_icon_bound));
        listBeans.add(new RecyclerListBean("冲突解决", ConflictActivity.class, "", R.drawable.ic_icon_conflict));
        listBeans.add(new RecyclerListBean("权限申请", PermissionActivity.class, "各种权限申请", R.drawable.ic_icon_permission));
        listBeans.add(new RecyclerListBean("碎片化Fragment", FragmentActivity.class, "fragment的用法", R.drawable.ic_icon_fragment));
        listBeans.add(new RecyclerListBean("任务", TaskActivity.class, "定时任务，后台job任务等等", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("相机与相册", CameraActivity.class, "相机及其相册的功能", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("测试", TestActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("Mvvm架构", MvvmActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("JNI调用", JniActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("WebView测试", TestActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("标题栏", TitleBarActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("跨进程线程通信", AsyncActivity.class, "aidl，handler，rxjava", R.drawable.ic_icon_task));
        return listBeans;
    }


    @Override
    protected boolean isShowBackArrow() {
        return false;
    }
}