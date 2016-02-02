package com.weixin.campaign;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WeixinCampaignSubjectMapper implements RowMapper<WeixinCampaignSubjectModel> {
	public WeixinCampaignSubjectModel mapRow(ResultSet rs, int index) throws SQLException {
		WeixinCampaignSubjectModel u = new WeixinCampaignSubjectModel(rs.getInt("id"), rs.getString("subject"),
				rs.getString("select_one"), rs.getString("select_two"), rs.getString("select_three"), 
				rs.getInt("subject_status"), rs.getInt("right_select"), rs.getString("subject_date"));
		return u;
	}
}