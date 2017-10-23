package com.xiaomi.spring.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaolai on 2017/10/23.
 */
@RestController
public class TestController {
    @RequestMapping(value = "/")
    public String test(){
        return "HelloWorld";
    }
}
