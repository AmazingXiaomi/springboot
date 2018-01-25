package com.xiaomi.springboot.contoller;

import com.xiaomi.springboot.domain.City;
import com.xiaomi.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/redis")
public class RedisAnnationController {
    @Autowired
    private CityService cityService;

    @GetMapping(value = "getCity")
    @ResponseBody
    public City getCity(String name){
        City cityByName = cityService.getCityByName(name);
        return cityByName;
    }
}
