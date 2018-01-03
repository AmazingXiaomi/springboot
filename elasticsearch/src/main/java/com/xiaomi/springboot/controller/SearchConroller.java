package com.xiaomi.springboot.controller;

import com.xiaomi.springboot.domain.Car;
import com.xiaomi.springboot.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchConroller {
    @Autowired
    private CarService carService;

    @PostMapping(value = "/api/car")
    public Long car(Car car){
        Long aLong = carService.saveCar(car);
        return aLong;
    }

    @GetMapping(value = "/api/car/search")
    public List<Car> search(int pageNumber,int pageSize,String content){
        List<Car> search = carService.search(pageNumber, pageSize, content);
        return search;
    }

}
