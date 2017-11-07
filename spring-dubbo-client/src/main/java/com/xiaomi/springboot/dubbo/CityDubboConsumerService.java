package com.xiaomi.springboot.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaomi.springboot.domain.City;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 城市 Dubbo 服务消费者
 *
 * Created by bysocket on 28/02/2017.
 */
@Component
public class CityDubboConsumerService {

    @Reference(version = "1.0.0")
    CityDubboService cityDubboService;

    @Reference(version = "1.0.0")
    AllCityService cityService;

    public void printCity() {
        String cityName="温岭";
        City city = cityDubboService.findCityByName(cityName);
        System.out.println(city.toString());
    }

    public void printCitys() {
        List<City> allCity = cityService.findAllCity();
        System.out.println(allCity.toString());
    }
}
