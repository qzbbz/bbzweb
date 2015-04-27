package com.wisdom.area.service;

import java.util.List;

public interface IAreaService {

	public List<String> getAreaByCityId(String cityId);
	
	public List<String> getCityByProvinceId(String provinceId);
	
	public List<String> getAllProvince();
	
}
