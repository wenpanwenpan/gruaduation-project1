package com.wp.demo.service;

import com.github.pagehelper.Page;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.wp.demo.bean.Content;
import com.wp.demo.bean.ViewContent;
import com.wp.demo.mapper.UserContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/3.
 * 处理用户评论的服务类
 */
@Service
public class UserContentService implements IProductService<Content,Integer>{


    @Autowired
    UserContentMapper userContentMapper;

    /**
     * 创建一条评论
     * @param bean
     * @return
     * @throws Exception
     */
    @Override
    public boolean doCreate(Content bean) throws Exception {

        Integer flg = userContentMapper.doCreate(bean);
        if(flg > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean doUpdate(Content bean) throws Exception {
        return false;
    }

    @Override
    public boolean doRemove(Integer id) throws Exception {

        Integer flag = userContentMapper.removeContentById(id);
        if(flag > 0){
            return true;
        }
        return false;
    }

    @Override
    public Content findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Content> findAll(String keyword) throws Exception {
        return null;
    }

    /**
     * 使用分页查询
     * @return
     * @throws Exception
     */
    @Override
    public Page<Content> findAll() throws Exception {
        return null;
    }

    /**
     * 通过用户的id编号，找到所有的评论消息
     * @param id
     * @return
     * @throws Exception
     */
    public Page<ViewContent> findAllById(Integer id) throws Exception {
        Page<ViewContent> allContent = userContentMapper.findAllContent(id);
        return allContent;
    }

    @Override
    public long getAllCount(String keyword) throws Exception {
        return 0;
    }

    public boolean removeAllContent(Integer sellerId){

        Integer flag = userContentMapper.removeAllContent(sellerId);
        if(flag >0){
            return true;
        }
        return false;
    }
}
