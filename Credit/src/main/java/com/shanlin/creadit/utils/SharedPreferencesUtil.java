/**
 *
 */
package com.shanlin.creadit.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * @author xionglh
 * @version CommonMethod.java 2014年10月5日 下午3:58:01 v1.0.0 xionglihui
 */
public class SharedPreferencesUtil {

    /**
     * 保存本地的文件名
     */
    public static final String SHARE_FILE_NAME = "shanlin_credit";


    /**
     * 通过SharedPreferences保存本地数据
     *
     * @param context
     * @param key
     * @param value
     */
    @SuppressLint("CommitPrefEdits")
    public static void saveStringValue(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public static void saveIntValue(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 通过SharedPreferences获取本地数据
     *
     * @param context
     * @param key
     * @return
     */
    public static String getStringValue(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARE_FILE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }

    public static int getIntValue(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(
                SHARE_FILE_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, 0);
    }

    /**
     * @param context
     * @param keys
     */
    @SuppressLint("CommitPrefEdits")
    public static void cleanStringValue(Context context, String... keys) {
        for (String key : keys) {
            SharedPreferences settings = context.getSharedPreferences(
                    SHARE_FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            if (settings.contains(key)) {
                editor.remove(key).commit();
            }
        }
    }
}
