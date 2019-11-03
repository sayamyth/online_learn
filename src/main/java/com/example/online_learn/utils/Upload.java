package com.example.online_learn.utils;

import com.example.online_learn.entity.Msg;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.List;
import java.util.UUID;

public class Upload {

    public Msg upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置编码格式
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        //检查上传编码是否是multipart
        boolean multipartContent = ServletFileUpload.isMultipartContent(request);
        if (multipartContent){
            return Msg.success().add("msg","上传方式错误");
        }
        //uuid生成文件夹
        String uuid = UUID.randomUUID().toString();
        //拼接到项目或者定义好的目录下
        String path = uuid.replace("-","").substring(0,16);

        //保存到该目录
        File savePath =new File("D:\\"+path);

        if (!savePath.exists()){
            savePath.mkdir();
        }
        //执行上传操作

//        file.transferTo(new File(savePath,file.getOriginalFilename()));


        //判断该路径是否存在

//        //初始化工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = upload.parseRequest((RequestContext) request);
        for (FileItem item : items) {
            item.getName();
        }
        return Msg.success();
    }

}
