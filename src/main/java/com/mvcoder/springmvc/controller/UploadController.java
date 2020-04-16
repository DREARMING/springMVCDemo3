package com.mvcoder.springmvc.controller;

import cn.hutool.json.JSONUtil;
import com.mvcoder.springmvc.Config.FileConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mvcoder on 2020/4/15.
 */
@RequestMapping(value = "/upload")
@Controller
public class UploadController {

    //保存在其他目录而不是在项目路径下，避免在升级war包时，被覆盖掉导致丢失数据。。
    //不过这种方式同样导致了无法直接访问文件，放在 webapp 目录下作为静态文件就可以直接访问

    @GetMapping
    public String index(){
        return "upload";
    }


    @PostMapping(value = "/fileSave")
    @ResponseBody
    public String saveFile(HttpServletRequest request, String text, MultipartFile[] files) throws UnsupportedEncodingException {
        System.out.println("text is : " + text + " , file nums : " + (files == null?0:files.length));
        List<String> fileList = new ArrayList<>();
        if(files != null) {
            for (MultipartFile file : files) {
                if (file.isEmpty() || file.getOriginalFilename() == null) {
                    System.out.println("file name : " + file.getOriginalFilename() + " empty");
                    continue;
                }
                String filename = file.getOriginalFilename();
                //String name = filename.substring(0, filename.lastIndexOf("."));
                String suffix = filename.substring(filename.lastIndexOf("."));
                String trueName = UUID.randomUUID().toString();
                File savePath = new File(FileConfig.PRIVATE_FILE_DIR, trueName + suffix);
                try {
                    file.transferTo(savePath);
                    String url = "http:" + request.getServerName() + ":" + request.getServerPort() + "/files/" + trueName + suffix;
                    fileList.add(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSONUtil.toJsonStr(fileList);
    }
}
