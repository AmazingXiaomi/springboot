package com.xiaomi.springboot.repository;

import com.xiaomi.springboot.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CityResitory  extends ElasticsearchRepository<City,Long>{
}
