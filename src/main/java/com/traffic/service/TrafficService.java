package com.traffic.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.traffic.bean.Traffic;
import com.traffic.bean.ana.TrafficParam;

public interface TrafficService {
	/**
	 *  查询所有的违章
	 * @return
	 */
	List<Traffic> findAllTraffic();

	/**
	 * 删除违章信息 
	 * @param traId
	 */
	int deleteTrafficById(int traId);
	
	/**
	 * 生成excel 文档对象
	 * @param traId
	 * @return
	 */
	HSSFWorkbook exportTraffic(int traId);

	/**
	 * 保存违章信息
	 * @param param
	 * @return
	 */
	String saveTraffic(TrafficParam param);

}
