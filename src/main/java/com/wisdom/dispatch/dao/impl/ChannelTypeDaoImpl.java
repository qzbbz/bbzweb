package com.wisdom.dispatch.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.ChannelType;
import com.wisdom.dispatch.dao.IChannelTypeDao;
import com.wisdom.dispatch.mapper.ChannelTypeMapper;

@Repository("channelTypeDao")
public class ChannelTypeDaoImpl implements IChannelTypeDao {

	private static final Logger logger = LoggerFactory
			.getLogger(ChannelTypeDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public ChannelType getChannelTypeById(long id) {
		String sql = "select * from channel_type where id = ?";
		try{
			ChannelType user = jdbcTemplate.queryForObject(sql, new Object[] { id },
					new ChannelTypeMapper());
			return user;
		}catch(Exception e){
			logger.error("getChannelTypeById error:");
		}
		return null;
	}

	@Override
	public boolean addChannelType(ChannelType channelType) {
		String sql = "insert into channel_type (name, create_time)"
				+ " values (?, ?)";
		try{
			int affectedRows = jdbcTemplate.update(sql, channelType.getName(),
					channelType.getCreateTime());
			logger.debug("addChannelType result : {}", affectedRows);
			return affectedRows != 0;
		}catch(Exception e){
			logger.error("addChannelType error!");
		}
		return false;
	}

	@Override
	public boolean deleteChannelType(ChannelType channelType) {
		String sql = "delete from channel_type where id = ?";
		try{
			int affectedRows = jdbcTemplate.update(sql, channelType.getId());
			logger.debug("deleteChannelType result : {}", affectedRows);
			return affectedRows != 0;
		}catch(Exception e){
			logger.error("deleteChannelType error");
		}
		return false;
	}

}
