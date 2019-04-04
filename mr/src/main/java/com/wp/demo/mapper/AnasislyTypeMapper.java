package com.wp.demo.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Administrator on 2019/3/26.
 */
public class AnasislyTypeMapper extends Mapper<LongWritable,Text,IntWritable,IntWritable> {

    private IntWritable k = new IntWritable();
    private IntWritable v = new IntWritable();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    /**
     * 从指定的文件中读取文件
     * 首先要做数据清洗
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //- 商品编号:::7__商品名称:::二手华为手机__商品所属类别:::6__商品所属卖家:::2

        //:::7____二手华为手机____6____2:::
        String[] fields = value.toString().split(":::");
        if(fields.length == 3){
            String[] split = fields[1].split("____");
            k.set(Integer.parseInt(split[2]));
            v.set(1);
            //将结果值写出
            context.write(k,v);
        }
    }
}
