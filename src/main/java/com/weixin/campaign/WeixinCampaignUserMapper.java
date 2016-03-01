package com.weixin.campaign;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WeixinCampaignUserMapper implements RowMapper<WeixinCampaignUserModel> {
	public WeixinCampaignUserModel mapRow(ResultSet rs, int index) throws SQLException {
		WeixinCampaignUserModel u = new WeixinCampaignUserModel(rs.getString("user_id"), rs.getString("user_name"),
				rs.getInt("finish_count"), rs.getInt("right_count"), rs.getTimestamp("update_time"),
				rs.getTimestamp("create_time"), rs.getLong("answer_rate"), rs.getInt("has_answer"));
		return u;
	}
}