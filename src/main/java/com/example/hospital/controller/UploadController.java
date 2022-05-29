package com.example.hospital.controller;

import com.example.hospital.utils.QiniuUtils;
import com.example.hospital.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping
    public Result upload(@RequestParam MultipartFile file){
        String originalFilename=file.getOriginalFilename();
        if(!originalFilename.endsWith(".png")&&!originalFilename.endsWith(".jpg")){
            return Result.fail(8888,"文件类型不对");
        }
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) {
            System.out.println("http://"+QiniuUtils.url+"/"+fileName);
            return Result.success("http://"+QiniuUtils.url+"/"+fileName);
        }
        return Result.fail(20001,"上传失败");
    }
}
