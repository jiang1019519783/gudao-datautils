package com.jiangyu.ggzl.util;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.postgresql.jdbc2.TimestampUtils;

/**
 * 工具类
 * CommonUtil
 * @description TODO
 * @author jiangyu
 * @date 2019年3月6日 上午9:43:18
 * @version 1.0.0
 */
public class CommonUtil {
    /**
     * 32位转36位
     * CommonUtil
     * @description TODO
     * @param old
     * @return
     * @author jiangyu
     * @date 2019年3月6日 上午9:43:35
     * @version 1.0.0
     */
    public static String analysis32To36(String old) {
        if(StringUtils.isEmpty(old)) {
            return null;
        }
        if(old.trim().length()<32) {
            return old;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(old.substring(0, 8));
        sb.append("-");
        sb.append(old.substring(8,12));
        sb.append("-");
        sb.append(old.substring(12,16));
        sb.append("-");
        sb.append(old.substring(16,20));
        sb.append("-");
        sb.append(old.substring(20));
        return sb.toString();
    }
    /**
     * 
     * CommonUtil
     * @description TODO
     * @param date
     * @return
     * @author jiangyu
     * @date 2019年3月6日 上午11:18:32
     * @version 1.0.0
     */
    public static java.sql.Date dateToSqlDate(Date date) {
        long time = date.getTime();
        java.sql.Date sdate = new java.sql.Date(time);
        return sdate;
    }
}
