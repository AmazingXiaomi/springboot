package com.xiaomi.springboot.service.impl;

import com.xiaomi.springboot.domain.Car;
import com.xiaomi.springboot.repository.CarRepository;
import com.xiaomi.springboot.service.CarService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarServiceImpl implements CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;
    @Override
    public Long saveCar(Car car) {
        Car save = carRepository.save(car);
        return save.getId();
    }

    @Override
    public List<Car> search(int pageNumber, int pageSize, String content) {
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        /*FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery().
                add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", content)),
                ScoreFunctionBuilders.weightFactorFunction(1000)).
                add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", content)),
                ScoreFunctionBuilders.weightFactorFunction(100));
*/
        // Function Score Query
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", content)),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", content)),
                        ScoreFunctionBuilders.weightFactorFunction(100));
        NativeSearchQuery build = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
        LOGGER.info("\n searchCity(): searchContent [" + content + "] \n DSL  = \n " + build.getQuery().toString());
        Page<Car> searchPageResults = carRepository.search(build);
        return searchPageResults.getContent();
    }
}
