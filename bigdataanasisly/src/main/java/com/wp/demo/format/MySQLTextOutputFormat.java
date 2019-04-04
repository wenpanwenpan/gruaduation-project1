package com.wp.demo.format;

import com.wp.demo.util.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2019/3/26.
 * 将数据写入到mysql
 */
public class MySQLTextOutputFormat extends OutputFormat<IntWritable,IntWritable> {

    protected  static class MySQLRecordWriter extends RecordWriter<IntWritable,IntWritable>{

        private Connection connection = null;

        public MySQLRecordWriter(){
            //获取资源
            connection = JDBCUtil.getConnection();
            try {
                //将表清空一次
                PreparedStatement pstat = null;
                String delSql = "truncate table anasislytype";
                pstat = connection.prepareStatement(delSql);
                pstat.execute();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        /**
         * 输出数据,通过jdbc写入到mysql中
         * @param key    :reduce方法写出的key
         * @param value  :reduce方法写出的value值
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void write(IntWritable key, IntWritable value) throws IOException, InterruptedException {

            PreparedStatement pstat = null;
            try {
                String insertSQL = "insert into anasislytype(type,count) values(?,?)";
                pstat = connection.prepareStatement(insertSQL);

                //取得reduce方法传过来的key
                String type = key.toString();
                String count = value.toString();

                pstat.setInt(1,Integer.parseInt(type));
                pstat.setInt(2,Integer.parseInt(count));

                //执行向数据库插入操作
                pstat.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                if(pstat != null){
                    try {
                        pstat.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        /**
         * 释放资源
         * @param taskAttemptContext
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //@Test
    public void test(){
        MySQLRecordWriter tt = new MySQLRecordWriter();
        Connection con = JDBCUtil.getConnection();
        System.out.println("+++++++++++++" + con);
    }

    @Override
    public RecordWriter<IntWritable, IntWritable> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new MySQLRecordWriter();
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {

    }

    //下面这段代码，摘抄自源码
    private FileOutputCommitter committer = null;
    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get("mapred.output.dir");
        return name == null?null:new Path(name);
    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if(committer == null){
            Path output = getOutputPath(context);
            committer = new FileOutputCommitter(output, context);
        }
        return committer;
    }
}
