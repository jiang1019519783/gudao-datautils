package com.jiangyu.ggzl.thread;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jiangyu.ggzl.bean.DataBaseInfoVo;
import com.jiangyu.ggzl.connection.SqlConnection;
import com.jiangyu.ggzl.dao.WashDataDao;

/**
 * 数据矫线程类 SjjzThread
 * 
 * @description TODO
 * @author jiangyu
 * @date 2019年1月19日 下午12:33:07
 * @version 1.0.0
 */
public class SjjzThread implements Runnable {

    private Log logger = LogFactory.getLog(SjjzThread.class);

    private static final String LOCK = "LOCK";

    private String tableName;
    private String columnName;
    private DataBaseInfoVo dataBaseInfoVo;
    private WashDataDao washDataDao;
    private CountDownLatch cdl;

    public SjjzThread(String tableName, String columnName, DataBaseInfoVo dataBaseInfoVo, WashDataDao washDataDao,
        CountDownLatch cdl) {
        this.columnName = columnName;
        this.dataBaseInfoVo = dataBaseInfoVo;
        this.tableName = tableName;
        this.cdl = cdl;
        this.washDataDao = washDataDao;
    }

    @Override
    public void run() {
        try {
            // 首先获取一个数据库连接，一个线程一个，用完后关闭
            Connection conn = new SqlConnection(dataBaseInfoVo).getConnection();
            washDataDao.doCorrectionData(conn, tableName, columnName, dataBaseInfoVo);
            conn.close();
            synchronized (LOCK) {
                cdl.countDown();
            }
        } catch (Exception e) {
            logger.error("表：" + tableName + "字段：" + columnName + "更新失败",e);
        }
    }

}
