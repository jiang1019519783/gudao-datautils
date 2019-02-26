package com.jiangyu.ggzl.result;

/**
 * 响应返回数据结果集
 * ResultInfo
 * @description TODO
 * @author jiangyu
 * @date 2019年2月20日 下午3:35:24
 * @version 1.0.0
 */
public class ResultInfo {
    /**
     * 响应状态码
     */
    private int code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应结果对象
     */
    private Object data;

    ResultInfo(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
