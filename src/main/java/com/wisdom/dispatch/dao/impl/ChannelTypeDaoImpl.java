package com.wisdom.dispatch.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wisdom.common.model.ChannelType;
import com.wisdom.dispatch.dao.IChannelTypeDao;
import com.wisdom.dispatch.mapper.ChannelTypeMapper;

public class ChannelTypeDaoImpl implements IChannelTypeDao {

	private static final Logger logger = LoggerFactory
			.getLogger(ChannelTypeDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public ChannelType getChannelTypeById(long id) {
		String sql = "select * from channel_type where id = ?";
		ChannelType user = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new ChannelTypeMapper());
		return user;
	}

	@Override
	public boolean addChannelType(ChannelType channelType) {
		String sql = "insert into channel_type (name, create_time)"
				+ " values (?, ?)";
		int affectedRows = jdbcTemplate.update(sql, channelType.getName(),
				channelType.getCreateTime());
		logger.debug("addChannelType result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteChannelType(ChannelType channelType) {
		String sql = "delete from channel_type where id = ?";
		int affectedRows = jdbcTemplate.update(sql, channelType.getId());
		logger.debug("deleteChannelType result : {}", affectedRows);
		return affectedRows != 0;
	}

}
