package com.jiangyu.ggzl.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jiangyu.ggzl.bean.GbthThxx;
import com.jiangyu.ggzl.connection.SqlConnection;
import com.jiangyu.ggzl.dao.SyncTalkDao;
import com.jiangyu.ggzl.util.DriverEnum;

/**
 * 同步数据业务逻辑实现类 SyncTalkService
 * 
 * @description TODO
 * @author jiangyu
 * @date 2019年3月5日 下午3:40:44
 * @version 1.0.0
 */
@Service
public class SyncTalkService {
    
    private Log logger = LogFactory.getLog(SyncTalkService.class);
    
    @Autowired
    private SyncTalkDao syncTalkDao;

    @Value("${gbth.storage.datasource.url}")
    private String storage_url;

    @Value("${gbth.storage.datasource.username}")
    private String storage_username;

    @Value("${gbth.storage.datasource.password}")
    private String storage_password;

    @Value("${gbth.jygz.datasource.url}")
    private String jygz_url;

    @Value("${gbth.jygz.datasource.username}")
    private String jygz_username;

    @Value("${gbth.jygz.datasource.password}")
    private String jygz_password;

    public void doSyncDataForTalk(GbthThxx thxx) {
        logger.info("-------------------");
        logger.info("-------------------");
        logger.info(thxx.toString());
        logger.info("-------------------");
        logger.info("-------------------");
        //获取数据库连接
        Connection storage = null ,jygz = null;
        try {
            //第一步，去一站式的附件表中取得当前谈话的相关附件
            storage = new SqlConnection(storage_url, storage_username, storage_password, DriverEnum.POSTGRESQL.getKey().toString()).getConnection();
            List<Map<String,Object>> fjxx = syncTalkDao.getDataFromStorage(storage,thxx);
            //第一步。保存谈话数据和附件数据到教改库中
            jygz = new SqlConnection(jygz_url, jygz_username, jygz_password, DriverEnum.POSTGRESQL.getKey().toString()).getConnection();
            syncTalkDao.saveGbthData(jygz,thxx,fjxx);
        } catch (Exception e) {
            logger.error("执行同步个别谈话数据失败["+e.getMessage()+"]",e);
        }finally {
            try {
                storage.close();
                jygz.close();
            } catch (SQLException e) {
                logger.error("关闭数据库连接失败",e);
            }
            
        }
    }
}
