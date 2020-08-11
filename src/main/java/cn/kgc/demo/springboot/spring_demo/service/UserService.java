package cn.kgc.demo.springboot.spring_demo.service;

import cn.kgc.demo.springboot.spring_demo.mybatis.entity.User;

import java.util.List;

public interface UserService {
    //匹配密码
    public boolean verify(String userName,String userPassword);
    //注册
    public void register(User user);
    //查找所有用户
    public List<User> searchAll();
}
