package com.hjg.hjgtools.activity.camera;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.CameraUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.DateUtils;
import com.hjg.base.util.DeviceUtils;
import com.hjg.base.util.FileUtils;
import com.hjg.base.util.IntentUtils;
import com.hjg.base.util.TimeUtils;
import com.hjg.base.util.UriUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.base.util.log.utils.Utils;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;
import com.hjg.hjgtools.view.dialog.ImageViewDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import io.reactivex.functions.Consumer;

/**
 * 相机操作,相册页面
 */
public class CameraActivity extends HJGBaseRecyclerMulItemActivity {

    //用于保存拍照图片的uri
    private Uri mCameraUri;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RxPermissions(this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
                    D.showShort("权限获取失败，可能某些数据取不到");
                }
            }
        });
    }

    @Override
    protected CharSequence setDesString() {
        return "当版本大于29时，即使你申请了动态权限，也是不能操作除了自身沙盒之外的文件夹，需要在androidmanifest.xml的application的标签下加入requestLegacyExternalStorage=true。29以下的默认加了";
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("打开相机", "打开相机,并且保存拍照照片到相应文件位置,需要适配低版本,但是onActivityResult返回的data是空值"));
        recyclerListBeans.add(new RecyclerListBean("打开相册", "打开相册"));

        return recyclerListBeans;
    }


    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        switch (recyclerListBean.getTitle()) {
            case "打开相机":
                String path = FileUtils.getExternalStorageRootDir() + "/" + TimeUtils.getNowLongMillis() + ".jpg";
                mCameraUri = CameraUtils.openCamera(activity, path, 10);
                break;
            case "打开相册":
                activity.startActivityForResult(IntentUtils.getAlbumIntent(activity), 12);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*必须是请求码与结果码等同*/
        if (requestCode == 10 && resultCode == RESULT_OK) {
//            String path = UriUtils.getFilePathByUri(mCameraUri);
            ImageViewDialog imageViewDialog = new ImageViewDialog(activity).setImageURL(mCameraUri);
            imageViewDialog.show();
        } else if (requestCode == 11 && data != null) {//打开相机拍照
//            DataString = content://media/external_primary/images/media/16918
//            data.getDataString();
            //使用bitmap,这里的bitmap不清晰
//            Bundle bundle = data.getExtras();
//            Bitmap bitmap = (Bitmap) bundle.get("data");
//            ImageViewDialog imageViewDialog = new ImageViewDialog(activity).setImageURL(bitmap);

//            使用uri
            Uri uri = data.getData(); //获取系统返回的照片的Uri,glide可以直接加载
            String path = UriUtils.getFilePathByUri(uri);
            L.d("path--" + path);

            ImageViewDialog imageViewDialog = new ImageViewDialog(activity).setImageURL(uri);
            imageViewDialog.show();

        } else if (requestCode == 12 && resultCode == RESULT_OK) {//打开相册
            try {
                Uri uri = data.getData(); //获取系统返回的照片的Uri,glide可以直接加载
                String path = UriUtils.getFilePathByUri(uri);

                //这里的path获取的之后，需要对应的读写权限才能使用图片
                L.d("path---" + path);
//                File file = new File(path);
                //使用文件打开
                ImageViewDialog imageViewDialog = new ImageViewDialog(activity).setImageURL(uri);
                imageViewDialog.show();

                //直接使用uri
//                ImageViewDialog imageViewDialog = new ImageViewDialog(activity).setImageURL(uri);
//                imageViewDialog.show();

                //使用bitmap
//                Bitmap bitmap = BitmapFactory.decodeFile(path);
//                L.d("bitmap---" + bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            Bundle bundle = data.getExtras();
//            Bitmap bitmap = (Bitmap) bundle.get("data");
//
//            ImageViewDialog imageViewDialog = new ImageViewDialog(activity).setImageURL(bitmap);
//            imageViewDialog.show();
        }
    }
}