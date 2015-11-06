package com.wisdom.recommender.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;


import com.wisdom.recommender.dao.IRecommendRecordDao;
import com.wisdom.recommender.mapper.RecommendRecordMapper;
import com.wisdom.common.model.RecommendRecord;


@Repository("recommendRecordDao")
public class RecommendRecordDaoImpl implements IRecommendRecordDao {

	private static final Logger logger = LoggerFactory
			.getLogger(RecommenderDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Override
	public RecommendRecord getRecommendRecordById(String recommender_id) {
		String sql = "select * from recommend_record where recommender_id = ?";
		RecommendRecord recommendRecord = jdbcTemplate.queryForObject(sql, new Object[] { recommender_id },
				new RecommendRecordMapper());
		return recommendRecord;
	}

	@Override
	public List<RecommendRecord> getAllRecommendRecord() {
		List<RecommendRecord> list = null;
		try {
			String sql = "select * from recommend_record";
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<RecommendRecord>(
							new RecommendRecordMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addRecommendRecord(RecommendRecord recommendRecord) {
		String sql = "insert into recommend_record (recommender_id, customer_email, created_time)"
				+ " values (?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, recommendRecord.getRecommenderId(),
				recommendRecord.getCustomerEmail(), recommendRecord.getCreatedTime());
		logger.debug("addRecommendRecord result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteRecommendRecord(RecommendRecord recommendRecord) {
		String sql = "delete from recommend_record where recommender_id = ?";
		int affectedRows = jdbcTemplate.update(sql, recommendRecord.getRecommenderId());
		logger.debug("deleteRecommendRecord result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateRecommendRecord(RecommendRecord recommendRecord) {
		String sql = "update recommend_record set customer_email=?, created_time=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, recommendRecord.getCustomerEmail(), recommendRecord.getCreatedTime(),
				recommendRecord.getRecommenderId());
		logger.debug("updateRecommendRecord result : {}", affectedRows);
		return affectedRows != 0;
	}

}
