package com.hjg.hjgtools.activity.file;

import android.os.Bundle;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.util.D;
import com.hjg.base.util.DateUtils;
import com.hjg.base.util.FileUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class FileActivity extends HJGBaseRecyclerMulItemActivity {
    private int tvHeight;
    private int scrollHeight = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvDes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tvHeight = tvDes.getHeight();
                L.d("tvHeight" + tvHeight);
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@androidx.annotation.NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@androidx.annotation.NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                L.d("dx--》" + dx);
                L.d("dy--》" + dy);
                tvDes.post(new Runnable() {
                    @Override
                    public void run() {
                        if (scrollHeight >= tvHeight) {
                            tvDes.setHeight(tvHeight + dy);
                        } else {
                            tvDes.setHeight(tvHeight - dy);
                        }
                    }
                });
            }
        });


    }

    @Override
    protected CharSequence setDesString() {
        return new TextSpanUtils.Builder("<provider\n" +
                "            android:name=\"androidx.core.content.FileProvider\"\n" +
                "            android:authorities=\"${applicationId}.provider\"\n" +
                "            android:exported=\"false\"\n" +
                "            android:grantUriPermissions=\"true\"><!--是否授予临时权限-->\n" +
                "            <meta-data\n" +
                "                android:name=\"android.support.FILE_PROVIDER_PATHS\"\n" +
                "                android:resource=\"@xml/file_paths\" />\n" +
                "</provider>")
                .appendNewLine()
                .append("<paths xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                        "    <files-path\n" +
                        "        name=\"my_images" +
                        "        path=\"images/\" />\n" +
                        "    <cache-path\n" +
                        "        name=\"apkfile" +
                        "        path=\"apkfile/\">\n" +
                        "    </cache-path>\n" +
                        "</paths>").create();
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "系统分区"));
        listBeans.add(new RecyclerListBean("获取内置存储卡cache目录", "不需要读写权限，只需要在cachpath中配置"));
        listBeans.add(new RecyclerListBean("创建cache下的文件", "不需要读写权限"));
        listBeans.add(new RecyclerListBean("读取cache下创建的文件内容", "不需要读写权限，但是在文件管理器中无法查看，必须要求有root权限，在用户目录下才能看到"));
        listBeans.add(new RecyclerListBean("获取内置存储卡file目录", "不需要权限，只需要在filepath中配置"));
        listBeans.add(new RecyclerListBean("创建file下的文件", "不需要权限"));
        listBeans.add(new RecyclerListBean("读取file下创建的文件内容", "不需要读写权限，但是在文件管理器中无法查看，必须要求有root权限，在用户目录下才能看到"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "用户分区"));
        listBeans.add(new RecyclerListBean("获取外置存储卡cache目录", "不需要权限"));
        listBeans.add(new RecyclerListBean("创建外置存储卡cache下的文件", "不需要读写权限"));
        listBeans.add(new RecyclerListBean("读取用户分区下cache下文件", "不需要读写权限，可以在文件管理器中查看到"));

        listBeans.add(new RecyclerListBean("获取用户分区下file目录", "不需要权限"));
        listBeans.add(new RecyclerListBean("创建用户分区下file的文件", "不需要读写权限"));
        listBeans.add(new RecyclerListBean("读取用户分区下file下文件", "不需要读写权限，可以在文件管理器中查看到"));
        listBeans.add(new RecyclerListBean("获取存储根目录", ""));

        return listBeans;
    }

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        switch (recyclerListBean.getTitle()) {

            /*系统分区*/
            /*系统分区*/
            /*系统分区*/
            case "获取内置存储卡cache目录":
                File cache = getCacheDir();
                D.showShort(cache.getAbsolutePath());
                break;
            case "创建cache下的文件":
                File file = new File(getCacheDir() + "/newCache.txt");
                Observable.just(1).map(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Integer integer) {
                        return FileUtils.writeFileFromString(file, "用户分区cache本次加入的时间是：" + DateUtils.getStringByFormat(), true);
                    }
                }).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        D.showShort("cache文件创建" + aBoolean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        D.showShort("cache文件创建" + throwable.getMessage());
                    }
                });
                break;
            case "读取cache下创建的文件内容":
                File file1 = new File(getCacheDir() + "/newCache.txt");
                D.showLong(FileUtils.readFile2String(file1, "UTF-8"));
                break;


            case "获取内置存储卡file目录":
                File filesDir = getFilesDir();
                D.showShort(filesDir.getAbsolutePath());
                break;

            case "创建file下的文件":
                File file2 = new File(getFilesDir() + "/newFile.txt");
                Observable.just(1).map(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Integer integer) {
                        return FileUtils.writeFileFromString(file2, "用户分区file本次加入的时间是：" + DateUtils.getStringByFormat(), true);
                    }
                }).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        D.showShort("file文件创建" + aBoolean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        D.showShort("file文件创建" + throwable.getMessage());
                    }
                });
                break;

            case "读取file下创建的文件内容":
                File file3 = new File(getFilesDir() + "/newFile.txt");
                D.showLong(FileUtils.readFile2String(file3, "UTF-8"));
                break;

            /*用户分区*/
            /*用户分区*/
            /*用户分区*/

            case "获取外置存储卡cache目录":
                File externalCacheDir = getExternalCacheDir();
                D.showShort(externalCacheDir.getAbsolutePath());
                break;

            case "创建外置存储卡cache下的文件":
                File externalCacheDirFile = new File(getExternalCacheDir() + "/newCache.txt");
                Observable.just(1).map(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Integer integer) {
                        return FileUtils.writeFileFromString(externalCacheDirFile, "外置存储卡用户分区cache本次加入的时间是：" + DateUtils.getStringByFormat(), true);
                    }
                }).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        D.showShort("cache文件创建" + aBoolean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        D.showShort("cache文件创建" + throwable.getMessage());
                    }
                });
                break;
            case "读取用户分区下cache下文件":
                File externalCacheDirFile1 = new File(getExternalCacheDir() + "/newCache.txt");
                D.showLong(FileUtils.readFile2String(externalCacheDirFile1, "UTF-8"));
                break;

            case "获取用户分区下file目录":
                File externalFileDir = getExternalFilesDir(null);
                D.showShort(externalFileDir.getAbsolutePath());
                break;
            case "创建用户分区下file的文件":
                //创建一个空文件
//                FileUtils.createFileByDeleteOldFile(getExternalFilesDir(null) + File.separator + "1.txt");

                File externalCacheDirFile2 = new File(getExternalFilesDir(null) + "/newFile.txt");
                Observable.just(1).map(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Integer integer) {
                        return FileUtils.writeFileFromString(externalCacheDirFile2, "外置存储卡用户分区file本次加入的时间是：" + DateUtils.getStringByFormat(), true);
                    }
                }).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        D.showShort("file文件创建" + aBoolean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        D.showShort("file文件创建" + throwable.getMessage());
                    }
                });
                break;
            case "读取用户分区下file下文件":
                File externalCacheDirFile3 = new File(getExternalFilesDir(null) + "/newFile.txt");
                D.showLong(FileUtils.readFile2String(externalCacheDirFile3, "UTF-8"));
                break;
            case "获取存储根目录":
                D.showLong(FileUtils.getExternalStorageRootDir());
                break;


        }
    }

}