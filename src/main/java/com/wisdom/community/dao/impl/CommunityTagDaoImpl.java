package com.wisdom.community.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.CommunityTag;
import com.wisdom.community.dao.ICommunityTagDao;
import com.wisdom.community.mapper.CommunityTagMapper;

@Repository("communityTagDao")
public class CommunityTagDaoImpl implements ICommunityTagDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CommunityTagDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public CommunityTag getCommunityTagById(long id) {
		String sql = "select * from community_tag where id = ?";
		CommunityTag company = jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new CommunityTagMapper());
		logger.debug("getCompanyByCompanyId");
		return company;
	}

	@Override
	public boolean addCommunityTag(CommunityTag communityTag) {
		String sql = "insert into community_tag (name, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, communityTag.getName(),
				communityTag.getCreateTime());
		logger.debug("addCommunityTag result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteCommunityTag(CommunityTag communityTag) {
		String sql = "delete from community_tag where id = ?";
		int affectedRows = jdbcTemplate.update(sql, communityTag.getId());
		logger.debug("deleteCommunityTag result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCommunityTag(CommunityTag communityTag) {
		String sql = "update community_tag set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, communityTag.getName(),
				communityTag.getId());
		logger.debug("updateCommunityTag result : {}", affectedRows);
		return affectedRows != 0;
	}

}
