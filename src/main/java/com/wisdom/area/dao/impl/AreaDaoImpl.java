package com.wisdom.area.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.wisdom.area.dao.IAreaDao;
import com.wisdom.area.mapper.ShxAreaMapper;
import com.wisdom.area.mapper.ShxCityMapper;
import com.wisdom.area.mapper.ShxProvinceMapper;
import com.wisdom.common.model.ShxArea;
import com.wisdom.common.model.ShxCity;
import com.wisdom.common.model.ShxProvince;

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

}
