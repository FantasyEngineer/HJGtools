package com.hjg.hjgtools.activity.camera;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.DateUtils;
import com.hjg.base.util.DeviceUtils;
import com.hjg.base.util.FileUtils;
import com.hjg.base.util.IntentUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;
import com.hjg.hjgtools.view.dialog.ImageViewDialog;
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
                            "com.hjg.hjgtools.fileProvider",
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
                activity.startActivityForResult(IntentUtils.getCaremaIntent(), 11);
                break;

            case "打开相册":
                activity.startActivityForResult(IntentUtils.getAlbumIntent(activity), 12);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            L.d(data);//空值
        } else if (requestCode == 11 && data != null) {//打开相机拍照
//            DataString = content://media/external_primary/images/media/16918
//            data.getDataString();
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");

            ImageViewDialog imageViewDialog = new ImageViewDialog(activity).setImageURL(bitmap);
            imageViewDialog.show();
        } else if (requestCode == 12) {//打开相册
            try {

                Uri uri = data.getData(); //获取系统返回的照片的Uri,glide可以直接加载
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri,
                        filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String path = cursor.getString(columnIndex);  //获取照片路径
                cursor.close();

                //这里的path获取的之后，需要对应的读写权限才能使用图片
                L.d("path---" + path);
                File file = new File(path);
                L.d(file);
                L.d(FileUtils.getFileSize(file));

                ImageViewDialog imageViewDialog = new ImageViewDialog(activity).setImageURL(new File(path));
                imageViewDialog.show();

                Bitmap bitmap = BitmapFactory.decodeFile(path);
                L.d("bitmap---" + bitmap);
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