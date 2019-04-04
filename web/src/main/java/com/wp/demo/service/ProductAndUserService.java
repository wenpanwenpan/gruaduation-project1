package com.wp.demo.service;

import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Customer;
import com.wp.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/22.
 * 处理用户和商品服务，主要处理事务关系
 */
@org.springframework.stereotype.Service
public class ProductAndUserService {

    @Autowired
    protected ProductService productService;

    @Autowired UserService userService;

    @Autowired
    AdminProductService adminProductService;

    /**
     * 使用事务
     * @param customer
     * @param commodity
     * @return
     * @throws Exception
     */
    @Transactional
    public boolean addCommodity(Customer customer, Commodity commodity) throws Exception {

        boolean flag = false;
        //向数据库中保存该商品，必须要先保存该商品，才能在数据库中获取该商品的自增id值，然后保存到用户的商品集合
        flag = productService.doCreate(commodity);

        //取得用户所出售的所有商品信息
        String commodities = customer.getCommodities();

        //如果该用户第一次上传出售商品
        if(commodities == null || "".equals(commodities)){
            //将该用户上传的商品的id保存到对应属性中
            customer.setCommodities(commodity.getPid() + "");
        }else {
            commodities = commodities + "_" + commodity.getPid();
            customer.setCommodities(commodities);
        }
        flag = userService.doUpdate(customer);
        return flag;
    }

    /**
     * 删除一件用户自己的商品
     * @return
     */
    @Transactional
    public Customer removeMyCommodity(Customer customer,String pid) throws Exception {
        Customer customer1 = UserUtils.ModifyUsersCommoditiesUtils(customer, pid, productService, userService);
        return customer1;
    }

    /**
     * 管理员删除一个用户，并且删除该用户所上传的商品,使用事务的原子性
     * @return
     */
    @Transactional
    public boolean removeUserAndCommodities(Customer customer){

        boolean flag = false;
        String commodities = null;
        //数据库中查找到该用户并且该用户上传过商品则删除上传的商品
        if(customer != null){
            commodities = customer.getCommodities();
            if(commodities != null && !"".equals(commodities)){
                String[] pids = commodities.split("_");
                List list = new ArrayList();
                for (int i = 0; i < pids.length; i++) {
                    list.add(pids[i]);
                }
                //删除用户的的同时删除用户所上传的商品
                adminProductService.deleteUsersCommodityByPids(list);
            }
            //在customer表中删除用户的信息
            userService.doRemoveUserById(customer.getUid());
            flag = true;
        }
        return flag;
    }

    /**
     * 管理员删除一件顾客的商品，同时要在顾客表中的commoditis字段进行删除
     * @return
     */
    public boolean removeUsersCommodityById(Customer customer,Integer pid) throws Exception {
        Customer customer1 = UserUtils.ModifyUsersCommoditiesUtils(customer, pid + "", adminProductService,userService);
        if(customer1 != null){
            return true;
        }
        return false;
    }


}
