package com.jiangyu.ggzl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  
@SpringBootApplication 
@EnableCaching
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class StartSpringBoot extends SpringBootServletInitializer{  
	/**
	 * 日志对象
	 */	private static Log logger = LogFactory.getLog(StartSpringBoot.class);
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StartSpringBoot.class);
    }
    @RequestMapping("/hello.do")  
    String hello() {  
        return "<html><body><h2>欢迎使用本系统</h2></body></html>";
    }  
      
    public static void main(String[] args) {
    	logger.info("springboot启动开始···");
        SpringApplication.run(StartSpringBoot.class, args);
        logger.info("springboot启动结束···");
    }  
} 
