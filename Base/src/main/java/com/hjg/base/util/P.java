package com.hjg.base.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import static com.hjg.base.util.ConstUtils.SHAREPREFERENCENAME;


/**
 * shareferenceUtils
 * 缓存
 */
public class P {
    public static SharedPreferences settings;

    static {
        settings = Utils.getContext().getSharedPreferences(SHAREPREFERENCENAME, Context.MODE_PRIVATE);

    }

    public static boolean contains(String key) {
        return settings.contains(key);
    }

    public static void remove(String key) {
        settings.edit().remove(key).commit();
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, final String defaultValue) {
        return settings.getString(key, defaultValue);
    }

    public static void putString(final String key, final String value) {
        settings.edit().putString(key, value).commit();
    }

    public static boolean getBoolean(final String key, final boolean defaultValue) {
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(final String key) {
        return settings.contains(key);
    }

    public static void putBoolean(final String key, final boolean value) {
        settings.edit().putBoolean(key, value).commit();
    }

    public static void putInt(final String key, final int value) {
        settings.edit().putInt(key, value).commit();
    }

    public static int getInt(final String key, final int defaultValue) {
        return settings.getInt(key, defaultValue);
    }

    public static void putFloat(final String key, final float value) {
        settings.edit().putFloat(key, value).commit();
    }

    public static float getFloat(final String key, final float defaultValue) {
        return settings.getFloat(key, defaultValue);
    }

    public static void putLong(final String key, final long value) {
        settings.edit().putLong(key, value).commit();
    }

    public static long getLong(final String key) {
        return getLong(key, 0);
    }

    public static long getLong(final String key, final long defaultValue) {
        return settings.getLong(key, defaultValue);
    }

    public static void clear(Context context, final SharedPreferences p) {
        final SharedPreferences.Editor editor = p.edit();
        editor.clear();
        editor.commit();
    }

    public static void clear() {
        settings.edit().clear().commit();
    }
}
