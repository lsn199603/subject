package com.education.subject.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.education.subject.entity.User;
import com.education.subject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhangle
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //public final static String Upload_File_Path = "D:\\upload\\";
    @Autowired
    private UserService userService;

    //上传文件
    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Socket socket = null;
        String text = null;
        String Code_Adress = "127.0.0.1";
        try {
            socket = new Socket(Code_Adress,9912);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[10240];
            String path = "E:\\upload\\after\\" + file.getOriginalFilename();
            outputStream.write(path.getBytes());

            int len = inputStream.read(bytes);
            String str = new String(bytes,0,len);
            text = getURLEncoderString(str);
            System.out.println(text);
        }catch (Exception e){
            e.printStackTrace();
        }



        System.out.println(file);
        String pathString = null;
        if(file!=null) {
            pathString = "E:/upload/after/" +file.getOriginalFilename();
        }

        try {
            File files=new File(pathString);
            //打印查看上传路径
            System.out.println(pathString);
            if(!files.getParentFile().exists()){
                files.getParentFile().mkdirs();
            }
            file.transferTo(files);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "{\"code\":0,\"msg\":\""+text+"\"}";
    }

    //登录
    @PostMapping("/login")
    public boolean login(String username, String password, HttpSession session){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("passward",password);
        map.put("sign",0);
        queryWrapper.allEq(map);
        User user = userService.getOne(queryWrapper);

        if(user!=null){
            session.setAttribute("user",user);
            return true;

        }else{
            return false;
        }

    }

    //修改密码
    @RequestMapping("/update")
    public boolean update(String username,String password,HttpSession session){
        session.invalidate();
        return userService.updatePwd(username,password);
    }

    //退出登录
    @RequestMapping("/outLogin")
    public boolean outLogin(String username, String password, HttpSession session){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("passward",password);
        map.put("sign",0);
        queryWrapper.allEq(map);
        User user = userService.getOne(queryWrapper);
        if(user!=null){
            session.invalidate();
            return true;

        }else{
            return false;
        }

    }
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.replaceAll("\\+", " ").replaceAll("%3A",":").replaceAll("%2C",",")
                .replaceAll("%3B",";")
                .replaceAll("%2F","/")
                .replaceAll("%3D","-");
    }
}
