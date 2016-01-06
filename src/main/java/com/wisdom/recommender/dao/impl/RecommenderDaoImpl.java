package com.wisdom.recommender.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;


import com.wisdom.recommender.dao.IRecommenderDao;
import com.wisdom.recommender.mapper.RecommenderMapper;
import com.wisdom.common.model.Recommender;


@Repository("recommenderDao")
public class RecommenderDaoImpl implements IRecommenderDao {

	private static final Logger logger = LoggerFactory
			.getLogger(RecommenderDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	


	@Override
	public Recommender getRecommenderById(String id) {
		String sql = "select * from recommender where id = ?";
		Recommender recommender = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new RecommenderMapper());
		return recommender;
	}

	@Override
	public List<Recommender> getAllRecommender() {
		List<Recommender> list = null;
		try {
			String sql = "select * from recommender";
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<Recommender>(
							new RecommenderMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addRecommender(Recommender recommender) {
		String sql = "insert into recommender (id, name)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, recommender.getId(),
				recommender.getName());
		logger.debug("addRecommender result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteRecommender(Recommender recommender) {
		String sql = "delete from recommender where id = ?";
		int affectedRows = jdbcTemplate.update(sql, recommender.getId());
		logger.debug("deleteRecommender result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateRecommender(Recommender recommender) {
		String sql = "update recommender set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, recommender.getName(),
				recommender.getId());
		logger.debug("updateRecommender result : {}", affectedRows);
		return affectedRows != 0;
	}

}
