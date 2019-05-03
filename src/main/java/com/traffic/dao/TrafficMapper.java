package com.traffic.dao;

import java.util.List;

import com.traffic.bean.Traffic;

public interface TrafficMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Traffic record);

    int insertSelective(Traffic record);

    Traffic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Traffic record);

    int updateByPrimaryKey(Traffic record);

	List<Traffic> findAllTraffic();
}