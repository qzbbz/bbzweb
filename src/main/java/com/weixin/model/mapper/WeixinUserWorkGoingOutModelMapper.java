package com.weixin.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.weixin.model.WeixinUserWorkGoingOutModel;

public class WeixinUserWorkGoingOutModelMapper implements RowMapper<WeixinUserWorkGoingOutModel>{
    public WeixinUserWorkGoingOutModel mapRow(ResultSet rs, int index) throws SQLException {
        WeixinUserWorkGoingOutModel u = new WeixinUserWorkGoingOutModel(
                rs.getInt("id"), rs.getString("user_id"),  rs.getLong("work_goingout_id"),rs.getInt("status"),rs.getInt("approval_status"),rs.getString("reasons"), 
                rs.getString("approval_id"), rs.getTimestamp("update_time"), rs.getTimestamp("create_time"));
        return u;
    }
}
