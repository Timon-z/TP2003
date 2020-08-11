package cn.kgc.demo.springboot.spring_demo.controller;

import cn.kgc.demo.springboot.spring_demo.mybatis.entity.User;
import cn.kgc.demo.springboot.spring_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    //显示登录页面的请求方法
    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }
    //处理登录验证的方法
    @PostMapping("/login")
    public String login(String userName, String userPassword, Model model){
        String result = "redirect:/userList";
        boolean flag = false;
        String msg = null;
        try {
            flag = userService.verify(userName,userPassword);
            if(!flag){
                msg = "登陆失败!用户名或密码不正确";
            }
        }catch (Exception e){
            msg = "登陆失败:"+e.getMessage();
        }
        if (!flag){
            model.addAttribute("msg",msg);
            result = "redirect:/loginPage";
        }
        return result;
    }
    //注册页面的方法
    public String registePage(){
        return "register";
    }
    //处理注册功能的方法
    public String registe(){
        String result = "";
        return result;
    }
    //显示用户列表的方法
    @GetMapping("/userList")
    public String userList(Model model){
        List<User> userList = this.userService.searchAll();
        model.addAttribute("userList",userList);
        return "userList";
    }



}
