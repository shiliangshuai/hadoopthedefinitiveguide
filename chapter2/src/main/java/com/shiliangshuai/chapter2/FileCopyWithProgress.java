package com.shiliangshuai.chapter2;

import com.sun.tools.classfile.ConstantPool;
import org.apache.hadoop.conf.Configuration;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FileCopyWithProgress {
    public static void main(String[] args) throws Exception {
        String localSrc = args[0];
        String dst = args[1];
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst), () -> {
            System.err.print('.');
            System.err.flush();
        });
/*        Progressable progressable = new Progressable() {
            @Override
            public void progress()
            {
                System.out.println(".");
            }
        };*/
        IOUtils.copyBytes(in, out, 4096, true);
    }
}
