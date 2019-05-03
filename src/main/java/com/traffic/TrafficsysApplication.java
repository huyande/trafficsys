package com.traffic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"com.traffic.dao"})
public class TrafficsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficsysApplication.class, args);
	}

}
