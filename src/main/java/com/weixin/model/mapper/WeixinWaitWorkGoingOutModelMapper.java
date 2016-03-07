package com.weixin.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.weixin.model.WeixinUserWorkGoingOutModel;
import com.weixin.model.WeixinWaitWorkGoingOutModel;

public class WeixinWaitWorkGoingOutModelMapper implements RowMapper<WeixinWaitWorkGoingOutModel>{
    public WeixinWaitWorkGoingOutModel mapRow(ResultSet rs, int index) throws SQLException {
        WeixinWaitWorkGoingOutModel u = new WeixinWaitWorkGoingOutModel(rs.getInt("work_goingout_id"), rs.getString("user_id"), 
           rs.getString("reasons"), rs.getTimestamp("create_time"), rs.getString("start"), rs.getString("end"), rs.getString("distance"),
           rs.getString("amount"), rs.getString("price"),  rs.getTimestamp("date"), rs.getString("approval_name"));
        return u;
    }
}
