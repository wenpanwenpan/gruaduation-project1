package com.wp.demo.bean;

/**
 * Created by Administrator on 2019/2/11.
 * 商品类
 */
public class Commodity {

    private String name;
    private double price;
    private Integer pid;        //商品编号
    private String note;        //商品介绍
    private Integer count;      //商品数量
    private String photo ;      //商品照片名
    //private String type;        //商品所属类别
    //private String authorId;      //商品所有者的ID

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", pid=" + pid +
                ", note='" + note + '\'' +
                ", count=" + count +
                ", photo='" + photo + '\'' +
                '}';
    }
}
