package com.hjg.hjgtools.activity.camera;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.hjg.base.util.D;
import com.hjg.base.util.DateUtils;
import com.hjg.base.util.DeviceUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.functions.Consumer;

/**
 * 相机操作,相册页面
 */
public class CameraActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RxPermissions(this).request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
                    D.showShort("权限获取失败，可能某些数据取不到");
                }
            }
        });
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("打开相机", "打开相机,并且保存拍照照片到相应文件位置,需要适配低版本,但是onActivityResult返回的data是空值"));
        recyclerListBeans.add(new RecyclerListBean("打开相机拍照", "打开相机，onActivityResult返回的data不为空值，再选择保存到其他位置"));
        recyclerListBeans.add(new RecyclerListBean("打开相册", "打开相册"));

        return recyclerListBeans;
    }


    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        String photoPath = getExternalCacheDir() + File.separator + "相机" + DateUtils.getStringByFormat() + ".png";
        switch (recyclerListBean.getTitle()) {
            case "打开相机":
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                if (DeviceUtils.getSDKVersion() >= Build.VERSION_CODES.N) {
                    // 将文件转换成content://Uri的形式
                    Uri photoURI = FileProvider.getUriForFile(activity,
                            activity.getPackageName() + ".provider",
                            new File(photoPath));

                    // 申请临时访问权限
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                } else {
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    Uri uri = Uri.parse("file://" + photoPath);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                activity.startActivityForResult(intent, 10);
                break;
            case "打开相机拍照":
                Intent intent1 = new Intent();
                intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                activity.startActivityForResult(intent1, 11);
                break;

            case "打开相册":
//                Matisse.from(this)
//                        .choose(MimeType.ofImage(), false)
//                        .countable(true)
//                        .capture(true)
//                        .captureStrategy(
//                                new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
//                        .maxSelectable(9)
//                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//                        .thumbnailScale(0.85f)
//                        .imageEngine(new GlideEngine())
//                        .setOnSelectedListener((uriList, pathList) -> {
//                            Log.e("onSelected", "onSelected: pathList=" + pathList);
//                        })
//                        .showSingleMediaType(true)
//                        .originalEnable(true)
//                        .maxOriginalSize(10)
//                        .autoHideToolbarOnSingleTap(true)
//                        .setOnCheckedListener(isChecked -> {
//                            Log.e("isChecked", "onCheck: isChecked=" + isChecked);
//                        })
//                        .forResult(10);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            L.d(data);//空值
        } else if (requestCode == 11) {
//            DataString = content://media/external_primary/images/media/16918
//            data.getDataString();
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");


        }
    }
}