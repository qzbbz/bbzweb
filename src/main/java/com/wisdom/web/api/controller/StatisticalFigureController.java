package com.wisdom.web.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatisticalFigureController {

	private static final Logger logger = LoggerFactory
			.getLogger(StatisticalFigureController.class);

	@RequestMapping("/getStatisticalFigure")
	@ResponseBody
	public Map<String, String> getAllAccounterCareer() {
		logger.debug("enter getStatisticalFigure");
		Map<String, String> retMap = new HashMap<>();
		retMap.put("111", "value1");
		retMap.put("222", "value2");
		return retMap;
	}

	
}
