package com.wp.demo.bean;

/**
 * Created by Administrator on 2019/4/3.
 * 用户留言bean
 */
public class Content {

    private Integer userId;
    private String updateTime;
    private Integer sellerId;
    private Integer contentId;      //评论id
    private Integer commodityId;    //评论的商品id
    private String content;         //评论的内容

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Content{" +
                "userId=" + userId +
                ", updateTime='" + updateTime + '\'' +
                ", sellerId=" + sellerId +
                ", contentId=" + contentId +
                ", commodityId=" + commodityId +
                ", content='" + content + '\'' +
                '}';
    }
}
