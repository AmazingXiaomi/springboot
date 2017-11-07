package com.xiaomi.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaomi.springboot.dao.CityDao;
import com.xiaomi.springboot.domain.City;
import com.xiaomi.springboot.dubbo.AllCityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service(version = "1.0.0")
public class AllCityServiceImpl implements AllCityService {

    @Autowired
    private CityDao cityDao;

    public List<City> findAllCity(){
        return cityDao.findAllCity();
    }

    public City findCityById(Long id) {
        return cityDao.findById(id);
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        return cityDao.updateCity(city);
    }

    @Override
    public Long deleteCity(Long id) {
        return cityDao.deleteCity(id);
    }

}
