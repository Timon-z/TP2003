package cn.kgc.demo.springboot.spring_demo.service;

import cn.kgc.demo.springboot.spring_demo.SpringDemoApplication;
import cn.kgc.demo.springboot.spring_demo.mybatis.entity.User;
import cn.kgc.demo.springboot.spring_demo.mybatis.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class TestUserServiceImpl {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testVerify(){
        //测试数据
        String userName = "张华";
        String userPassword = "0000000";

        //期望值
        boolean result = true;
        //获取实际结果
        result = userService.verify(userName,userPassword);
        //断言
        Assert.assertTrue(result);
    }
    @Test
    public void testRegister(){
        User user = new User();
        user.setUsername("阿甘");
        user.setUserpassword("777777");
        user.setUsercode("agan");
        user.setGender(1);
        user.setPhone("18245634774");
        user.setAddress("长沙市芙蓉区");

        this.userService.register(user);
        user = userMapper.selectByUserName("阿甘");
        Assert.assertNotNull(user);

    }
    @Test
    public void testSearchAll(){
        List<User> userList = this.userService.searchAll();
        int size = userList.size();
        boolean flag = size == 27;
        Assert.assertTrue(flag);
    }
}
