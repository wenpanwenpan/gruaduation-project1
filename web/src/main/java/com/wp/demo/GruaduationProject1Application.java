package com.wp.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.wp.demo.mapper")
@SpringBootApplication
public class GruaduationProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(GruaduationProject1Application.class, args);
	}

}

