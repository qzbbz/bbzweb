package com.wisdom.recommender.dao.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;






import com.wisdom.common.model.RecommendInfo;
import com.wisdom.common.model.Recommender;
import com.wisdom.recommender.dao.IRecommendInfoDao;
import com.wisdom.recommender.mapper.RecommendInfoMapper;
import com.wisdom.recommender.mapper.RecommenderMapper;

@Repository("recommendInfoDao")
public class RecommendInfoDaoImpl implements IRecommendInfoDao {


	private static final Logger logger = LoggerFactory
			.getLogger(RecommenderDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean addRecommendInfo(RecommendInfo recommendInfo) {
		List<RecommendInfo> existed = getRecommendInfoByIP(recommendInfo.getIP());
		for(RecommendInfo single: existed){
			if(single.getIP() == recommendInfo.getIP() && single.getRecommenderId() == recommendInfo.getRecommenderId()){
				return false;
			}
		}
		
		String sql = "insert into recommend_info (recommender_id, ip)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, recommendInfo.getRecommenderId(),
				recommendInfo.getIP());
		logger.debug("addRecommendInfo result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<RecommendInfo> getRecommendInfoByIP(String ip) {
		List<RecommendInfo> list = null;
		try {
			String sql = "select recommender_id from recommender_info where ip = ?";
			list = (List<RecommendInfo>) jdbcTemplate.queryForObject(sql, new Object[] { ip },
					new RecommendInfoMapper());
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}



}
