package com.wp.demo;

import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Department;
import com.wp.demo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@MapperScan(value = "com.wp.demo.mapper")
@RunWith(SpringRunner.class)
@SpringBootTest
public class GruaduationProject1ApplicationTests {

	//必须用自动装配，不能用new，不然service层里面的自动装配就不起作用，会报空指针异常
	@Autowired
	ProductService productService;
	@Test
	public void contextLoads() {

		try {
			List<Commodity> all = productService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

