package com.example.controller;

import com.example.pojo.Result;
import com.example.service.UserService;
import com.example.utils.AliOssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        String filename=UUID.randomUUID().toString()+file.getOriginalFilename();
//        file.transferTo(new File("C:\\Users\\14569\\Desktop\\Spring\\Springboot\\demo3\\files\\"
//                +filename));
        String url= AliOssUtil.uploadFile(file.getInputStream(),filename);
        return Result.success(url);
    }
}
