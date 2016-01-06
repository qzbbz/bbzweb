package com.wisdom.recommender.dao.impl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.RecommendInfo;
import com.wisdom.common.model.Recommender;
import com.wisdom.company.mapper.CompanyMapper;
import com.wisdom.recommender.dao.IRecommendInfoDao;
import com.wisdom.recommender.mapper.RecommendInfoMapper;
import com.wisdom.recommender.mapper.RecommenderMapper;
import com.wisdom.recommender.mapper.RecommenderRecordMapper;

@Repository("recommendInfoDao")
public class RecommendInfoDaoImpl implements IRecommendInfoDao {


	private static final Logger logger = LoggerFactory
			.getLogger(RecommenderDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean addRecommendInfo(RecommendInfo recommendInfo) {
		List<RecommendInfo> existed = getRecommendInfoByIP(recommendInfo.getIP());
		if (existed != null){
			for(RecommendInfo single: existed){
				if(single.getIP().equals(recommendInfo.getIP()) && single.getRecommenderId().equals(recommendInfo.getRecommenderId())){
					return false;
				}
			}
		}
		
		String sql = "insert into recommend_info (recommender_id, ip, created_time)"
				+ " values (?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, recommendInfo.getRecommenderId(),
				recommendInfo.getIP(), recommendInfo.getCreatedTime());
		logger.debug("addRecommendInfo result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<RecommendInfo> getRecommendInfoByIP(String ip) {
		List<RecommendInfo> list = new ArrayList<>();
		try {
			String sql = "select * from recommend_info where ip = ?";
			list = jdbcTemplate.query(sql, new Object[] { ip },
					new RowMapperResultSetExtractor<RecommendInfo>(new RecommendInfoMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<RecommendInfo> getRecommendInfoByRecommenderId(String recommender_id) {
		List<RecommendInfo> list = new ArrayList<>();
		try {
			String sql = "select * from recommend_info where recommender_id = ?";
			list =  jdbcTemplate.query(sql, new Object[] { recommender_id },
					new RowMapperResultSetExtractor<RecommendInfo>(new RecommendInfoMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}






}
