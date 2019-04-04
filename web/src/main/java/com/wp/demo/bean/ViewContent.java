package com.wp.demo.bean;

/**
 * Created by Administrator on 2019/4/3.
 * 该类是由数据库中几张表关联查询后得到的bean结果，相当于对应于一个视图
 */
public class ViewContent {

    private String uname;
    private String content;
    private String pname;
    private Integer contentId;
    private Integer pid;        //商品id

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "ViewContent{" +
                "uname='" + uname + '\'' +
                ", content='" + content + '\'' +
                ", pname='" + pname + '\'' +
                ", contentId=" + contentId +
                ", pid=" + pid +
                '}';
    }
}
