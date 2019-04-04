package com.wp.demo.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/2/24.
 */
public class Customer {

    //private boolean sex;
    //private Integer age;
    private String uname;
    private String tel;
    //用户账号
    private String account;
    private String password;
    private String address;
    //用户邮编
    private String zipCode;
    //用户id用于与商品表进行关联
    private Integer uid;

    //该用户所上传要出售的商品id集合，用字符串代替，使用时进行拆分
    private String commodities;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCommodities() {
        return commodities;
    }

    public void setCommodities(String commodities) {
        this.commodities = commodities;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "uname='" + uname + '\'' +
                ", tel='" + tel + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", uid=" + uid +
                ", commodities='" + commodities + '\'' +
                '}';
    }
}
