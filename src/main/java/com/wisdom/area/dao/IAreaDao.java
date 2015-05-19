package com.wisdom.area.dao;

import java.util.List;

import com.wisdom.common.model.ShxArea;
import com.wisdom.common.model.ShxCity;
import com.wisdom.common.model.ShxProvince;

public interface IAreaDao {
	
	public List<ShxArea> getAreaByCityId(String cityId);
	
	public List<ShxCity> getCityByProvinceId(String provinceId);
	
	public List<ShxProvince> getAllProvince();
	
	public String getProvinceNameByProvinceId(String provinceId);
	
	public String getCityNameByCityId(String cityId);
	
	public String getAreaNameByAreaId(String areaId);
	
}
