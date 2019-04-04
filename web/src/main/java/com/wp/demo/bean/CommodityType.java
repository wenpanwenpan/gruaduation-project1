package com.wp.demo.bean;

/**
 * Created by Administrator on 2019/2/24.
 * 商品类别表
 */
public class CommodityType {
    //商品类别ID
    private Integer id;
    //商品类别名称
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "CommodityType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
