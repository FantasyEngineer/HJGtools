package com.hjg.hjgtools.view.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.hjg.base.util.ScreenUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.view.flyco.dialog.widget.internal.BaseAlertDialog;

import java.io.File;

/**
 * 图片展示dialog
 */
public class ImageViewDialog extends BaseAlertDialog<ImageViewDialog> {

    private ImageView imageView;
    private Uri uri;
    private String url;
    private Bitmap bitmap;
    private File file;

    public ImageViewDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {

        //内部布局
        imageView = new ImageView(getContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth() - 100, ScreenUtils.getScreenWidth() - 100));
        mLlContainer.addView(imageView);
        mLlContainer.setGravity(Gravity.CENTER);

        return mLlContainer;
    }

    @Override
    public void setUiBeforShow() {
        if (uri != null)
            Glide.with(getContext()).load(uri).into(imageView);
        if (file != null)
            Glide.with(getContext()).load(file).into(imageView);
        if (StrUtil.isNotEmpty(url))
            Glide.with(getContext()).load(url).into(imageView);
        if (bitmap != null)
            Glide.with(getContext()).asBitmap().load(bitmap).into(imageView);
    }


    public ImageViewDialog setImageURL(Uri uri) {
        this.uri = uri;
        return this;
    }

    public ImageViewDialog setImageURL(String url) {
        this.url = url;
        return this;
    }

    public ImageViewDialog setImageURL(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }

    public ImageViewDialog setImageURL(File file) {
        this.file = file;
        return this;
    }


}
