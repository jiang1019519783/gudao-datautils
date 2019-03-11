package com.jiangyu.ggzl.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.jiangyu.ggzl.bean.DataBaseInfoVo;
import com.jiangyu.ggzl.util.DriverEnum;

/**
 * 获取数据库连接
 * SqlConnection
 * @description TODO
 * @author jiangyu
 * @date 2019年2月21日 上午9:49:09
 * @version 1.0.0
 */
public class SqlConnection {
    /**
     * 数据库类型
     */
    private String dataBaseType;
    /**
     * 数据库名
     */
    private String dataBaseName;
    /**
     * url
     */
    private String url;
    /**
     * ip
     */
    private String ip;
    /**
     * 端口
     */
    private String port;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    
    public SqlConnection(String dataBaseType,String dataBaseName,String ip,String port,String userName,String password) {
        this.dataBaseType = dataBaseType;
        this.dataBaseName = dataBaseName;
        this.ip = ip;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.url = "jdbc:" + this.dataBaseType + "://" + this.ip + ":" + this.port + "/" + this.dataBaseName;
    }
    
    public SqlConnection(DataBaseInfoVo dataBaseInfoVo) {
        this.dataBaseType = dataBaseInfoVo.getDataBaseType();
        this.dataBaseName = dataBaseInfoVo.getDataBaseName();
        this.ip = dataBaseInfoVo.getIp();
        this.port = dataBaseInfoVo.getPort();
        this.userName = dataBaseInfoVo.getUserName();
        this.password = dataBaseInfoVo.getPassword();
        this.url = "jdbc:" + this.dataBaseType + "://" + this.ip + ":" + this.port + "/" + this.dataBaseName;
    }
    
    public SqlConnection(String url,String username,String password,String dataBaseType) {
        this.url = url;
        this.userName = username;
        this.password = password;
        this.dataBaseType = dataBaseType;
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String driver = getDriver();
        if(StringUtils.isEmpty(driver)) {
            //不支持的数据库
            return null;
        }
        //加载驱动
        Class.forName(driver);
        //连接数据库
        return DriverManager.getConnection(url, userName, password);
    }
    /**
     * 根据数据库类型，返回数据库驱动类
     * SqlConnection
     * @description TODO
     * @param dataBaseType
     * @return
     * @author jiangyu
     * @date 2019年2月21日 上午10:38:42
     * @version 1.0.0
     */
    public String getDriver() {
        DriverEnum[] arr = DriverEnum.values();
        String value = null;
        for(DriverEnum de:arr) {
            if(de.getKey().equals(dataBaseType)) {
                value = de.getValue();
                break;
            }
        }
        return value;
    }
}
