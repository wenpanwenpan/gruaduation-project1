package com.wp.demo.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Administrator on 2019/3/26.
 */
public class AnasislyTypeReducer extends Reducer<IntWritable,IntWritable,IntWritable,IntWritable> {

    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int count = 0;
        IntWritable v = new IntWritable();
        for (IntWritable value : values) {
            count = count + value.get();
        }
        v.set(count);
        context.write(key,v);
    }
}
