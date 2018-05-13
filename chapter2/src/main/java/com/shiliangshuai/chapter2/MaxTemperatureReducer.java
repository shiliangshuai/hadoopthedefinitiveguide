package com.shiliangshuai.chapter2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MaxTemperatureReducer extends Reducer<Text,IntWritable, Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        System.out.println("MaxTemperatureReducer.reduce begin key = " + key + ". values = " + values);
        int maxValue = Integer.MIN_VALUE;
        for(IntWritable value : values){
            maxValue = Math.max(maxValue, value.get());
        }
        context.write(key, new IntWritable(maxValue));
        System.out.println("MaxTemperatureReducer.reduce end");
    }
}
