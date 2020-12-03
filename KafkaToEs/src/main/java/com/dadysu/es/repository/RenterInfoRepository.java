package com.dadysu.es.repository;

import com.dadysu.es.bean.BinlogTestModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RenterInfoRepository extends ElasticsearchRepository<BinlogTestModel, Long> {

}
