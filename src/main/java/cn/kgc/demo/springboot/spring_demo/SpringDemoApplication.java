package cn.kgc.demo.springboot.spring_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//实现接口的动态绑定
@MapperScan(basePackages = "cn.kgc.demo.springboot.spring_demo.mybatis.mapper")
//扫描service
@ComponentScan(basePackages = {"cn.kgc.demo.springboot.spring_demo.service","cn.kgc.demo.springboot.spring_demo.controller","cn.kgc.demo.springboot.spring_demo.config"})
//开启注解事务
@EnableTransactionManagement
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}

