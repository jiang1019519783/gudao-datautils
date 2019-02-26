package com.jiangyu.ggzl.bean;

import javax.validation.constraints.NotNull;

/**
 * 页面输入的对象实体
 * VueLoginInfoVo
 * @description TODO
 * @author jiangyu
 * @date 2019年2月20日 下午3:35:47
 * @version 1.0.0
 */
public class DataBaseInfoVo {
    /**
     * 数据库类型
     */
    private String dataBaseType;
    /**
     * ip
     */
    @NotNull(message="数据库ip不允许为空")
    private String ip;
    /**
     * 端口
     */
    @NotNull(message="数据库端口不允许为空")
    private String port;
    /**
     * 数据库名
     */
    @NotNull(message="数据库名不允许为空")
    private String dataBaseName;
    /**
     * 用户
     */
    @NotNull(message="用户名不允许为空")
    private String userName;
    /**
     * 密码
     */
    @NotNull(message="密码不允许为空")
    private String password;
    /**
     * 源数据库模式
     */
    private String sourceSechma;
    /**
     * 待清洗数据库表名
     */
    private String sourceTable;
    /**
     * 待清洗数据字段
     */
    @NotNull(message="待清洗字段不允许为空")
    private String sourceColumn;
    /**
     * 临时表所在数据库模式名
     */
    private String purposeSechma;
    /**
     * 临时表名
     */
    private String tempTable;
    /**
     * 源值字段
     */
    private String oldColumn;
    /**
     * 目标值字段
     */
    private String newColumn;
    
    public String getDataBaseType() {
        return dataBaseType;
    }
    public void setDataBaseType(String dataBaseType) {
        this.dataBaseType = dataBaseType;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getDataBaseName() {
        return dataBaseName;
    }
    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSourceSechma() {
        return sourceSechma;
    }
    public void setSourceSechma(String sourceSechma) {
        this.sourceSechma = sourceSechma;
    }
    public String getSourceTable() {
        return sourceTable;
    }
    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }
    public String getSourceColumn() {
        return sourceColumn;
    }
    public void setSourceColumn(String sourceColumn) {
        this.sourceColumn = sourceColumn;
    }
    public String getPurposeSechma() {
        return purposeSechma;
    }
    public void setPurposeSechma(String purposeSechma) {
        this.purposeSechma = purposeSechma;
    }
    public String getTempTable() {
        return tempTable;
    }
    public void setTempTable(String tempTable) {
        this.tempTable = tempTable;
    }
    public String getOldColumn() {
        return oldColumn;
    }
    public void setOldColumn(String oldColumn) {
        this.oldColumn = oldColumn;
    }
    public String getNewColumn() {
        return newColumn;
    }
    public void setNewColumn(String newColumn) {
        this.newColumn = newColumn;
    }
}

