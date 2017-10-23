package com.xiaomi.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 * springboot 启动类
 */
//
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

//程序启动入口
        //启动嵌入式的tomcat并初始化spring环境及各个spring组件
        SpringApplication.run(App.class, args);

    }
}
