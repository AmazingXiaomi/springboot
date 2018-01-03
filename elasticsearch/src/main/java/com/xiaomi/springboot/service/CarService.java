package com.xiaomi.springboot.service;

import com.xiaomi.springboot.domain.Car;

import java.util.List;

public interface CarService {

    Long saveCar(Car car);

    List<Car> search(int pageNumber,int pageSize,String content);
}
