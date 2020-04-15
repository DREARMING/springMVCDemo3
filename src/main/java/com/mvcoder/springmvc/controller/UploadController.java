package com.mvcoder.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by mvcoder on 2020/4/15.
 */
@RequestMapping(value = "/upload")
@Controller
public class UploadController {

    //保存在其他目录而不是在项目路径下，避免在升级war包时，被覆盖掉导致丢失数据。。
    //不过这种方式同样导致了无法直接访问文件，放在 webapp 目录下作为静态文件就可以直接访问
    private static final String SAVE_PATH = "D:\\temp\\upload";

    @GetMapping
    public String index(){
        return "upload";
    }


    @PostMapping(value = "/fileSave")
    @ResponseBody
    public String saveFile(HttpServletRequest request, String text, MultipartFile[] files){
        System.out.println("text is : " + text + " , file nums : " + (files == null?0:files.length));
        if(files != null) {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    System.out.println("file name : " + file.getOriginalFilename() + " empty");
                    continue;
                }
                File savePath = new File(SAVE_PATH, file.getOriginalFilename() == null?System.currentTimeMillis()+"":file.getOriginalFilename());
                try {
                    file.transferTo(savePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "200";
    }
}
