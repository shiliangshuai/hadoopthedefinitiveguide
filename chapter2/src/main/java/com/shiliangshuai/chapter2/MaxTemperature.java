package com.shiliangshuai.chapter2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxTemperature {
    public static void main(String[] args) throws Exception{
        if(args.length != 2)
        {
            System.err.println("Uage: MaxTemperature <input path> <output path>");
            System.exit(-1);
        }
        System.out.println("MaxTemperature.main begin");
//        Job job = new Job();
        Job job = Job.getInstance();
        job.setJarByClass(MaxTemperature.class);
        job.setJobName("Max Temperature");
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.out.println("MaxTemperature.main before waitForCompletion.");
        System.exit(job.waitForCompletion(true) ? 0 : 1);
        System.out.println("MaxTemperature.main end");
    }
}