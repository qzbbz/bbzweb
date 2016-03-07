package com.weixin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.weixin.dao.IWeixinUserWorkGoingOutDao;
import com.weixin.model.WeixinUserWorkGoingOutModel;
@Repository("weixinUserWorkGoingOutDaoImpl")
public class WeixinUserWorkGoingOutDaoImpl implements IWeixinUserWorkGoingOutDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory
            .getLogger(WeixinUserWorkGoingOutDaoImpl.class);
    @Override
    public long addUserWorkGoingOut(WeixinUserWorkGoingOutModel uwgom) {
        logger.debug("WeixinWorkGoingOutModel : {}" , uwgom.toString());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int num;
        try {
            num = jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(
                        Connection connection) throws SQLException {
                    String sql = "INSERT INTO user_work_goingout (user_id, work_goingout_id, status, approval_status, reasons, approval_id, update_time, create_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
                    PreparedStatement ps = connection.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, uwgom.getUserId());
                    ps.setLong(2, uwgom.getUserWorkGoingOutId());
                    ps.setInt(3, uwgom.getStatus());
                    ps.setInt(4, uwgom.getApprovalStatus());
                    ps.setString(5, uwgom.getReasons());
                    ps.setString(6, uwgom.getApprovalId());
                    ps.setTimestamp(7, uwgom.getUpdateTime());
                    ps.setTimestamp(8, uwgom.getCreateTime());
                    return ps;
                }
                },keyHolder);
            return keyHolder.getKey().longValue();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return -1;
    }

}
