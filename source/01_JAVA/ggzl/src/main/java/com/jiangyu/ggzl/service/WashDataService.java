package com.jiangyu.ggzl.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangyu.ggzl.bean.DataBaseInfoVo;
import com.jiangyu.ggzl.connection.SqlConnection;
import com.jiangyu.ggzl.dao.WashDataDao;
import com.jiangyu.ggzl.result.ResultFactory;
import com.jiangyu.ggzl.result.ResultInfo;
import com.jiangyu.ggzl.thread.SjjzThread;
import com.jiangyu.ggzl.util.DriverEnum;

/**
 * 数据清洗业务逻辑处理类 WashDataService
 * 
 * @description
 * @author jiangyu
 * @date 2019年2月21日 上午9:44:48
 * @version 1.0.0
 */
@Service
public class WashDataService {
    /**
     * 日志对象
     */
    private Log logger = LogFactory.getLog(WashDataService.class);

    @Autowired
    private WashDataDao washDataDao;

    /**
     * 
     * WashDataService
     * 
     * @description 清洗数据
     * @param dataBaseInfoVo
     * @author jiangyu
     * @date 2019年2月21日 上午9:45:31
     * @version 1.0.0
     */
    public ResultInfo doWash(DataBaseInfoVo dataBaseInfoVo) {
        Connection conn = null;
        try {
            // 第一步，开始数据库连接
            conn = new SqlConnection(dataBaseInfoVo).getConnection();
            // 第二步，查询需要清洗的数据量
            List<Map<String, Object>> list = getWaitDealTable(conn,dataBaseInfoVo);
            // 关闭数据库连接
            conn.close();
            if (CollectionUtils.isEmpty(list)) {
                // 没有需要清洗的数据
                return ResultFactory.buildSuccessResult("没有需要清洗的数据！");
            }
            // 使用线程池来清洗数据
            CountDownLatch cdl = new CountDownLatch(list.size());
            // 对每一个表都做批量更新
            String tableName = null, updateColumn = null;
            SjjzThread st;
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
            for (Map<String, Object> map : list) {
                // 每一个表单独处理，没办法，需要记录每一个表的影响行数，只能在循环中来执行更新操作了。
                tableName = MapUtils.getString(map, "table");
                updateColumn = MapUtils.getString(map, "column");
                st = new SjjzThread(tableName, updateColumn, dataBaseInfoVo, washDataDao, cdl);
                newFixedThreadPool.execute(st);
            }
            try {
                cdl.await();
            } catch (InterruptedException e) {
                return ResultFactory.buildFailResult("runtime error when wash data "
                    + "for table["+tableName+"."+updateColumn+"],{清洗数据失败}");
            }
            return ResultFactory.buildSuccessResult("清洗完成。");
        } catch (ClassNotFoundException e) {
            logger.error("can not found driver[不支持的数据库]", e);
            return ResultFactory.buildFailResult("can not found driver[不支持的数据库]");
        } catch (SQLException e) {
            logger.error("can not connect database[数据库连接失败]", e);
            return ResultFactory.buildFailResult("can not connect database[数据库连接失败]");
        }
    }
    /**
     * 获取需要清洗的表
     * WashDataService
     * @description TODO
     * @param conn
     * @param dataBaseInfoVo
     * @author jiangyu
     * @date 2019年2月21日 下午5:29:44
     * @version 1.0.0
     */
    public List<Map<String, Object>> getWaitDealTable(Connection conn, DataBaseInfoVo dataBaseInfoVo) {
        // 根据不通的数据库来执行不同的脚本
        if (DriverEnum.POSTGRESQL.getKey().equals(dataBaseInfoVo.getDataBaseType())) {
            return washDataDao.queryWaitDealTableForPostgreSql(conn, dataBaseInfoVo);
        }
        return null;
    }
}
