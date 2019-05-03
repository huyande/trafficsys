package com.traffic.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.traffic.bean.Punishmentway;
import com.traffic.bean.Traffic;
import com.traffic.bean.ana.TrafficJsonObj;
import com.traffic.bean.ana.TrafficParam;
import com.traffic.dao.PunishmentwayMapper;
import com.traffic.dao.TrafficMapper;
import com.traffic.service.TrafficService;
import com.traffic.utils.ExcelUtil;
@Service
public class TrafficServiceImpl implements TrafficService {

	@Autowired
	private TrafficMapper trafficMapper;
	
	@Autowired
	private PunishmentwayMapper punishmentwayMapper;
	
	
	@Override
	public List<Traffic> findAllTraffic() {
		List<Traffic> tarfficDatas = trafficMapper.findAllTraffic();
		if(tarfficDatas!=null) {
			List<Traffic> trafficJsonObjDatas = new ArrayList<Traffic>();
			for(Traffic tra : tarfficDatas) {
				List<Punishmentway> punishDatas = punishmentwayMapper.findPunishmentByTrafficId(tra.getId());
				tra.setPunishmentways(punishDatas);
				trafficJsonObjDatas.add(tra);
			}
			return trafficJsonObjDatas;
		}else {
			return null;
		}
	}


	@Override
	public int deleteTrafficById(int traId) {
		return trafficMapper.deleteByPrimaryKey(traId);
	}


	@Override
	public HSSFWorkbook exportTraffic(int traId) {
		Traffic traffic = trafficMapper.selectByPrimaryKey(traId);
		if(traffic!=null) {
			List<Punishmentway> punishDatas = punishmentwayMapper.findPunishmentByTrafficId(traffic.getId());
			traffic.setPunishmentways(punishDatas);
			try {
				HSSFWorkbook hssWorkbook = ExcelUtil.generateExcelFile(traffic);
				return hssWorkbook;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	@Override
	public String saveTraffic(TrafficParam param) {
		//创建违章对象 赋值个当前对象 进行保存
		Traffic traffic = new Traffic();
		traffic.setName(param.getName());
		traffic.setLicenseid(param.getLicenseid());
		traffic.setAddress(param.getAddress());
		traffic.setDecodes(param.getDecodes());
		traffic.setPhonenumber(param.getPhonenumber());
		traffic.setLicenceplate(param.getLicenceplate());
		traffic.setModeltype(param.getModeltype());
		traffic.setFactoryname(param.getFactoryname());
		traffic.setExpiringdate(param.getExpiringdate());
		traffic.setViolationdate(param.getViolationdate());
		traffic.setCreatetime(param.getCreatetime());
		traffic.setViolationaddress(param.getViolationaddress());
		traffic.setViolationcontent(param.getViolationcontent());
		
		//进行保存操作
		int num = trafficMapper.insert(traffic);
		
		JSONObject jsonObject = new JSONObject();
		if(num!=0) {
			//获取到违章种类
			List<String> pun_list = param.getPunishmentways();
			if(pun_list.size()>1) {
				for(int i=1;i<pun_list.size();i++) {
					Punishmentway punishmentway = new Punishmentway();
					punishmentway.setPunishmenttype(Integer.parseInt(pun_list.get(i)));
					punishmentway.setTrafficid(traffic.getId());
					punishmentwayMapper.insert(punishmentway);
				}
			}
			jsonObject.put("data", true);
			return jsonObject.toJSONString();
		}
		jsonObject.put("data", false);
		return jsonObject.toJSONString();
	
	}

}
