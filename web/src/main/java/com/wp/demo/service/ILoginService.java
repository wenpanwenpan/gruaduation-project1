package com.wp.demo.service;

import com.wp.demo.bean.Customer;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/3/18.
 */
@Service
public interface ILoginService<T> {

    public T getUserLoginByAccount(String account, String password);

}
