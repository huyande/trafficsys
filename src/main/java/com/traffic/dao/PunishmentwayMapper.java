package com.traffic.dao;

import java.util.List;

import com.traffic.bean.Punishmentway;

public interface PunishmentwayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Punishmentway record);

    int insertSelective(Punishmentway record);

    Punishmentway selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Punishmentway record);

    int updateByPrimaryKey(Punishmentway record);
    //查询违章id下的 违章类型
	List<Punishmentway> findPunishmentByTrafficId(Integer trafficId);
}