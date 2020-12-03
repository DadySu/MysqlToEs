package com.dadysu.es.dao;

import com.dadysu.es.bean.BinlogTestModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RenterInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BinlogTestModel record);

    int insertSelective(BinlogTestModel record);

    BinlogTestModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BinlogTestModel record);

    int updateByPrimaryKey(BinlogTestModel record);

    List<BinlogTestModel> query();
}
