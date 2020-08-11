package cn.kgc.demo.springboot.spring_demo.mapper;

import cn.kgc.demo.springboot.spring_demo.SpringDemoApplication;
import cn.kgc.demo.springboot.spring_demo.mybatis.entity.User;
import cn.kgc.demo.springboot.spring_demo.mybatis.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class TsetUserMapper {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectByPrimaryKey(){
        //准备测试数据
        long id = 12;
        //准备期望值
        //z 张晨
        String userCode = "zhangchen";
        String userName = "张晨";
        //获取实际值
        User user = userMapper.selectByPrimaryKey(id);
        String actUserCode = user.getUsercode();
        String actUserName = user.getUsername();
        Assert.assertEquals(userCode,actUserCode);
        Assert.assertEquals(userName,actUserName);
    }

}
