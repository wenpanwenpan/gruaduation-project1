package com.wp.demo.service;

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

    public List<T> findAll(String keyword, int intcurrentPage, int lineSize)
            throws Exception;

    public long getAllCount(String keyword) throws Exception;
}
