package com.jiangyu.ggzl.bean;

import java.io.Serializable;
import java.util.Date;

import groovy.transform.ToString;

/**
 * DzsxSxxx
 *
 * @author heyang
 * @version 1.0.0
 * @description 电子审讯-审讯信息实体
 * @date 2019-2-20 10:32
 */
@ToString
public class GbthThxx implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String bh;

    /**
     * 罪犯信息编号
     */
    private String zfxxBh;

    /**
     * 罪犯编号
     */
    private String zfbh;

    /**
     * 罪犯姓名
     */
    private String zfxm;

    /**
     * 谈话时间
     */
    private Date thsj;

    /**
     * 谈话时间时间格式化
     */
    private String thsjf;

    /**
     * 谈话地点
     */
    private String thdd;

    /**
     * 谈话目的
     */
    private String thmd;

    /**
     * 创建人姓名
     */
    private String cjrXm;

    /**
     * 手机imei码
     */
    private String imeiId;

    /**
     * 是否录音
     */
    private String sfly;

    /**
     * 语音识别内容
     */
    private String yyxbnr;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否敏感
     */
    private String sfmg;

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getZfxxBh() {
        return zfxxBh;
    }

    public void setZfxxBh(String zfxxBh) {
        this.zfxxBh = zfxxBh;
    }

    public String getZfbh() {
        return zfbh;
    }

    public void setZfbh(String zfbh) {
        this.zfbh = zfbh;
    }

    public String getZfxm() {
        return zfxm;
    }

    public void setZfxm(String zfxm) {
        this.zfxm = zfxm;
    }

    public Date getThsj() {
        return thsj;
    }

    public void setThsj(Date thsj) {
        this.thsj = thsj;
    }

    public String getThsjf() {
        return thsjf;
    }

    public void setThsjf(String thsjf) {
        this.thsjf = thsjf;
    }

    public String getThdd() {
        return thdd;
    }

    public void setThdd(String thdd) {
        this.thdd = thdd;
    }

    public String getThmd() {
        return thmd;
    }

    public void setThmd(String thmd) {
        this.thmd = thmd;
    }

    public String getCjrXm() {
        return cjrXm;
    }

    public void setCjrXm(String cjrXm) {
        this.cjrXm = cjrXm;
    }

    public String getImeiId() {
        return imeiId;
    }

    public void setImeiId(String imeiId) {
        this.imeiId = imeiId;
    }

    public String getSfly() {
        return sfly;
    }

    public void setSfly(String sfly) {
        this.sfly = sfly;
    }

    public String getYyxbnr() {
        return yyxbnr;
    }

    public void setYyxbnr(String yyxbnr) {
        this.yyxbnr = yyxbnr;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSfmg() {
        return sfmg;
    }

    public void setSfmg(String sfmg) {
        this.sfmg = sfmg;
    }
    public String toString() {
        return "业务主键  [" + this.bh + "]——罪犯主键  [" + this.zfxxBh
            + "]——罪犯编号  ["+this.zfbh + "]——罪犯姓名  [" +this.zfxm
            + "]——谈话地点 ["+this.thdd + "]——谈话目的  [" + this.thmd + "]";
    }
}
