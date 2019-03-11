package com.jiangyu.ggzl.util;

public enum YearEnum {
    
    Z017("2017","7D6AC285-0162-44CD-E040-007F01000662"),
    Z018("2018","7D6AC285-0163-44CD-E040-007F01000662"),
    Z019("2019","7D6AC285-0164-44CD-E040-007F01000662"),
    Z020("2020","7D6AC285-0165-44CD-E040-007F01000662");
    
    private String key;
    
    private String value;
    
    private YearEnum(String key,String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
