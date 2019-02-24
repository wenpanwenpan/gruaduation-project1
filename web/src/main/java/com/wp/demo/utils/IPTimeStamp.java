package com.wp.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date ;
import java.util.Random ;

/**
 * 按照日期时间戳生成随机字符串
 */
public class IPTimeStamp {

    private SimpleDateFormat sdf = null ;
    private String ip = null ;
    public IPTimeStamp(){}

    public IPTimeStamp(String ip){
        this.ip = ip ;
    }

    /**
     * 按照日期时间戳拼凑出一个随机字符串
     * @return
     */
    public String getIPTimeRand(){
        StringBuffer buf = new StringBuffer() ;
        if(this.ip != null){
            String s[] = this.ip.split("\\.") ;
            for(int i=0;i<s.length;i++){
                buf.append(this.addZero(s[i],3)) ;
            }
        }
        buf.append(this.getTimeStamp()) ;
        Random r = new Random() ;
        for(int i=0;i<3;i++){
            buf.append(r.nextInt(10)) ;
        }
        return buf.toString() ;
    }

    /**
     * 按照指定的格式取得日期
     * @return
     */
    public String getDate(){
        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS") ;
        return this.sdf.format(new Date()) ;
    }

    /**
     * 得到相应格式的日期
     * @return
     */
    public String getTimeStamp(){
        this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS") ;
        return this.sdf.format(new Date()) ;
    }

    /**
     * 给传入的字符串，按照指定的长度进行添0操作
     * @param str
     * @param len
     * @return
     */
    private String addZero(String str,int len){
        StringBuffer s = new StringBuffer() ;
        s.append(str) ;
        while(s.length() < len){
            s.insert(0,"0") ;
        }
        //System.out.println(s.toString());
        return s.toString() ;
    }

    //测试结果
    public static void main(String args[]){
        IPTimeStamp ipTimeStamp = new IPTimeStamp("192.168.1.1");
        System.out.println(ipTimeStamp.getIPTimeRand()) ;
    }
}
