package com.dadysu.es.dao;

import com.dadysu.es.bean.RenterInfoModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RenterInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RenterInfoModel record);

    int insertSelective(RenterInfoModel record);

    RenterInfoModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RenterInfoModel record);

    int updateByPrimaryKey(RenterInfoModel record);

    List<RenterInfoModel> query();
}
