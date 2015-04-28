package com.wisdom.web.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.area.service.IAreaService;

@Controller
public class AreaController {

	private static final Logger logger = LoggerFactory
			.getLogger(AreaController.class);
	
	@Autowired
	private IAreaService areaService;
	
	@RequestMapping("/getAllProvinces")
	@ResponseBody
	public Map<String, String> getAllProvince() {
		logger.debug("enter getAllProvince");
		Map<String, String> retMap = areaService.getAllProvince();
		logger.debug("finish getAllProvince");
		return retMap;
	}
	
	@RequestMapping("/getCities")
	@ResponseBody
	public Map<String, String> getCities(HttpServletRequest request) {
		logger.debug("enter getCities");
		String provinceId = request.getParameter("provinceId");
		Map<String, String> retMap = areaService.getCityByProvinceId(provinceId);
		logger.debug("finish getCities");
		return retMap;
	}
	
	@RequestMapping("/getAreas")
	@ResponseBody
	public Map<String, String> getAreas(HttpServletRequest request) {
		logger.debug("enter getAreas");
		String cityId = request.getParameter("cityId");
		Map<String, String> retMap = areaService.getAreaByCityId(cityId);
		logger.debug("finish getAreas");
		return retMap;
	}
	
}
