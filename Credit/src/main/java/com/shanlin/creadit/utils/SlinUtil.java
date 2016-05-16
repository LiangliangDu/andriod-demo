/**
 *
 */
package com.shanlin.creadit.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xionglh
 * @version SlinUtil.java Created by xionglh on 2015年4月2日 下午2:20:37 v1.0.3
 */
public class SlinUtil {


    /**
     * 验证是否为手机
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (mobiles.length() < 11)
            return false;
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[0-9])|(14[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 手机显示
     *
     * @param mobile
     * @return
     */
    public static String mobileFilter(String mobile) {
        try {
            return mobile.substring(0, 3) + " **** " + mobile.substring(mobile.length() - 4, mobile.length());
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 电话号码模糊
     *
     * @param tel
     * @return
     */
    public static String telFilter(String tel) {
        try {
            return tel.substring(0, 4) + " **** " + tel.substring(tel.length() - 4, tel.length());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Bigdecimal截取两位小数
     *
     * @param v1 截取值
     * @return 补位后的值
     */
    public static BigDecimal formatScale2(BigDecimal v1) {
        if (v1 == null)
            return new BigDecimal("0");
        return v1.setScale(2, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal formatScale4(BigDecimal v1) {
        if (v1 == null)
            return new BigDecimal("0");
        return v1.setScale(4, BigDecimal.ROUND_DOWN);
    }

    /**
     * 不要小数
     *
     * @param v1
     * @return
     */
    public static BigDecimal formatScale(BigDecimal v1) {
        if (v1 == null)
            return new BigDecimal("0");
        return v1.setScale(0, BigDecimal.ROUND_DOWN);
    }

    public static boolean isCollectionEmpty(Collection<?> c) {
        return (c == null || c.isEmpty());
    }


}
