package com.wp.demo.bean;

/**
 * Created by Administrator on 2019/4/2.
 */
public class AnasislyResult {
    //商品类别ID
    private Integer type;
    //商品类别名称
    private String typeName;
    //该类商品的数量
    private Integer count;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AnasislyResult{" +
                "type=" + type +
                ", typeName='" + typeName + '\'' +
                ", count=" + count +
                '}';
    }
}
