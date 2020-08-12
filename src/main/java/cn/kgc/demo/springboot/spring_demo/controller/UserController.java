package cn.kgc.demo.springboot.spring_demo.controller;

import cn.kgc.demo.springboot.spring_demo.mybatis.entity.User;
import cn.kgc.demo.springboot.spring_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("myRedisTemplate") //@Resource
    private RedisTemplate<String,Object> redisTemplate;

    //显示登录页面的请求方法
    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }
    //处理登录验证的方法
    @PostMapping("/login")
    public String login(String userName, String userPassword, Model model,HttpSession session){
        String result = "redirect:/userList";
        boolean flag = false;
        String msg = null;
        try {
            flag = userService.verify(userName,userPassword);
            if(!flag){
                msg = "登陆失败!用户名或密码不正确";
            }else{
                //在Session中保存当前的用户名
                session.setAttribute("userName",userName);
                //王Redis中添加登录标记
                this.redisTemplate.opsForValue().set(userName,"hasLogin");
                //有bug
                this.redisTemplate.expire(userName,1000, TimeUnit.SECONDS);
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
    public String registe(User user,Model model){
        String result = "redirect:/loginPage";
        String msg = null;
        try{
            this.userService.register(user);
        }catch (Exception e){
            msg = "注册失败:"+e.getMessage();
            model.addAttribute("msg",msg);
            result = "redirect:/registePage";
        }
        return result;
    }
    //显示用户列表的方法
    @GetMapping("/userList")
    public String userList(Model model, HttpSession session){
        String result = "userList";
        //判断是否有登录,如果没有登录,转向到login页面,有登陆就继续跑
        //知道哪个
        try {
            String userName = (String) session.getAttribute("userName");
            Object loginFlag = this.redisTemplate.opsForValue().get(userName);
            if (loginFlag == null) {
                result = "redirect:/loginPage";
            } else {
                List<User> userList = this.userService.searchAll();
                for (User u : userList) {
                    System.out.println(u.getUsername());
                }
                model.addAttribute("userList", userList);
                model.addAttribute("welcome", "Welcome......");
            }
        }catch (Exception e){
            /*System.out.println(e.getMessage());*/
            result = "redirect:/loginPage";
        }
        return result;
    }



}
