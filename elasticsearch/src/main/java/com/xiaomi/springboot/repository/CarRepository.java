package com.xiaomi.springboot.repository;

import com.xiaomi.springboot.domain.Car;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CarRepository extends ElasticsearchRepository<Car,Long> {
}
