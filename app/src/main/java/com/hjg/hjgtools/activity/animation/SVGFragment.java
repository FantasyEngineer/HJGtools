package com.hjg.hjgtools.activity.animation;

import android.Manifest;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseFragment;
import com.hjg.base.util.AssetUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.FileUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.FragmentSvgBinding;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 *
 */
public class SVGFragment extends HJGDatabindingBaseFragment<FragmentSvgBinding> {

    private SVGAParser svgaParserNet;

    @Override
    protected int getContentID() {
        return R.layout.fragment_svg;
    }

    @Override
    protected void initViewAction(View view) {

        AnimatedVectorDrawable anim1 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.animation_svg_show, null);
        databinding.iv.setImageDrawable(anim1);
        anim1.start();


        AnimatedVectorDrawable anim2 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.animation_svg_encry_show, null);
        databinding.iv2.setImageDrawable(anim2);
        anim2.start();


        /*代码使用加载asset目录下svg动画*/
        SVGAParser svgaParser = new SVGAParser(getActivity());
        svgaParser.parse("Goddess.svga", new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                /*其实就是创建了一个svgdrawable*/
                databinding.svgIv2.setVideoItem(svgaVideoEntity);
                /*执行动画*/
                databinding.svgIv2.startAnimation();
            }

            @Override
            public void onError() {

            }
        });

        /*加载网络下载svg*/
        svgaParserNet = new SVGAParser(getActivity());
        try {
            svgaParserNet.parse(new URL("https://github.com/FantasyEngineer/HJGTools/raw/master/app/src/main/assets/Castle.svga"), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                    L.d("onComplete");
                    /*其实就是创建了一个svgdrawable*/
                    databinding.svgIv3.setVideoItem(svgaVideoEntity);
                    /*执行动画*/
                    databinding.svgIv3.startAnimation();
                }

                @Override
                public void onError() {
                    D.showShort("网络下载失败");

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        /*读取文件流播放*/
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .flatMap(new Function<Boolean, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(@NonNull Boolean aBoolean) throws Exception {
                        /*如果有权限，那么将asset目录下的文件复制到手机根目录下*/
                        if (aBoolean) {
                            InputStream asset = AssetUtils.getAsset("alarm.svga");
                            return Observable.just(FileUtils.writeFileFromIS(FileUtils.getExternalStorageRootDir() + File.separator + "alarm.svga", asset, false));
                        } else {
                            return Observable.just(false);
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                /*true，代表复制到了文件中，那么加载这个文件肯定没有问题了*/
                if (aBoolean) {
                    SVGAParser svgaParserFile = new SVGAParser(getActivity());
                    File file = new File(FileUtils.getExternalStorageRootDir() + File.separator + "alarm.svga");
                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    svgaParserFile.parse(fileInputStream, "123", new SVGAParser.ParseCompletion() {
                        @Override
                        public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                            L.d("onComplete");
                            /*其实就是创建了一个svgdrawable*/
                            databinding.svgIv4.setVideoItem(svgaVideoEntity);
                            /*执行动画*/
                            databinding.svgIv4.startAnimation();
                        }

                        @Override
                        public void onError() {

                        }
                    });
                } else {
                    D.showShort("授予权限失败或者写入文件失败");
                }
            }
        });
    }

}