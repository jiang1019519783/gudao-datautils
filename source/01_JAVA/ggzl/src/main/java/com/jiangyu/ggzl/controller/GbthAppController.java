package com.jiangyu.ggzl.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiangyu.ggzl.bean.GbthThxx;
import com.jiangyu.ggzl.service.SyncTalkService;

/**
 * 个别谈话接收
 * GbthAppController
 * @description TODO
 * @author jiangyu
 * @date 2019年3月5日 下午4:20:25
 * @version 1.0.0
 */
@RestController
public class GbthAppController {
    
    private Log logger = LogFactory.getLog(GbthAppController.class);
    
    @Autowired
    private SyncTalkService syncTalkService;
    
    @CrossOrigin
    @RequestMapping(value = "/api/gbth", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Boolean> syncGbth(@RequestBody GbthThxx thxx) {
        syncTalkService.doSyncDataForTalk(thxx);
        return ResponseEntity.ok(true);
    }
}
