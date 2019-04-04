package com.wp.demo.bean;

/**
 * Created by Administrator on 2019/2/24.
 */
public class Admin {

    private Integer sex;
    private Integer age;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

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

    @Override
    public String toString() {
        return "Admin{" +
                "sex=" + sex +
                ", age=" + age +
                ", uname='" + uname + '\'' +
                ", tel='" + tel + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", uid=" + uid +
                '}';
    }
}
