package com.wisdom.area.service;

import java.util.Map;

public interface IAreaService {

	public Map<String, String> getAreaByCityId(String cityId);
	
	public Map<String, String> getCityByProvinceId(String provinceId);
	
	public Map<String, String> getAllProvince();
	
	public String getProvinceNameByProvinceId(String provinceId);
	
	public String getCityNameByCityId(String cityId);
	
	public String getAreaNameByAreaId(String areaId);
}
