package com.wp.demo.service;

import com.github.pagehelper.Page;
import com.wp.demo.bean.Commodity;

import java.util.List;

/**
 * Created by Administrator on 2019/2/11.
 */
public interface IProductService<T,K> {

    public boolean doCreate(T bean) throws Exception;

    public boolean doUpdate(T bean) throws Exception;

    public boolean doRemove(K id) throws Exception;

    public T findById(K id) throws Exception;

    public List<T> findAll(String keyword) throws Exception;

    //返回Page集合，便于分页使用
    public Page<T> findAll() throws Exception;

    public long getAllCount(String keyword) throws Exception;

}
