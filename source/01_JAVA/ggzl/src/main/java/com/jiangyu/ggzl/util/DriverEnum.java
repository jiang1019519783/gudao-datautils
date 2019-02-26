package com.jiangyu.ggzl.util;

public enum DriverEnum {
    
    POSTGRESQL("postgresql","org.postgresql.Driver"),
    MYSQL("mysql","com.mysql.jdbc.Driver");
    
    private String key;
    
    private String value;
    
    private DriverEnum(String key,String value) {
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}
