/**
 * @(#)SystemUtils1.0.0 2016/4/1417:26
 * <p/>
 * Copyright © 2016善林金融.  All rights reserved.
 */
package com.shanlin.creadit.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * 系統帮助类
 *
 * @author zhanghao
 * @version $$ Revision:1.0.0, $$ Date: 2016/4/1417:26$$
 */
public class SystemUtils {

    /**
     * 获取折本版本号
     *
     * @return
     */
    public static int getDeviceVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备ID
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
