package com.weixin.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.weixin.model.WeixinWorkGoingOutModel;

public class WeixinWorkGoingOutModelMapper implements RowMapper<WeixinWorkGoingOutModel>{
    public WeixinWorkGoingOutModel mapRow(ResultSet rs, int index) throws SQLException {
        WeixinWorkGoingOutModel u = new WeixinWorkGoingOutModel(
                rs.getLong("id"), rs.getString("start"),  rs.getString("end"),rs.getString("distance"),rs.getString("amount"),
                rs.getTimestamp("date"), rs.getTimestamp("create_time"),rs.getString("cost_center"), rs.getString("price"));
        return u;
    }
}
