package com.wisdom.area.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.area.dao.IAreaDao;
import com.wisdom.area.mapper.ShxAreaMapper;
import com.wisdom.area.mapper.ShxCityMapper;
import com.wisdom.area.mapper.ShxProvinceMapper;
import com.wisdom.common.model.ShxArea;
import com.wisdom.common.model.ShxCity;
import com.wisdom.common.model.ShxProvince;

@Repository("areaDao")
public class AreaDaoImpl implements IAreaDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AreaDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<ShxArea> getAreaByCityId(String cityId) {
		List<ShxArea> list = null;
		try {
			String sql = "select * from shx_area where father =?";
			list = jdbcTemplate.query(sql, new Object[]{cityId},
					new RowMapperResultSetExtractor<ShxArea>(
							new ShxAreaMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<ShxCity> getCityByProvinceId(String provinceId) {
		List<ShxCity> list = null;
		try {
			String sql = "select * from shx_city where father =?";
			list = jdbcTemplate.query(sql, new Object[]{provinceId},
					new RowMapperResultSetExtractor<ShxCity>(
							new ShxCityMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<ShxProvince> getAllProvince() {
		List<ShxProvince> list = null;
		try {
			String sql = "select * from shx_province";
			list = jdbcTemplate.query(sql, new RowMapperResultSetExtractor<ShxProvince>(
							new ShxProvinceMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public String getProvinceNameByProvinceId(String provinceId) {
		logger.debug("provinceId : {}", provinceId);
		String sql = "select * from shx_province where provinceID = ?";
		ShxProvince sp = null;
		try {
			sp = jdbcTemplate.queryForObject(sql,
				new Object[] { provinceId }, new ShxProvinceMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}" + e.toString());
		}
		return sp == null ? "" : sp.getProvince();
	}

	@Override
	public String getCityNameByCityId(String cityId) {
		logger.debug("cityId : {}", cityId);
		String sql = "select * from shx_city where cityID = ?";
		ShxCity sc = null;
		try {
			sc = jdbcTemplate.queryForObject(sql,
				new Object[] { cityId }, new ShxCityMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}" + e.toString());
		}
		return sc == null ? "" : sc.getCity();
	}

	@Override
	public String getAreaNameByAreaId(String areaId) {
		logger.debug("areaId : {}", areaId);
		String sql = "select * from shx_area where areaID = ?";
		ShxArea sa = null;
		try {
			sa = jdbcTemplate.queryForObject(sql,
				new Object[] { areaId }, new ShxAreaMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}" + e.toString());
		}
		return sa == null ? "" : sa.getArea();
	}

}
