package com.example.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AliOssUtil {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    private static final String endpoint = "https://oss-cn-beijing.aliyuncs.com";

    private static final String bucketName = "BUCKET";

    public static String uploadFile(InputStream in, String filename) throws Exception {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, access_key_id,access_key_secret);

        String url="";
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filename, in);
            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            url="https://"+bucketName+"."+endpoint.substring(endpoint.lastIndexOf('/')+1)+"/"+filename;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }

    public static void main(String[] args) throws Exception {
        String filename="C:\\Users\\14569\\Desktop\\Spring\\Springboot\\demo3\\files\\144883e2-54f5-4d64-ad53-981abbd227e2timeline.png";
        InputStream in = new FileInputStream(filename);
        uploadFile(in,filename);
    }
}