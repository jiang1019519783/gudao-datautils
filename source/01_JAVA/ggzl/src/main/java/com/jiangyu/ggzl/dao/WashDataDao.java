package com.jiangyu.ggzl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jiangyu.ggzl.bean.DataBaseInfoVo;

@Repository
public class WashDataDao {
    /**
     * 日志对象
     */
    private Log logger = LogFactory.getLog(WashDataDao.class);
    /**
     * 封装结果数据
     * WashDataDao
     * @description TODO
     * @param rs
     * @return
     * @throws SQLException
     * @author jiangyu
     * @date 2019年2月21日 下午1:42:41
     * @version 1.0.0
     */
    public List<Map<String,Object>> packageData(ResultSet rs) throws SQLException {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map;
        while(rs.next()) {
            map = new HashMap<String, Object>(8);
            map.put("table",rs.getString("table_name"));
            map.put("column",rs.getString("column_name"));
            list.add(map);
        }
        return list;
    }
    
    /**
     * 针对postgresql数据库查询待清洗字段
     * WashDataDao
     * @description TODO
     * @param conn
     * @param dataBaseInfoVo
     * @return
     * @author jiangyu
     * @date 2019年2月21日 下午1:40:10
     * @version 1.0.0
     */
    public List<Map<String,Object>> queryWaitDealTableForPostgreSql(Connection conn, DataBaseInfoVo dataBaseInfoVo) {
        StringBuilder sql = new StringBuilder("SELECT\r\n" + 
            "    column_name,\r\n" + 
            "    table_name,\r\n" + 
            "    data_type,\r\n" + 
            "    character_maximum_length,\r\n" + 
            "    numeric_precision,\r\n" + 
            "    numeric_scale\r\n" + 
            "FROM\r\n" + 
            "    information_schema.COLUMNS");
        sql.append(" where table_schema ~ ? ");
        sql.append(" and column_name ~ ? ");
        if(StringUtils.isNotEmpty(dataBaseInfoVo.getSourceColumn())) {
            sql.append(" and table_name ~ ? ");
        }
        sql.append(" order by table_name");
        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setString(1, dataBaseInfoVo.getSourceSechma());
            ps.setString(2, dataBaseInfoVo.getSourceColumn());
            if(StringUtils.isNotEmpty(dataBaseInfoVo.getSourceColumn())) {
                ps.setString(3, dataBaseInfoVo.getSourceTable());
            }
            //获取查询结果集
            return packageData(ps.executeQuery());
        } catch (SQLException e) {
            logger.error("创建预处理对象失败",e);
            return null;
        }
    }
    /**
     * 清洗数据
     * WashDataDao
     * @description TODO
     * @param conn
     * @param tableName
     * @param columnName
     * @param dataBaseInfoVo
     * @return
     * @author jiangyu
     * @date 2019年2月21日 下午4:19:34
     * @version 1.0.0
     * @throws SQLException 
     */
    public void doCorrectionData(Connection conn,String tableName, String columnName, DataBaseInfoVo dataBaseInfoVo) throws SQLException {
        //更新，并获取影响行数
        logger.info("------------------开始更新"+tableName+"表的"+columnName+"-------------------------------");
        String sql = createUpdateSql(tableName,columnName,dataBaseInfoVo);
        logger.info("------------------sql语句["+sql+"]-------------------------------");
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();
        logger.info("------------------表："+tableName+"字段："+columnName+"-----更新完毕--------------------------");
    }
    /**
     * 创建更新sql
     * WashDataDao
     * @description TODO
     * @param tableName
     * @param columnName
     * @param dataBaseInfoVo
     * @return
     * @author jiangyu
     * @date 2019年2月21日 下午4:25:17
     * @version 1.0.0
     */
    public String createUpdateSql(String tableName, String columnName, DataBaseInfoVo dataBaseInfoVo) {
        StringBuilder sb = new StringBuilder("update ");
        if(StringUtils.isNotEmpty(dataBaseInfoVo.getSourceSechma())) {
            sb.append(dataBaseInfoVo.getSourceSechma()).append(".");
        }
        sb.append(tableName).append(" set ").append(columnName).append(" = temp.").append(dataBaseInfoVo.getNewColumn()).append(" from ");
        if(StringUtils.isNotEmpty(dataBaseInfoVo.getPurposeSechma())) {
            sb.append(dataBaseInfoVo.getPurposeSechma()).append(".");
        }
        sb.append(dataBaseInfoVo.getTempTable()).append(" temp where ");
        if(StringUtils.isNotEmpty(dataBaseInfoVo.getSourceSechma())) {
            sb.append(dataBaseInfoVo.getSourceSechma()).append(".");
        }
        sb.append(tableName).append(".").append(columnName).append(" = temp.").append(dataBaseInfoVo.getOldColumn());
        return sb.toString();
    }

}
