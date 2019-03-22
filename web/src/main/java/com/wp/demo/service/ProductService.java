package com.wp.demo.service;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.CommodityType;
import com.wp.demo.bean.Customer;
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

    @Autowired
    UserService userService;

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
        Integer integer = productMapper.doUpdate(bean);
        if(integer > 0){
            return true;
        }
        return false;
    }

    /**
     * 通过商品id来删除一件商品
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean doRemove(Integer id) throws Exception {

        Integer flg = productMapper.removeByPid(id);
        if (flg > 0){
            return true;
        }
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
    public long getAllCount(String keyword) throws Exception {
        return 0;
    }

    /**
     * 从数据库中获取商品类型
     * @return
     */
    public List<CommodityType> findAllCommodityType(){

        List<CommodityType> allCommodityType = productMapper.findAllCommodityType();

        return allCommodityType;
    }

    /**
     * 按条件进行模糊查询商品
     * @param key
     * @return
     */
    public Page<Commodity> seachCommodity(String key){

        Page<Commodity> commodities = productMapper.seachCommodity(key);
        return commodities;
    }

    /**
     * 查找到用户所有已经出售的商品
     * @return
     */
    public Page<Commodity> manageMySell(Integer uid){

        //先通过用户的uid查找到该用户的commodities属性
        Customer customer = userService.findCustomerById(uid);
        //如果存在该用户并且他有上传过商品
        if(customer != null && customer.getCommodities() != null && !"".equals(customer.getCommodities())){
            ArrayList<Integer> pids = new ArrayList<>();
            //取得该用户上传出售的商品的所有id编号
            String[] commodities = customer.getCommodities().split("_");

            for (int i = 0; i < commodities.length; i++) {
                pids.add(Integer.parseInt(commodities[i]));
            }

            Page<Commodity> myCommodities = productMapper.findMySellByPids(pids);

            System.out.println("用户所上传的商品件数： " + myCommodities.size());

            return myCommodities;
        }

        return null;
    }

}
