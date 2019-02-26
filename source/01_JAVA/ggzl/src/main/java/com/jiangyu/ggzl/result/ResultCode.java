package com.jiangyu.ggzl.result;

/**
 * 响应码枚举，参考 HTTP状态码的语义
 * ResultCode
 * @description TODO
 * @author jiangyu
 * @date 2019年2月20日 下午3:36:54
 * @version 1.0.0
 */
public enum ResultCode {
    /** 
     * 成功
     */ 
    SUCCESS(200),
    /** 
     * 失败 
     */ 
    FAIL(400),
    
    /** 
     * 未认证（签名错误）
     */ 
    UNAUTHORIZED(401),
    
    /** 
     * 接口不存在
     */ 
    NOT_FOUND(404),
    
    /** 
     * 服务器内部错误
     */ 
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
    
}
