package com.dadysu.es.repository;

import com.dadysu.es.bean.RenterInfoModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RenterInfoRepository extends ElasticsearchRepository<RenterInfoModel, Long> {

}
