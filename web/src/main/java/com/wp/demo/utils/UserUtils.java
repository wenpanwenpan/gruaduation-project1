package com.wp.demo.utils;

import com.wp.demo.bean.Customer;
import com.wp.demo.service.IProductService;
import com.wp.demo.service.IUserService;
import com.wp.demo.service.ProductService;
import com.wp.demo.service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/11.
 */

public class UserUtils {

    /**
     * 处理从用户的commodities字段中移除一件出售的商品id
     * @param customer
     * @param pid
     * @param productService
     * @return  将更改过字段的customer对象返回
     * @throws Exception
     */
    public static  Customer ModifyUsersCommoditiesUtils(Customer customer, String pid,
                                                        IProductService productService,
                                                        IUserService userService) throws Exception {
        boolean isContains = false;
        ArrayList<String> commodities = null;

        if(customer != null){
            commodities = new ArrayList<>(Arrays.asList(customer.getCommodities().split("_")));
            isContains = commodities.contains(pid);
        }

        if (isContains) {

            //将该顾客的customer表中的commodities字段减去被删除的商品的pid
            commodities.remove(pid + "");
            //删除commodity表中的对应pid的数据
            boolean flg = productService.doRemove(Integer.parseInt(pid));

            //使用stringbuffer节省内存开销，减小gc回收次数
            StringBuffer newCommodities = new StringBuffer();
            //将修改后的商品集合更新到用户表的字段中
            for (int i = 0; i < commodities.size(); i++) {
                if (newCommodities.length() == 0) {
                    newCommodities.append(commodities.get(i));
                }else {
                    newCommodities.append("_").append(commodities.get(i));
                }
            }
            customer.setCommodities(newCommodities.toString());
            //向数据库更新用户信息
            userService.doUpdatePersonalInfo(customer);
        }

        return customer;
    }

    /**
     * 判断传入的商品pid是否是用户的商品
     * @return
     */
    public static boolean isUsersCommodity(String pid,Customer customer){
        String commodities = customer.getCommodities();
        if (commodities != null){
            String[] split = commodities.split("_");
            for (int i = 0; i < split.length; i++) {
                if(split[i].equals(pid)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *获取指定格式的日期
     * @return
     */
    public static String dateFormat(String dateFormat){

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        //使用日历类替代以前的Date类
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();     //取得当前时间
        String date = sdf.format(time);

        return date;
    }




}
