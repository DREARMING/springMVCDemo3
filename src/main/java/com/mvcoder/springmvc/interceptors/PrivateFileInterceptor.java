package com.mvcoder.springmvc.interceptors;

import com.mvcoder.springmvc.Config.FileConfig;
import com.sun.xml.internal.ws.encoding.ContentTypeImpl;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 对 /files/ 访问路径的资源进行拦截，返回保存在共有目录的图片
 *
 * Created by mvcoder on 2020/4/16.
 */
public class PrivateFileInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean valid = checkToken();
        if(!valid){
            response.setStatus(404);
            return false;
        }
        String[] paths = request.getRequestURI().split("/files/");
        if(paths.length > 0){
            String filePath = paths[paths.length - 1];
            File file = new File(FileConfig.PRIVATE_FILE_DIR, filePath);
            System.out.println("access file path : " + file.getAbsolutePath());
            if(!file.exists()) {
                response.setStatus(404);
                return false;
            }
            response.setStatus(200);
            //String filename = filePath.substring(filePath.lastIndexOf(File.separator));
            //response.setHeader("content-disposition", "attachment;filename=" + filePath);
            byte[] buffer = new byte[4096];
            int len = -1;
            InputStream is = new FileInputStream(file);
            while ((len = is.read(buffer)) != -1){
                response.getOutputStream().write(buffer, 0, len);
            }
            response.getOutputStream().flush();
            is.close();
            return false;
        }
        response.setStatus(200);
        return false;
    }

    private boolean checkToken() {
        return true;
    }
}
