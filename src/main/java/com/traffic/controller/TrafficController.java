package com.traffic.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.traffic.bean.Traffic;
import com.traffic.bean.ana.TrafficJsonObj;
import com.traffic.bean.ana.TrafficParam;
import com.traffic.service.TrafficService;
import com.traffic.utils.FileUtil;
import com.traffic.utils.JacksonUtil;

@Controller
@RequestMapping("admin")
public class TrafficController {

	@Autowired
	private TrafficService trafficService;
	/**
	 * 跳转到主页面 
	 * @return
	 */
	@RequestMapping("trafficView")
	//@ResponseBody
	public String hello() {
		return "index";
	}
	
	/**
	 * 显示所有的罚款信息
	 * @return
	 */
	@RequestMapping("trafficList")
	@ResponseBody
	public String trafficList() {
		List<Traffic> tarList = trafficService.findAllTraffic();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", tarList);
		return JacksonUtil.toJSon(jsonObject);
	}
	
	/**
	 * 删除罚款信息
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String delete(int traId) {
		int num  = trafficService.deleteTrafficById(traId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", num==1?true:false);
		return JacksonUtil.toJSon(jsonObject);
	}
	
	/**
	 * 导出罚款信息
	 */
	@RequestMapping("export")
	@ResponseBody
	public void export(int traId,HttpServletResponse response) {
		HSSFWorkbook workbook = trafficService.exportTraffic(traId);
		FileUtil.downloadExcelFile(workbook, response,"_罚款信息");
	}
	
	/**
	 * 添加罚款信息
	 */
	@RequestMapping("save")
	@ResponseBody
	public String save(@RequestBody(required=false) TrafficParam param) {
		if(param!=null) {
			return trafficService.saveTraffic(param);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", false);
		return jsonObject.toJSONString();
		
	}
	
	
}
