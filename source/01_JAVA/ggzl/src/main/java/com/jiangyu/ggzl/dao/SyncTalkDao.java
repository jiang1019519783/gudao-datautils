package com.jiangyu.ggzl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jiangyu.ggzl.bean.GbthThxx;
import com.jiangyu.ggzl.util.CommonUtil;
import com.jiangyu.ggzl.util.YearEnum;
/**
 * 
 * SyncTalkDao
 * @description TODO
 * @author jiangyu
 * @date 2019年3月5日 下午4:04:00
 * @version 1.0.0
 */
@Repository
public class SyncTalkDao {
    
    private Log logger = LogFactory.getLog(SyncTalkDao.class);
    
    /**
     * 获取附件相关数据
     * SyncTalkDao
     * @description TODO
     * @param storage
     * @param thxx
     * @author jiangyu
     * @date 2019年3月5日 下午5:37:13
     * @version 1.0.0
     */
    public List<Map<String,Object>> getDataFromStorage(Connection storage, GbthThxx thxx) {
        StringBuilder sql = new StringBuilder();
        sql.append("select c_bh code,c_ywid objid,c_entity_name filename,'F' as type,'P' status,'gbth' "
            + "businesstype,lc_protocol protocol from db_storage.t_entity_file where c_ywid = ? ");
        //sql.append(" and c_zfxx_bh = ? ");
        sql.append(" and c_ywlb in ('DZSX_SXLY','DZSX_SXLY_CONTENT')");
        try {
            PreparedStatement ps = storage.prepareStatement(sql.toString());
            ps.setString(1, thxx.getBh());
            //ps.setString(2, thxx.getZfxxBh());
            
            ResultSet rs = ps.executeQuery();
            
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            Map<String,Object> map;
            while(rs.next()) {
                map = new HashMap<String, Object>(8);
                map.put("code",rs.getString("code"));
                map.put("objid",rs.getString("objid"));
                map.put("filename",rs.getString("filename"));
                map.put("type",rs.getString("type"));
                map.put("status",rs.getString("status"));
                map.put("businesstype",rs.getString("businesstype"));
                map.put("protocol",rs.getString("protocol"));
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            logger.error("查询附件信息出错",e);
            return null;
        }
    }

    public void saveGbthData(Connection jygz, GbthThxx thxx, List<Map<String, Object>> fjxx) {
        //首先保存主表数据
        try {
            saveGbth(jygz,thxx);
            saveFjxx(jygz,fjxx);
        } catch (SQLException e) {
            logger.error("保存数据失败",e);
        }
    }
    
    private void saveFjxx(Connection jygz, List<Map<String, Object>> fjxx) throws SQLException {
        String sql = null;
        PreparedStatement ps = null;
        for(Map<String,Object> map:fjxx) {
            sql = "insert into db_jygz.syobj(code,objid,filename,type,status,businesstype,protocol,fileid) values (?,?,?,?,?,?,?,?)";
            ps = jygz.prepareStatement(sql);
            ps.setString(1, CommonUtil.analysis32To36(map.get("code").toString()));
            ps.setString(2, CommonUtil.analysis32To36(map.get("objid").toString()));
            ps.setString(3, map.get("filename").toString());
            ps.setString(4, map.get("type").toString());
            ps.setString(5, map.get("status").toString());
            ps.setString(6, map.get("businesstype").toString());
            ps.setString(7, map.get("protocol").toString());
            ps.setString(8, map.get("protocol").toString().substring(map.get("protocol").toString().lastIndexOf("/")+1));
            ps.execute();
        }
    }

    public void saveGbth(Connection jygz, GbthThxx thxx) throws SQLException {
        //通过罪犯编号和罪犯姓名查询罪犯信息
        String sql = "select code from db_jygz.cfiprisoner where coding = ? and name = ? "
            + " and asyoufk in (select asyoufk from db_jygz.asyuserdtl"
            + " where asyuserfk in (?,?))";
        PreparedStatement ps = jygz.prepareStatement(sql);
        ps.setString(1, thxx.getZfbh());
        ps.setString(2, thxx.getZfxm());
        ps.setString(3, "864E9713-1B90-4866-B62E-711AE830AC98");
        ps.setString(4, "D088045B-F61B-48A3-AFA6-AF34A6900139");
        ResultSet rs = ps.executeQuery();
        String zfcode = null;
        while(rs.next()) {
            zfcode = rs.getString("code");
        }
        if(StringUtils.isEmpty(zfcode)) {
            return;
        }
        //通过干警姓名查询干警信息
        sql = "select code,asyoufk from db_jygz.asyuser where name = ? and asyoufk in  (select asyoufk from db_jygz.asyuserdtl "
            + " where asyuserfk in ('864E9713-1B90-4866-B62E-711AE830AC98','D088045B-F61B-48A3-AFA6-AF34A6900139'))";
        ps = jygz.prepareStatement(sql);
        ps.setString(1, thxx.getCjrXm());
        rs = ps.executeQuery();
        String gjcode = null,asyoufk = null;
        while(rs.next()) {
            gjcode = rs.getString("code");
            asyoufk = rs.getString("asyoufk");
        }
        if("陈庆欢".equals(thxx.getCjrXm())) {
            gjcode = "cdfb40fc-35ba-4556-beaf-f3ab1e59dd3c";
            asyoufk = "d3727577-4bb8-43f3-ab44-c3b78e5c63f3";
        }
        if(StringUtils.isEmpty(gjcode)) {
            return;
        }
        //保存数据
        sql = "insert into db_jygz.csptalk(code,cfiprisonerfk,asyperiodfk,asyuserfk,asyoufk,"
            + "locus,txdate,content,reasontxt,djrfk,djdwfk,time,thsj,type,djsj,zflx) "
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'861441D6-FE02-46EC-8429-3D90F300D910')";
        ps = jygz.prepareStatement(sql);
        ps.setString(1, CommonUtil.analysis32To36(thxx.getBh()));
        ps.setString(2, zfcode);
        ps.setString(3, getValueByKey(String.valueOf(getYearFormDate(thxx.getThsj()))));
        ps.setString(4, gjcode);
        ps.setString(5, asyoufk);
        ps.setString(6, thxx.getThdd());
        ps.setDate(7, CommonUtil.dateToSqlDate(thxx.getThsj()));
        ps.setString(8, thxx.getYyxbnr());
        ps.setString(9, thxx.getThmd());
        ps.setString(10, gjcode);
        ps.setString(11, asyoufk);
        ps.setString(12, (getHourFromDate(thxx.getCreateTime())-getHourFromDate(thxx.getThsj()))+
            ":"+(getMinuteFromDate(thxx.getCreateTime())-getMinuteFromDate(thxx.getThsj())));
        ps.setString(13, getHourFromDate(thxx.getThsj())+":"+getMinuteFromDate(thxx.getThsj()));
        ps.setString(14, "B0B2C631-C7F5-4388-8948-C27E488E7C99");
        ps.setDate(15, CommonUtil.dateToSqlDate(new Date()));
        
        ps.execute();
    }
    public int getHourFromDate(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.get(Calendar.HOUR_OF_DAY);
    }
    public int getMinuteFromDate(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.get(Calendar.MINUTE);
    }
    
    public int getYearFormDate(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        return cd.get(Calendar.YEAR);
    }
    
    public String getValueByKey(String key) {
        YearEnum[] arr = YearEnum.values();
        String value = null;
        for(YearEnum ye:arr) {
            if(key.equals(ye.getKey())) {
                value = ye.getValue();
                break;
            }
        }
        return value;
    }
}
