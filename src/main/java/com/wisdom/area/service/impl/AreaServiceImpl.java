package com.wisdom.area.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.area.dao.IAreaDao;
import com.wisdom.area.service.IAreaService;
import com.wisdom.common.model.ShxArea;
import com.wisdom.common.model.ShxCity;
import com.wisdom.common.model.ShxProvince;

@Service("areaService")
public class AreaServiceImpl implements IAreaService {

	private static final Logger logger = LoggerFactory
			.getLogger(AreaServiceImpl.class);

	@Autowired
	private IAreaDao areaDao;
	
	@Override
	public List<String> getAreaByCityId(String cityId) {
		List<String> areaList = new ArrayList<>();
		List<ShxArea> shxAreaList = areaDao.getAreaByCityId(cityId);
		if(shxAreaList != null && shxAreaList.size() > 0) {
			for(ShxArea area : shxAreaList) {
				areaList.add(area.getArea());
			}
		}
		logger.debug("area result : {}", areaList.toString());
		return areaList;
	}

	@Override
	public List<String> getCityByProvinceId(String provinceId) {
		List<String> cityList = new ArrayList<>();
		List<ShxCity> shxCityList = areaDao.getCityByProvinceId(provinceId);
		if(shxCityList != null && shxCityList.size() > 0) {
			for(ShxCity city : shxCityList) {
				cityList.add(city.getCity());
			}
		}
		logger.debug("area result : {}", cityList.toString());
		return cityList;
	}

	@Override
	public List<String> getAllProvince() {
		List<String> provinceList = new ArrayList<>();
		List<ShxProvince> shxProvinceList = areaDao.getAllProvince();
		if(shxProvinceList != null && shxProvinceList.size() > 0) {
			for(ShxProvince province : shxProvinceList) {
				provinceList.add(province.getProvince());
			}
		}
		logger.debug("area result : {}", provinceList.toString());
		return provinceList;
	}

}
