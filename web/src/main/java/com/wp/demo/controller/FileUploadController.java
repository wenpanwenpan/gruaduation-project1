package com.wp.demo.controller;

import com.wp.demo.utils.IPTimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/2/22.
 */
@Controller
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @GetMapping("/test")
    public String index() {
        return "test";
    }


    IPTimeStamp ipTimeStamp = new IPTimeStamp("192.168.1.1");

    @PostMapping("/upload1")
    @ResponseBody
    public Map<String, String> upload1(@RequestParam("photo") MultipartFile file) throws IOException {
        log.info("[文件类型] - [{}]", file.getContentType());
        log.info("[文件名称] - [{}]", file.getOriginalFilename());
        log.info("[文件大小] - [{}]", file.getSize());
        // TODO 将文件写入到指定目录（具体开发中有可能是将文件写入到云存储/或者指定目录通过 Nginx
        // 进行 gzip 压缩和反向代理，此处只是为了演示故将地址写成本地电脑指定目录）
        String fileName = ipTimeStamp.getIPTimeRand() + ".jpg";
        file.transferTo(new File("E:\\temp\\" + fileName));

        Map<String, String> result = new HashMap<>(16);
        result.put("contentType", file.getContentType());
        result.put("fileName", file.getOriginalFilename());
        result.put("fileSize", file.getSize() + "");
        return result;
    }


}
