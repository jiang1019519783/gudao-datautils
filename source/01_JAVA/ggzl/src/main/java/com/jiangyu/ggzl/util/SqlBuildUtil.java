package com.jiangyu.ggzl.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 构建sql工具类 SqlBuildUtil
 * 
 * @description TODO
 * @author jiangyu
 * @date 2018年11月20日 下午3:46:48
 * @version 1.0.0
 */
public class SqlBuildUtil {
    /**
     * 等于 SqlBuildUtil
     * 
     * @description TODO
     * @param column
     * @param value
     * @param param
     * @return
     * @author jiangyu
     * @date 2018年11月20日 下午3:49:10
     * @version 1.0.0
     */
    public static String buildEqualSql(String column, Object value, String param) {
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            return " and " + column + " = " + param;
        }
        return "";
    }

    /**
     * 大于等于 SqlBuildUtil
     * 
     * @description TODO
     * @param column
     * @param value
     * @param param
     * @return
     * @author jiangyu
     * @date 2018年11月20日 下午3:52:13
     * @version 1.0.0
     */
    public static String buildMoreThanAndEqual(String column, Object value, String param) {
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            return " and " + column + " >= " + param;
        }
        return "";
    }

    /**
     * 大于等于 SqlBuildUtil
     * 
     * @description TODO
     * @param column
     * @param value
     * @param param
     * @return
     * @author jiangyu
     * @date 2018年11月20日 下午3:52:13
     * @version 1.0.0
     */
    public static String buildMoreThanAndEqual(String column, Object value, List<Object> param) {
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            param.add(value);
            return " and " + column + " >= ?";
        }
        return "";
    }

    /**
     * 小于等于 SqlBuildUtil
     * 
     * @description TODO
     * @param column
     * @param value
     * @param param
     * @return
     * @author jiangyu
     * @date 2018年11月20日 下午3:52:13
     * @version 1.0.0
     */
    public static String buildLessThanAndEqual(String column, Object value, String param) {
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            return " and " + column + " <= " + param;
        }
        return "";
    }

    /**
     * 小于等于 SqlBuildUtil
     * 
     * @description TODO
     * @param column
     * @param value
     * @param param
     * @return
     * @author jiangyu
     * @date 2018年11月20日 下午3:52:13
     * @version 1.0.0
     */
    public static String buildLessThanAndEqual(String column, Object value, List<Object> param) {
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            param.add(value);
            return " and " + column + " <= ?";
        }
        return "";
    }

    /**
     * in SqlBuildUtil
     * 
     * @description TODO
     * @param column
     * @param value
     * @param param
     * @return
     * @author jiangyu
     * @date 2018年11月20日 下午3:49:10
     * @version 1.0.0
     */
    public static String buildInSql(String column, Object value, String param) {
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            return " and " + column + " in (" + param + ")";
        }
        return "";
    }

    /**
     * 
     * SqlBuildUtil
     * 
     * @description like sql
     * @param column
     * @param value
     * @param param
     * @return
     * @author fengzhiqiang
     * @date 2018年12月18日 下午5:36:44
     * @version 1.0.6
     */
    public static String buildLikeSql(String column, Object value, String param) {
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            return " and " + column + " like " + param + " || '%' ";
        }
        return "";
    }

    /**
     * 全模糊匹配 SqlBuildUtil
     * 
     * @description TODO
     * @param column
     * @param value
     * @param param
     * @return
     * @author jiangyu
     * @date 2018年12月25日 下午5:52:53
     * @version 1.0.0
     */
    public static String buildAllLikeSql(String column, Object value, String param) {
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            return " and " + column + " like '%' || " + param + " || '%' ";
        }
        return "";
    }

    /**
     * 全模糊匹配 SqlBuildUtil
     * 
     * @description TODO
     * @param column
     * @param value
     * @param param
     * @return
     * @author jiangyu
     * @date 2018年12月25日 下午5:52:53
     * @version 1.0.0
     */
    public static String buildAllLikeSql(String column, Object value, List<Object> param) {
        if (value != null && StringUtils.isNotEmpty(value.toString())) {
            param.add(value);
            return " and " + column + " ~ ? ";
        }
        return "";
    }
}
