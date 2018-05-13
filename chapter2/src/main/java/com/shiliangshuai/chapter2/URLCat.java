package com.shiliangshuai.chapter2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class URLCat {
/*    static{
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }*/

    public static void main(String[] args) throws Exception {
        String uri = args[0];
        System.out.println("2 uri = " + uri);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
//        InputStream in = null;
        FSDataInputStream in = null;
        try {
//            in = new URL(args[0]).openStream();
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(0);
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
