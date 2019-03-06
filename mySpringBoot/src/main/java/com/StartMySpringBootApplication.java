package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@MapperScan("com.dao")//对mapper包扫描 或者在dao层每个mapper添加@Mapper
@SpringBootApplication //Spring Boot项目的核心注解，主要目的是开启自动配置
public class StartMySpringBootApplication {
	
	//启动项
	public static void main(String[] args) {
		SpringApplication.run(StartMySpringBootApplication.class, args);
	}
	
}
