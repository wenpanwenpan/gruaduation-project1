package com.wp.demo;

import com.wp.demo.bean.Admin;
import com.wp.demo.bean.Commodity;
import com.wp.demo.mapper.AdminLoginMapper;
import com.wp.demo.service.AdminLoginService;
import com.wp.demo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

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

	@Test
	public void test01(){
		ArrayList<String> strings = new ArrayList<>();
		String str1 = "1_2_3_4_5_6_7_8_9_10";
		ArrayList<String> list = new ArrayList<>(Arrays.asList(str1.split("_")));

		System.out.println(list.contains("1"));
		System.out.println("集合长度：" + list.size());

		System.out.println(list);

		list.remove(1);

		System.out.println(list.contains("1"));
	}

	@Test
	public void testDate(){
		//设置商品上传日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Calendar date = Calendar.getInstance();
        Date time = date.getTime();
        String format = sdf.format(time);
        System.out.println(format);
	}

	@Test
	public void test001(){

		AdminLoginService loginService = new AdminLoginService();
		Admin admin = loginService.getUserLoginByAccount("wenpanfeng", "123");
		System.out.println(admin);

	}


}

