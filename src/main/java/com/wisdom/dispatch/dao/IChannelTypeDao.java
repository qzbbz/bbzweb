package com.wisdom.dispatch.dao;

import com.wisdom.common.model.ChannelType;

public interface IChannelTypeDao {

	public ChannelType getChannelTypeById(long id);
	
	public boolean addChannelType(ChannelType channelType);
	
	public boolean deleteChannelType(ChannelType channelType);
	
}
