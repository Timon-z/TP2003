package cn.kgc.demo.springboot.spring_demo.service;

import cn.kgc.demo.springboot.spring_demo.mybatis.entity.User;
import cn.kgc.demo.springboot.spring_demo.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean verify(String userName, String userPassword) {
        boolean result = false;
        //根据用户名找用户对象
        User user = userMapper.selectByUserName(userName);
        //如果为null->抛异常
        if (user==null){
            throw new RuntimeException(userName +" 不存在!");
        }else{
            //不为null,比较密码
            result = user.getUserpassword().equals(userPassword);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void register(User user) {
        this.userMapper.insert(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> searchAll() {
        return this.userMapper.selectByExample(null);
    }
}
