package com.jiangyu.ggzl.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangyu.ggzl.bean.DataBaseInfoVo;
import com.jiangyu.ggzl.result.ResultFactory;
import com.jiangyu.ggzl.result.ResultInfo;
import com.jiangyu.ggzl.service.WashDataService;


@Controller
public class DataBaseController {
    
    @Autowired
    private WashDataService washDataService;

    /**
     * 登录控制器，前后端分离用的不同协议和端口，所以需要加入@CrossOrigin支持跨域。
     * 给VueLoginInfoVo对象加入@Valid注解，并在参数中加入BindingResult来获取错误信息。
     * 在逻辑处理中我们判断BindingResult知否含有错误信息，如果有错误信息，则直接返回错误信息。
     */
    @CrossOrigin
    @RequestMapping(value = "/api/database", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultInfo login(@Valid @RequestBody DataBaseInfoVo dataBaseInfoVo, BindingResult bindingResult) {
        //校验数据是否正确
        if (bindingResult.hasErrors()) {
            String message = String.format("数据录入出错，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }
        //去service层处理具体业务逻辑
        return washDataService.doWash(dataBaseInfoVo);
    }
}
