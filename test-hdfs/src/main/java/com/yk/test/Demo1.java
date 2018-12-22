package com.yk.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Date;

public class Demo1 {

    private Configuration conf;
    private FileSystem fs;


    @Before
    public void before() throws IOException {
        conf = new Configuration(true);
        fs = FileSystem.get(conf);
    }

    @After
    public void after() throws IOException {
        fs.close();
    }

    @Test
    public void testMkDir() throws IOException {
        boolean flag = fs.mkdirs(new Path("/hello/world"));
        System.out.println(flag?"success":"fail");
    }

    @Test
    public void testLs() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus.getPath().getName());
        }
    }

    @Test
    public void getFileInfo() throws IOException {
        FileStatus fileStatus = fs.getFileStatus(
                new Path("/user/root/mytxt.txt"));

        System.out.println(fileStatus);

        Date date = new Date(fileStatus.getAccessTime());
        System.out.print("create time :");
        System.out.println(date);

        System.out.print("block size :");
        System.out.println(fileStatus.getBlockSize());

        System.out.print("group :");
        System.out.println(fileStatus.getGroup());

        System.out.print("all size : ");
        System.out.println(fileStatus.getLen());

        System.out.print("owner :");
        System.out.println(fileStatus.getOwner());

        System.out.print("replication :");
        System.out.println(fileStatus.getReplication());
    }

    @Test
    public void getFileBlocks() throws IOException {
        FileStatus fileStatus = fs.getFileStatus(new Path("/user/root/hadoop-2.6.5.tar.gz"));
        BlockLocation[] blockLocations = fs.getFileBlockLocations(fileStatus,0,fileStatus.getLen());
        for (BlockLocation blockLocation : blockLocations) {
            System.out.println(blockLocation);
        }
    }

    @Test
    public void testUpload() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("d:/CatJsq v1.9/gui-config.json");
        FSDataOutputStream fsDataOutputStream = fs.create(new Path("/user/root/mytxt.txt"));
        IOUtils.copyBytes(fileInputStream,fsDataOutputStream,conf);
    }

    @Test
    public void testDownload() throws IOException {
        FSDataInputStream fsDataInputStream = fs.open(new Path("/user/root/mytxt.txt"));
        fsDataInputStream.seek(30);
        FileOutputStream fileOutputStream = new FileOutputStream("d:/aa.txt");
        IOUtils.copyBytes(fsDataInputStream,fileOutputStream,1048576);


    }


}
