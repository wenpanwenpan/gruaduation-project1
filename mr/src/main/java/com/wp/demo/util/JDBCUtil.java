package com.wp.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Administrator on 2019/1/10.
 */
public class JDBCUtil {

    private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    //jdbc:mysql://cMaster:3306/ct-project?userUnicode=true&characterEncoding=UTF-8"
    private static final String MYSQL_URL =
            "jdbc:mysql://cMaster:3306/ct-project?userUnicode=true&characterEncoding=UTF-8";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "wenpan";


    /**
     * 获取mysql的连接对象
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;

        try {
            Class.forName(MYSQL_DRIVER_CLASS);
            conn = DriverManager.getConnection(MYSQL_URL,MYSQL_USERNAME,MYSQL_PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }

        return conn;
    }
}
