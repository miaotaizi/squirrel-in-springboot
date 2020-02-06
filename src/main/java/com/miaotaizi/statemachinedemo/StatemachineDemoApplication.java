package com.miaotaizi.statemachinedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.miaotaizi.statemachinedemo.mapper")
public class StatemachineDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatemachineDemoApplication.class, args);
	}

}
