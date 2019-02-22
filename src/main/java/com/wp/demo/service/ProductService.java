package com.wp.demo.service;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Department;
import com.wp.demo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/11.
 * 控制层调用服务层，服务层调用dao层
 */
@Service
public class ProductService implements IProductService<Commodity,Integer>{

    @Autowired
    ProductMapper productMapper;

    /**
     * 添加一件二手商品
     * @param bean
     * @return
     * @throws Exception
     */
    @Override
    public boolean doCreate(Commodity bean) throws Exception {

        int i = productMapper.doCreate(bean);

        if(i != 0){
            return true;
        }

        return false;
    }

    @Override
    public boolean doUpdate(Commodity bean) throws Exception {
        return false;
    }

    @Override
    public boolean doRemove(Integer id) throws Exception {
        return false;
    }

    @Override
    public Commodity findById(Integer pid) throws Exception {

        Commodity commodity = productMapper.findById(pid);

        return commodity;
    }

    @Override
    public List<Commodity> findAll(String keyword) throws Exception {
        return null;
    }

    /**
     * 查询所有的商品信息
     * @return
     * @throws Exception
     */
    /*public List<Commodity> findAll() throws Exception {

        System.out.println("开始执行findAll方法！！！");
        //直接调用mapper中的方法，进行操作
        List<Commodity> allCommodity = productMapper.getAllCommodity();

        System.out.println("查询集合大小： " + allCommodity.size());


        return allCommodity;
    }*/

    //使用分页插件
    public Page<Commodity> findAll() throws Exception {

        System.out.println("开始执行findAll方法！！！");
        //直接调用mapper中的方法，进行操作
        Page<Commodity> allCommodity = productMapper.getAllCommodity();

        System.out.println("查询集合大小： " + allCommodity.size());


        return allCommodity;
    }

    @Override
    public List<Commodity> findAll(String keyword, int intcurrentPage, int lineSize) throws Exception {
        return null;
    }

    @Override
    public long getAllCount(String keyword) throws Exception {
        return 0;
    }
}
