package com.wisdom.community.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Community;
import com.wisdom.community.dao.ICommunityDao;
import com.wisdom.community.mapper.CommunityMapper;

@Repository("communityDao")
public class CommunityDaoImpl implements ICommunityDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CommunityDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Community> getCommunityByTagId(long tagId) {
		List<Community> list = null;
		try {
			String sql = "select * from community where tag_id =?";
			list = jdbcTemplate.query(sql, new Object[] { tagId },
					new RowMapperResultSetExtractor<Community>(
							new CommunityMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<Community> getCommunityByKeywords(String keywords) {
		keywords = "%" + keywords + "%";
		List<Community> list = null;
		try {
			String sql = "select * from community where title like ? or abstract_data like ? or data like ?";
			list = jdbcTemplate.query(sql, new Object[] { keywords, keywords,
					keywords }, new RowMapperResultSetExtractor<Community>(
					new CommunityMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<Community> getCommunityByOffset(long startOffset, long endOffset) {
		List<Community> list = null;
		try {
			String sql = "select * from community limit ?, ?";
			list = jdbcTemplate.query(sql, new Object[] { startOffset,
					endOffset }, new RowMapperResultSetExtractor<Community>(
					new CommunityMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addCommunity(Community community) {
		String sql = "insert into community (user_id, tag_id, image, title, abstract_data, data, create_time, update_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, community.getUserId(),
				community.getTagId(), community.getImage(),
				community.getTitle(), community.getAbstractData(),
				community.getData(), community.getCreateTime(),
				community.getUpdateTime());
		logger.debug("addCommunity result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteCommunity(Community community) {
		String sql = "delete from community where id = ?";
		int affectedRows = jdbcTemplate.update(sql, community.getId());
		logger.debug("deleteCommunity result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCommunity(Community community) {
		String sql = "update community set tag_id=?, image=?, title=?, abstract_data=?, data=?, update_time=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, community.getTagId(),
				community.getImage(), community.getTitle(),
				community.getAbstractData(), community.getData(),
				community.getUpdateTime(), community.getId());
		logger.debug("updateCommunity result : {}", affectedRows);
		return affectedRows != 0;
	}

}
