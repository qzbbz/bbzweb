package com.wisdom.area.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.area.dao.IAreaDao;
import com.wisdom.area.service.IAreaService;
import com.wisdom.common.model.ShxArea;
import com.wisdom.common.model.ShxCity;
import com.wisdom.common.model.ShxProvince;

@Service("areaService")
public class AreaServiceImpl implements IAreaService {

	@Autowired
	private IAreaDao areaDao;
	
	@Override
	public Map<String, String> getAreaByCityId(String cityId) {
		Map<String, String> areaMap = new HashMap<>();
		List<ShxArea> shxAreaList = areaDao.getAreaByCityId(cityId);
		if(shxAreaList != null && shxAreaList.size() > 0) {
			for(ShxArea area : shxAreaList) {
				areaMap.put(area.getAreaId(), area.getArea());
			}
		}
		return areaMap;
	}

	@Override
	public Map<String, String> getCityByProvinceId(String provinceId) {
		Map<String, String> cityMap = new HashMap<>();
		List<ShxCity> shxCityList = areaDao.getCityByProvinceId(provinceId);
		if(shxCityList != null && shxCityList.size() > 0) {
			for(ShxCity city : shxCityList) {
				cityMap.put(city.getCityId(), city.getCity());
			}
		}
		return cityMap;
	}

	@Override
	public Map<String, String> getAllProvince() {
		Map<String, String> provinceMap = new HashMap<>();
		List<ShxProvince> shxProvinceList = areaDao.getAllProvince();
		if(shxProvinceList != null && shxProvinceList.size() > 0) {
			for(ShxProvince province : shxProvinceList) {
				provinceMap.put(province.getProvinceId(), province.getProvince());
			}
		}
		return provinceMap;
	}

	@Override
	public String getProvinceNameByProvinceId(String provinceId) {
		return areaDao.getProvinceNameByProvinceId(provinceId);
	}

	@Override
	public String getCityNameByCityId(String cityId) {
		return areaDao.getCityNameByCityId(cityId);
	}

	@Override
	public String getAreaNameByAreaId(String areaId) {
		return areaDao.getAreaNameByAreaId(areaId);
	}

}
