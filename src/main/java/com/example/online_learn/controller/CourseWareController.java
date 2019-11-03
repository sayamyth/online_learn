package com.example.online_learn.controller;

import com.example.online_learn.entity.Course;
import com.example.online_learn.entity.CourseWare;
import com.example.online_learn.entity.CourseWareType;
import com.example.online_learn.entity.Msg;
import com.example.online_learn.service.CourseWareService;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.persistence.criteria.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
public class CourseWareController {
    @Autowired
    private CourseWareService courseWareService;
    /**
     *分页展示
     */
    @RequestMapping("/getCourseWareListWithPage")
    @ResponseBody
    public Page getCourseWareListWithPage(Integer page,Integer limit){
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("courseWareId")));

        Specification<CourseWare> spec = new Specification<CourseWare>() {
            @Override
            public Predicate toPredicate(Root<CourseWare> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        Page<CourseWare> list = courseWareService.getCourseWareListWithPage(spec, pageRequest);
        for (CourseWare courseWare : list) {
            courseWare.setCourseName(courseWare.getCourse().getCourseName());
            courseWare.setCourseId(Long.toString(courseWare.getCourse().getCourseId()));
            courseWare.setCourseWareTypeId(Long.toString(courseWare.getCourseWareType().getCourseWareTypeId()));
            courseWare.setCourseWareTypeName(courseWare.getCourseWareType().getCourseWareTypeName());
        }
        return list;
    }

    /**
     *模糊查询，分页展示
     */
    @RequestMapping("/getCourseWareListByNameWithPage")
    @ResponseBody
    public Page getCourseWareListByNameWithPage(String name,Integer page,Integer limit){
        if (page==null || limit==null ){
            page=0;
            limit=10;
        }
        PageRequest pageRequest = PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("courseWareId")));

        Specification<CourseWare> spec = new Specification<CourseWare>() {
            @Override
            public Predicate toPredicate(Root<CourseWare> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.get("courseWareName");
                Predicate like = criteriaBuilder.like(path.as(String.class), "%" + name + "%");
                return like;
            }
        };
        Page<CourseWare> list = courseWareService.getCourseWareListWithPage(spec, pageRequest);
        for (CourseWare courseWare : list) {
            courseWare.setCourseName(courseWare.getCourse().getCourseName());
            courseWare.setCourseId(Long.toString(courseWare.getCourse().getCourseId()));
            courseWare.setCourseWareTypeId(Long.toString(courseWare.getCourseWareType().getCourseWareTypeId()));
            courseWare.setCourseWareTypeName(courseWare.getCourseWareType().getCourseWareTypeName());
        }
        return list;
    }

    /**
     * 保存新的课件
     */
    @RequestMapping("/addCourseWare")
    @ResponseBody
    public Msg addCourseWare(MultipartFile file,Integer typeid,Integer courseid,HttpServletRequest request, HttpServletResponse response) {
        //设置编码格式
//        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("utf-8");
        //检查上传编码
        boolean multipartContent = ServletFileUpload.isMultipartContent(request);
        System.out.println(multipartContent);

            if (multipartContent) {
                //uuid生成文件夹
                String uuid = UUID.randomUUID().toString();
                //uuid生成新的文件名
                String newFileName = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
                //拼接到项目或者定义好的目录下
                String path = uuid.replace("-", "").substring(0, 16);

                //保存到该目录
                File savePath = new File("D:\\test\\" + path);
                //获取文件名,分割前缀和后缀
                String str[];
                String fileName = file.getOriginalFilename();
                System.out.println(fileName);
                str = fileName.split("\\.");
                System.out.println(str.length);
                System.out.println(str[1]);
                String prefix = str[0];
                String suffix = str[str.length-1];

                if (!savePath.exists()) {
                    savePath.mkdir();
                }
                //上传文件

                try {
                    file.transferTo(new File(savePath, newFileName));
                } catch (Exception e) {
                    return Msg.success().add("msg", "数据异常");
                }

                //保存数据到数据库
                Course course = new Course();
                course.setCourseId(courseid);

                CourseWareType courseWareType = new CourseWareType();
                courseWareType.setCourseWareTypeId(typeid);

                CourseWare courseWare = new CourseWare();
//                courseWare.setCourseWareName(fileName);
                courseWare.setCourseWarePath(savePath+"\\"+newFileName);
                courseWare.setPrefix(prefix);
                courseWare.setSuffix(suffix);
                courseWare.setCourse(course);
                courseWare.setCourseWareType(courseWareType);

                CourseWare ware = courseWareService.saveCourseWare(courseWare);
                if (ware!=null){
                    return Msg.success().add("msg", "保存成功");
                }else {
                    return Msg.success().add("msg", "保存失败");
                }
            } else {
                return Msg.success().add("msg", "数据格式异常");
            }
    }
    /**
     * 修改文件名字
     */
    @RequestMapping("/updateCourseWare")
    @ResponseBody
    public Msg updateCourseWare(String id,String name,Integer courseid,Integer typeid){

        CourseWare one = courseWareService.findOneById(id);

        Course course = new Course();
        course.setCourseId(courseid);

        CourseWareType courseWareType = new CourseWareType();
        courseWareType.setCourseWareTypeId(typeid);

        one.setCourse(course);
        one.setCourseWareType(courseWareType);
        one.setPrefix(name);

        CourseWare ware = courseWareService.saveCourseWare(one);
        if (ware!=null){
            return Msg.success().add("msg", "修改成功");
        }else {
            return Msg.success().add("msg", "修改失败");
        }
    }

    /**
     * 通过id删除课件
     */
    @RequestMapping("/deleteCourseWare")
    @ResponseBody
    public Msg deleteCourseWare(String id,String path){

        String ids[];
        String paths[];
        ids = id.split(",");
        paths = path.split(",");
        System.out.println(id);
        System.out.println(ids.toString());
        System.out.println(ids.length);
        System.out.println(paths.toString());
        int success=0;
        int fail=0;
        for (int i = 0;i < ids.length;i++) {
            System.out.println(ids[i]+"**********"+paths[i]);
            File file = new File(paths[i]);
            boolean delete = file.delete();
            boolean exists = file.exists();
            System.out.println(delete+"*****"+exists);
            if (delete || !exists){
                System.out.println("删除成功");
                int m = courseWareService.deleteCourseWare(ids[i]);
                System.out.println(m);
                if (m>0){
                    success++;
                }else {
                    fail++;
                }
            }else {
                fail++;
            }
        }
        return Msg.success().add("msg","共操作"+ids.length+"个，成功"+success+"个，失败"+fail+"个");
    }

    /**
     * 下载文件
     */
    @RequestMapping("/downLoad1")
    public HttpServletResponse  downLoad(String id,HttpServletRequest request,HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");
        response.addHeader("content-Type","application/octet-stream");

        request.setCharacterEncoding("utf-8");
        CourseWare ware = courseWareService.findOneById(id);
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(ware.getPrefix()+"."+ware.getSuffix(),"utf-8"));//中文乱码

        File file = new File(ware.getCourseWarePath());
        System.out.println(file.length());

        InputStream in = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        byte b[] =new  byte[1024];
        int len = -1;
        while ( (len=in.read(b)) !=-1){
            out.write(b,0,len);
        }
        out.close();
        in.close();
        return response;
    }
    @RequestMapping("/downLoad")
    public HttpServletResponse download(String name,String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

}
