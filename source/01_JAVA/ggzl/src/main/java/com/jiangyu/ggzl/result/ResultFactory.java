package com.jiangyu.ggzl.result;

/**
 * 响应结果生成工厂类
 * ResultFactory
 * @description TODO
 * @author jiangyu
 * @date 2019年2月20日 下午3:37:26
 * @version 1.0.0
 */
public class ResultFactory {

    public static ResultInfo buildSuccessResult(Object data) {
        return buidResult(ResultCode.SUCCESS, "成功", data);
    }

    public static ResultInfo buildFailResult(String message) {
        return buidResult(ResultCode.FAIL, message, null);
    }

    public static ResultInfo buidResult(ResultCode resultCode, String message, Object data) {
        return buidResult(resultCode.code, message, data);
    }
    
    public static ResultInfo buidResult(int resultCode, String message, Object data) {
        return new ResultInfo(resultCode, message, data);
    }
}

