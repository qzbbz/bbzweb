package com.weixin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.weixin.dao.IWeixinWorkGoingOutDao;
import com.weixin.model.WeixinWaitAuditInvoiceModel;
import com.weixin.model.WeixinWorkGoingOutModel;
import com.weixin.model.mapper.WeixinWaitAuditInvoiceModelMapper;
import com.weixin.model.mapper.WeixinWorkGoingOutModelMapper;
@Repository("weixinWorkGoingOutDao")
public class WeixinWorkGoingOutDaoImpl implements IWeixinWorkGoingOutDao {
    private static final Logger logger = LoggerFactory
            .getLogger(WeixinWorkGoingOutDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public long addWorkGoingOut(WeixinWorkGoingOutModel wgom) {
        logger.debug("WeixinWorkGoingOutModel : {}" , wgom.toString());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int num;
        try {
            num = jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(
                        Connection connection) throws SQLException {
                    String sql = "INSERT INTO work_goingout (start, end, distance, amount, date, create_time, cost_center, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
                    PreparedStatement ps = connection.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, wgom.getStart());
                    ps.setString(2, wgom.getEnd());
                    ps.setString(3, wgom.getDistance());
                    ps.setString(4, wgom.getAmount());
                    ps.setTimestamp(5, wgom.getDate());
                    ps.setTimestamp(6, wgom.getCreateTime());
                    ps.setString(7, wgom.getCostCenter());
                    ps.setString(8, wgom.getPrice());
                    return ps;
                }
                },keyHolder);
            return keyHolder.getKey().longValue();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return -1;
    }

    @Override
    public List<WeixinWorkGoingOutModel> getWorkGoingOutIdById(int id) {
        List<WeixinWorkGoingOutModel> list = null;
        try {
            String sql = "select * from work_goingout where id = ?";
            list = jdbcTemplate.query(sql, new Object[] {id },
                    new RowMapperResultSetExtractor<WeixinWorkGoingOutModel>(
                            new WeixinWorkGoingOutModelMapper()));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return list;
    }

}
