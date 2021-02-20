package com.hjg.hjgtools.activity.file;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hjg.base.util.D;
import com.hjg.base.util.FileUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.io.File;
import java.util.ArrayList;

public class FileActivity extends HJGBaseRecyclerMulItemActivity {
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


    //
//    private LinearLayoutManager layoutManager;
//
//    private ArrayList dataList = ArrayListUtils.newArrayList();
//    private HashMap<String, Class> classMap;
//
//    @Override
//    protected int getContentID() {
//        return R.layout.activity_main;
//    }
//
//    @Override
//    protected boolean isOpenNetListener() {
//        return false;
//    }
//
//    @Override
//    protected void initViewAction() {
//
//        classMap = MapUtils.newHashMap();
//        classMap.put("弹窗", DialogActivity.class);
//        classMap.put("广播测试", ReceiverActivity.class);
//        classMap.put("通知测试", NotificationActivity.class);
//        classMap.put("加解密", EncryptionActivity.class);
//        classMap.put("控件", EdittextActivity.class);
//        classMap.put("分享功能", EdittextActivity.class);
//
//        Iterator iterable = classMap.entrySet().iterator();
//        while (iterable.hasNext()) {
//            Map.Entry entry = (Map.Entry) iterable.next();
//            dataList.add(entry.getKey());
//        }
//
//        layoutManager = new LinearLayoutManager(this);
//        databinding.recyclerView.setLayoutManager(layoutManager);
//
//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(dataList);
//        recyclerViewAdapter.setOnRvClickListener(this);
//        databinding.recyclerView.addItemDecoration(new MyDividerItemDecoration(this));
//        databinding.recyclerView.setAdapter(recyclerViewAdapter);
//
//
////    @Override
////    protected void onCreate(@Nullable Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//
//
////
////        image = findViewById(R.id.image);
////        textView = findViewById(R.id.textView);
//
////        textView.setText(TextSpanUtils.getBuilder("你好").setBold().setClickSpan(new ClickableSpan() {
////            @Override
////            public void onClick(@NonNull View widget) {
////
////                D.showShort("你好");
////            }
////        }).append("fafjsdjfasf").setClickSpan(new ClickableSpan() {
////            @Override
////            public void onClick(@NonNull View widget) {
////                D.showShort("fafjsdjfasf");
////            }
////        }).create());
//
////        RxPermissions rxPermissions = new RxPermissions(this);
////        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
////            @Override
////            public void accept(Boolean aBoolean) throws Exception {
////                L.d(aBoolean);
////
////
////                L.d(FileUtils.getCachePath(MainActivity.this));
////
////                L.d(FileUtils.createFileByDeleteOldFile(FileUtils.getCachePath(MainActivity.this) + File.separator + "houjiguo/1.txt"));
////                L.d(FileUtils.createFileByDeleteOldFile(FileUtils.getCachePath(MainActivity.this) + File.separator + "hou"));
////                L.d(FileUtils.createOrExistsDir(FileUtils.getCachePath(MainActivity.this) + File.separator + "hou2.txt"));
////
////
////            }
////        });
//
//
////        L.d(StrUtil.idHide("341203199306042438"));
//
////        textView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(MainActivity.this, NetService.class);
////                if (!ServiceUtils.isServiceRunning(MainActivity.this, NetService.class.getName())) {
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////                        D.showShort("开启服务");
////                        startForegroundService(intent);
////                    }
////                }
////
////                finish();
////            }
////        });
//
//
////        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//
////        KeyBoardUtils.setListener(this, new OnSoftKeyBoardChangeListener() {
////            @Override
////            public void keyBoardShow(int height) {
////                L.d("键盘弹起" + height);
////
////                L.d(ActivityUtils.getTopActivity());
////
////            }
////
////            @Override
////            public void keyBoardHide(int height) {
////                L.d("键盘收回" + height);
////            }
////        });
//
////        L.d(DataUtils.isDateyyyyMMdd("1900-00-0"));
////        L.d(DataUtils.isAfter("1900-00-0"));
////
////        L.d(DataUtils.getNowDataString());
////
////
////        L.d(DataUtils.getNowDataString(DataUtils.TYPE_LINE));
////
////
////        L.d(DataUtils.deleteDataStringLine("1900-00-0"));
////
////        L.d(DataUtils.nFormat.format(CalenderUtils.formatyyyyMMdd2Calender("19000101").getTime()));
////
////
////        L.d(DataUtils.addDateLine("19000101"));
//
////        P.putString("nihao", "nnn");
////
////        L.d(P.getString("nihao"));
////
////        D.showShort(P.getString("nihao"));
//////
////        ArrayList list = ArrayListUtils.newArrayList();
////        list.add("1");
////        list.add("1");
////        list.add("2");
////        list.add("3");
////        list.add("1");
////        ArrayList<String> resultList = (ArrayList) ArrayListUtils.deleteSpecialElement(list);
////        for (String o : resultList) {
////            L.d(o);
////        }
//
//
////        L.d(list);
////        Map<Integer, String> map = MapUtils.newHashMap();
////        Map<Integer, String> map = new TreeMap<>();
////
////        //添加元素
////        map.put(20134316, "尚振伟");
////        map.put(20134314, "牛建伟");
////        map.put(20134313, "刘中林");
////        map.put(20134311, "刘     江");
////
////        L.d(map);
////        L.d(MapUtils.getValue(map));
////        L.d(MapUtils.getKey(map));
////
////        String[] arryayString = ArrayUtils.newArrayString(4);
////        arryayString[0] = "0";
////        arryayString[1] = "1";
////        arryayString[2] = "2";
////        arryayString[3] = "3";
////
////        L.d(arryayString);
////
////        List list1 = ArrayUtils.array2List(arryayString);
////        list1.remove(1);
////        list1.add(1000);
////        L.d(list1);
////
////
////        L.d(DateUtils.getYear());
////        L.d(DateUtils.getMonth());
////        L.d(DateUtils.getDay());
////
////
////        L.d(DateUtils.getDaysNum(2018, 2));
////        L.d(DateUtils.getSundays(2018, 3));
////
////
////        L.d(StrUtil.getLenght("1900-00-00"));
////        DeviceUtils.printAllDeviceinfo();
//
//
////        L.d();
//
////        List list1 = null;
//
//
////        L.d(EmptyUtils.isEmpty(list1));
//
//
////        L.d("字符串--" + EmptyUtils.isEmpty(new String()));
//
////        handlerHolder = new HandlerUtils.HandlerHolder(this);
//
//
////        L.d(ImageUtils.bitmap2String(ImageUtils.drawable2Bitmap(ResUtils.getDrawable(R.drawable.ic_launcher))));
//
//
////        L.d(RegexUtils.isEN("fasfasfasdfasdfasd&&&&&"));
////        L.d(RegexUtils.isNum("11111"));
////        L.d(RegexUtils.isEnAndNum("002232fdsjfhajkhfjkAAAA00"));
////        L.d(RegexUtils.is0OrNotFirst0Num("00"));
////        L.d(RegexUtils.isUpperEN("A"));
//    }
//
//    public void open(View view) {
//        ActivityUtils.startActivity(SecondActivity.class);
//
////        ActivityUtils.startActivity(PdfWebViewActivity.class);
////        image.setImageBitmap(ImageUtils.string2bitmap(ImageUtils.bitmap2String(ImageUtils.drawable2Bitmap(ResUtils.getDrawable(R.drawable.ic_launcher)))));
////        handlerHolder.sendEmptyMessage(1);
////        ActivityUtils.INSTANCE.openApp2(this, "com.hjg.locationproject");
////        ActivityUtils.INSTANCE.openAppActivity(this, "com.hjg.locationproject", "com.hjg.locationproject.MainActivity");
////        ActivityUtils.INSTANCE.goExplore(MainActivity.this, "http://www.baidu.com");
//
////        L.d( ActivityUtils.INSTANCE.hasBrowser(MainActivity.this));
//
////        ActivityUtils.INSTANCE.openAlbum(MainActivity.this, 2);
////        ActivityUtils.INSTANCE.callPhone(MainActivity.this, "10086"
////        L.d(NetUtil.getLocalIpAddress(MainActivity.this));
//
////        L.d(NetUtil.isOnline());
////        L.d(NetUtil.isWifiConnected());
////        L.d(NetUtil.isMobileConnected());
////        L.d(ActivityUtils.getLauncherActivity(this, "com.hjg.hjgtools"));
//
////        L.d(ActivityUtils.isActivityAlive(SecondActivity.class));
////        L.d(ActivityUtils.isActivityAlive(MainActivity.class));
//
////        L.d(AppUtils.isInstallApp(MainActivity.this, "com.hjg.hjgtools"));
////        AppUtils.uninstallApp(MainActivity.this, "com.hjg.locationproject");
//
////        FileUtils.createFileByDeleteOldFile()
//
////        FileUtils.getCachePath()
//
//
////        DeviceUtils.printAllDeviceinfo();
//
//    }


    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean("获取内置存储卡缓存目录", "不需要权限，只需要在filepath中配置"));
        return listBeans;
    }

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        switch (recyclerListBean.getTitle()) {


            case "创建内置存储卡缓存目录":
                File cache = getCacheDir();
                D.showShort(cache.getAbsolutePath());
                break;

        }
    }


}