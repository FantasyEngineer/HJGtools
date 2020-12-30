package com.hjg.base.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.hjg.base.util.log.androidlog.L;

import java.io.File;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * desc  : 设备相关工具类
 */
public class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 获取imei号（需要权限）
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     * <uses-permission android:name="android.permission.READ_PRIVILEGED_PHONE_STATE" />
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        String imei = "";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                imei = tm.getDeviceId();
            } else {
                Method method = tm.getClass().getMethod("getImei");
                imei = (String) method.invoke(tm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }

    /**
     * 判断设备是否root
     *
     * @return the boolean{@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/",
                "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 不同 Sim 卡的序列号不同.
     * Sim 卡序列号，当手机上装有 Sim 卡并且可用时，返回该值。手机未装 Sim 卡或者不可用时，返回 null.
     * 需要 READ_PHONE_STATE 权限。 (Android 6.0 以上需要用户手动赋予该权限)
     * 29以上不允许获取
     *
     * @return Sim 序列号（Sim Serial Number）
     */
    public static String getSimSerialNum() {
        String simSN = "";
        try {
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) HJGUtils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            simSN = tm.getSimSerialNumber();
        } catch (Exception e) {
            simSN = "无法获取SimSerial";
        }
        return simSN;
    }


    /**
     * 获取设备系统版本号
     *
     * @return 设备系统版本号
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 获取设备序列号
     * 需要请求权限PHONE_STATE(目前电视上是能获取到的。但是在手机上获取不到)
     *
     * @return 序列号
     */
    public static String getSerial() {
        String serial = "";
        //通过android.os获取sn号
        try {
            serial = android.os.Build.SERIAL;
            if (!serial.equals("") && !serial.equals("unknown")) return serial;
        } catch (Exception e) {
            serial = "";
        }

        //通过反射获取sn号
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
            if (!serial.equals("") && !serial.equals("unknown")) return serial;

            //9.0及以上无法获取到sn，此方法为补充，能够获取到多数高版本手机 sn
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) serial = Build.getSerial();
        } catch (Exception e) {
            serial = "";
        }
        return serial;
    }


    /**
     * 获取deviceid
     * 当设备为手机时，返回设备的唯一ID。手机制式为 GSM 时，返回手机的 IMEI 。手机制式为 CDMA 时，返回手机的 MEID 或 ESN 。
     * 非电话设备或者 Device ID 不可用时，返回 null .
     * 属于比较稳定的设备标识符。
     * 需要 READ_PHONE_STATE 权限。 (Android 6.0 以上需要用户手动赋予该权限)。
     * 某些设备上该方法存在 Bug ，返回的结果可能是一串0或者一串*号。
     * 自 Android 10（API 级别 29）起，您的应用必须是设备或个人资料所有者应用，具有特殊运营商许可，或具有 READ_PRIVILEGED_PHONE_STATE 特权，才能访问不可重置的设备标识符。
     *
     * @return 设备ID（DeviceId）
     */
    public static String getDeviceId() {
        if (getSDKVersion() >= 29) {
            return "版本过高，没有许可不允许访问不可重置的设备标志符";
        }
        android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) HJGUtils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }


    /**
     * 获取设备AndroidID
     * 在设备第一次启动的时候生成并保存，并且可能会在恢复出厂设置后重置该值。理论上是大部分是重置的。（API 中原话是：The value may change if a factory reset is performed on the device.）
     * 在 Android 2.2 中不可靠.
     * 部分设备由于制造商错误实现，导致会返回相同的 Android_ID.
     * 在 Android 4.2 及以上, 设备启用多用户功能后,每个用户的 Android_ID 不相同.
     *
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        return Settings.Secure.getString(HJGUtils.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return MAC地址
     */
    public static String getMacAddress() {
        String macAddress = getMacAddressByWifiInfo();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        macAddress = getMacAddressByNetworkInterface();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        macAddress = getMacAddressByFile();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        return "please open wifi";
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @return MAC地址
     */
    @SuppressLint("HardwareIds")
    private static String getMacAddressByWifiInfo() {
        try {
            WifiManager wifi = (WifiManager) HJGUtils.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifi != null) {
                WifiInfo info = wifi.getConnectionInfo();
                if (info != null) return info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return MAC地址
     */
    private static String getMacAddressByNetworkInterface() {
        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : nis) {
                if (!ni.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02x:", b));
                    }
                    return res1.deleteCharAt(res1.length() - 1).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备MAC地址
     *
     * @return MAC地址
     */
    private static String getMacAddressByFile() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("getprop wifi.interface", false);
        if (result.result == 0) {
            String name = result.successMsg;
            if (name != null) {
                result = ShellUtils.execCmd("cat /sys/class/net/" + name + "/address", false);
                if (result.result == 0) {
                    if (result.successMsg != null) {
                        return result.successMsg;
                    }
                }
            }
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备厂商
     * <p>如Xiaomi</p>
     *
     * @return 设备厂商
     */

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备型号
     * <p>如MI2SC</p>
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        return model;
    }

    /**
     * 获取设备品牌
     *
     * @return 设备品牌
     */
    public static String getBrand() {
        String brand = Build.BRAND;
        return brand;
    }


    /**
     * 设备名 （Device）
     *
     * @return 设备名
     */
    public static String getDevice() {
        return android.os.Build.DEVICE;
    }


    /**
     * 关机
     * <p>需要root权限或者系统权限 {@code <android:sharedUserId="android.uid.system"/>}</p>
     */
    public static void shutdown() {
        ShellUtils.execCmd("reboot -p", true);
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        HJGUtils.getContext().startActivity(intent);
    }

    /**
     * 重启
     * <p>需要root权限或者系统权限 {@code <android:sharedUserId="android.uid.system"/>}</p>
     */
    public static void reboot() {
        ShellUtils.execCmd("reboot", true);
        Intent intent = new Intent(Intent.ACTION_REBOOT);
        intent.putExtra("nowait", 1);
        intent.putExtra("interval", 1);
        intent.putExtra("window", 0);
        HJGUtils.getContext().sendBroadcast(intent);
    }

    /**
     * 重启
     * <p>需系统权限 {@code <android:sharedUserId="android.uid.system"/>}</p>
     *
     * @param reason 传递给内核来请求特殊的引导模式，如"recovery"
     */
    public static void reboot(String reason) {
        PowerManager mPowerManager = (PowerManager) HJGUtils.getContext().getSystemService(Context.POWER_SERVICE);
        try {
            mPowerManager.reboot(reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重启到recovery
     * <p>需要root权限</p>
     */
    public static void reboot2Recovery() {
        ShellUtils.execCmd("reboot recovery", true);
    }

    /**
     * 重启到bootloader
     * <p>需要root权限</p>
     */
    public static void reboot2Bootloader() {
        ShellUtils.execCmd("reboot bootloader", true);
    }


    /**
     * 输出所有信息
     */
    public static void printAllDeviceinfo() {
        L.d("设备device------>" + getDevice());
        L.d("设备deviceID---->" + getDeviceId());
        L.d("型号------------>" + getModel());
        L.d("品牌------------>" + getBrand());
        L.d("制造商---------->" + getManufacturer());
        L.d("mac地址--------->" + getMacAddress());
        L.d("serial序列号---------->" + getSerial());
        L.d("androidId---------->" + getAndroidID());
        L.d("sim卡SN---------->" + getSimSerialNum());
        L.d("系统版本号---------->" + getSDKVersion());
        L.d("是否root---------->" + isDeviceRooted());
    }
}
