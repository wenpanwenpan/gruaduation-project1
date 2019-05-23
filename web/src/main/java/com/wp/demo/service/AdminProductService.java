package com.wp.demo.service;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Customer;
import com.wp.demo.mapper.AdminProductMapper;
import com.wp.demo.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/3/11.
 *管理员的管理用户商品和用户信息的服务类
 */
@Service
public class AdminProductService implements IProductService<Commodity,Integer> {

    @Autowired
    AdminProductMapper adminProductMapper;

    @Autowired
    AdminUserMapper adminUserMapper;

    @Override
    public boolean doCreate(Commodity bean) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Commodity bean) throws Exception {
        return false;
    }

    @Override
    public boolean doRemove(Integer id) throws Exception {
        Integer flg = adminProductMapper.removeByPid(id);
        if (flg > 0){
            return true;
        }
        return false;
    }

    @Override
    public Commodity findById(Integer id) throws Exception {
        return null;
    }

    /**
     * 模糊查询
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Commodity> findAll(String keyword) throws Exception {
        return null;
    }

    public Page<Customer> findUserByKeyWord(String keyword){
        Page<Customer> customers = adminProductMapper.findUserByKeyWord(keyword);
        return customers;
    }
    /**
     * 管理所有用户出售的商品，查询得到所有商品
     * @return
     * @throws Exception
     */
    @Override
    public Page<Commodity> findAll() throws Exception {
        Page<Commodity> allCommodity = adminProductMapper.findAllCommodity();
        return allCommodity;
    }

    @Override
    public long getAllCount(String keyword) throws Exception {
        return 0;
    }

    public boolean resetUserPassword(Integer uid){
        Integer integer = adminUserMapper.resetUserPassword(uid);
        return integer > 0;
    }

    /**管理员查看所有用户
     * @return
     */
    public Page<Customer> adminViewAllUser(){
        Page<Customer> allUser = adminUserMapper.findAllUser();

        return allUser;
    }

    public boolean doRemoveUserById(Integer uid){

        Integer integer = adminUserMapper.doRemoveUserById(uid);
        if(integer > 0){
            return true;
        }
        return false;
    }

    public boolean deleteUsersCommodityByPids(List<Integer> pids){
        Integer integer = adminProductMapper.removeUsersAllCommodity(pids);
        if (integer > 0){
            return true;
        }
        return false;
    }
}
