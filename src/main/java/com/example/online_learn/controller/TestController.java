package com.example.online_learn.controller;

import com.example.online_learn.dao.ClassDao;
import com.example.online_learn.dao.LoginDao;
import com.example.online_learn.dao.UserDao;
import com.example.online_learn.dao.UserInfoDao;

import com.example.online_learn.service.LoginService;
import com.example.online_learn.service.UserService;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.UUID;

@Controller
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private ClassDao classDao;
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private UserInfoDao userInfoDao;


    @RequestMapping("/test")
    public String test() {
        return "test";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //定义编码格式
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("utf-8");

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        FileOutputStream outputStream = new FileOutputStream(file.getOriginalFilename());
        BufferedOutputStream bf =new  BufferedOutputStream(outputStream);
//        bf.
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        BufferedOutputStream bf = new BufferedOutputStream();
        return "666";
    }

    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //设置编码格式
        //request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("utf-8");
        //检查上传编码
        boolean multipartContent = ServletFileUpload.isMultipartContent(request);
        System.out.println(multipartContent);
//            if (multipartContent) {
//                return "上传方式错误";
//            }
        //uuid生成文件夹
        String uuid = UUID.randomUUID().toString();
        //拼接到项目或者定义好的目录下
        String path = uuid.replace("-", "").substring(0, 16);

        //保存到该目录
        File savePath = new File("D:\\test\\" + path);

        if (!savePath.exists()) {
            savePath.mkdir();
        }
        //执行上传操作
        try {
            file.transferTo(new File(savePath, file.getOriginalFilename()));
            return "上传成功" + savePath + "\\" + file.getOriginalFilename();
        }
        catch (Exception e) {
            return "错误";
        }

    }

    @RequestMapping("/upload1")
    @ResponseBody
    public String upload1(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {


        //设置编码格式
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        //检查上传编码是否是multipart
        boolean multipartContent = ServletFileUpload.isMultipartContent(request);
//        if (multipartContent){
//            return "上传方式错误";
//        }
        //uuid生成文件夹
        String uuid = UUID.randomUUID().toString();
        //拼接到项目或者定义好的目录下
        String path = uuid.replace("-", "").substring(0, 16);

        //保存到该目录
        File savePath = new File("D:\\" + path);

        if (!savePath.exists()) {
            savePath.mkdir();
        }
//        //初始化工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = upload.parseRequest(request);
        System.out.println(fileItems.isEmpty());

        List<FileItem> items = upload.parseRequest(request);
        Iterator<FileItem> iterator = items.iterator();
        while (iterator.hasNext()){
            FileItem item = iterator.next();
            if (item.isFormField()){
                if ((item.getFieldName()).equals("username")){
                    System.out.println(item.getString("utf-8"));
                }
            } else {
                String name = item.getName();
                System.out.println(name);
            }
        }

        return "6666";
    }


}
