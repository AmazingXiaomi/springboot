package com.xiaomi.springboot.controller;

import com.xiaomi.springboot.domain.City;
import com.xiaomi.springboot.service.CityService;
import com.xiaomi.springboot.utils.ErrorEnum;
import com.xiaomi.springboot.utils.GlobleError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 *
 * Created by bysocket on 07/02/2017.
 */
@Controller
public class CityRestController {

    @Autowired
    private CityService cityService;

 /*   @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public List<City> findAllCity() {
        return cityService.findAllCity();
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public void createCity(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.PUT)
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.DELETE)
    public void modifyCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }*/

    @RequestMapping(value = "/api/city/{id}",method = RequestMethod.GET)
    public String findOneCity(Model model, @RequestParam(value="id", required=false, defaultValue="1") Long id) throws GlobleError {
        if (id.equals(1)){
            throw  new GlobleError(ErrorEnum.SUCCESS);
        }
        model.addAttribute("city", cityService.findCityById(id));
        return "city";
    }
    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public String findAllCity(Model model) {
        List<City> cityList = cityService.findAllCity();
        model.addAttribute("cityList",cityList);
        return "cityList";
    }

}
