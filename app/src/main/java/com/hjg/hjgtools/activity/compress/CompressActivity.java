package com.hjg.hjgtools.activity.compress;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ImageUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.SizeUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityCompressBinding;

public class CompressActivity extends HJGDatabindingBaseActivity<ActivityCompressBinding> {


    private Bitmap bitmap;

    @Override
    protected int getContentID() {
        return R.layout.activity_compress;
    }

    @Override
    protected void initViewAction() {
        //直接加载从121内存占用变成了186m。
//        databinding.iv.setBackgroundResource(R.mipmap.img_big);


        bitmap = ImageUtils.getBitmap(getResources(), R.mipmap.img_big, SizeUtils.dp2px(100), SizeUtils.dp2px(100));
        databinding.iv.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
    }
}