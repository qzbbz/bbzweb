package com.wisdom.sales.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Recommender;
import com.wisdom.common.model.Sales;
import com.wisdom.recommender.mapper.RecommenderMapper;
import com.wisdom.sales.dao.ISalesDao;
import com.wisdom.sales.mapper.SalesMapper;


@Repository("salesDao")
public class SalesDaoImpl implements ISalesDao {

	private static final Logger logger = LoggerFactory
			.getLogger(SalesDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Sales> getAllSalesRecords() {
		List<Sales> list = null;
		try {
			String sql = "select * from sales";
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<Sales>(
							new SalesMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public long addSalesRecord(Sales sales) {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int id = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					String sql = "insert into sales (saller_account,user_name,user_company,user_phone,latest_comment,accountant,updated_time,status,accountant_id)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, sales.getSallerAccount() == null? "": sales.getSallerAccount());
					ps.setString(2, sales.getUserName() == null? "": sales.getUserName());
					ps.setString(3, sales.getUserCompany() == null ? "" : sales.getUserCompany());
					ps.setString(4, sales.getUserPhone() == null ? "" : sales.getUserPhone());
					ps.setString(5, sales.getLatestComment() == null ? "" : sales.getLatestComment());
					ps.setString(6,sales.getAccountant() == null ? "" : sales.getAccountant());
					ps.setString(7, sales.getUpdatedTime()  == null ? "" : sales.getUpdatedTime());
					ps.setString(8,sales.getStatus() == null ? "": sales.getStatus());
					ps.setString(9,sales.getAccountantId() == null ? "": sales.getAccountantId());
					return ps;
				}
			}, keyHolder);
			logger.debug("addSalesRecordAndGetKey result : {}", keyHolder.getKey().longValue());
			return keyHolder.getKey().longValue();
		} catch (DataAccessException e) {
			logger.error("addSalesRecordAndGetKey error." + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Boolean updateSalesRecordStatus(String user_phone, String status) {
		String sql = "update sales set status = ? where user_phone = ?";
		int affectedRows = jdbcTemplate.update(sql, status, user_phone);
		logger.debug("updateSalesRecordStatus result : {}", affectedRows);
		return affectedRows != 0;	
	}

	@Override
	public List<Sales> getAllSalesRecordsByUpdatedTime(String time) {
		List<Sales> list = null;
		try {
			String sql = "select * from sales where updated_time = '" + time + "'";
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<Sales>(
							new SalesMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public Boolean updateSalesRecordInformation(Integer id, String accountant, String updatedTime,
			String status, String accountantId) {
		String sql = "update sales set accountant = ?, updated_time = ?, status = ? , accountant_id=? where id = ?";
		int affectedRows = jdbcTemplate.update(sql, accountant, updatedTime, status, accountantId, id);
		logger.debug("updateSalesRecordInfo result : {}", affectedRows);
		return affectedRows != 0;	
		
	}

	@Override
	public Boolean updateSalesRecordLatestComment(Integer id, String comment) {
		String sql = "update sales set latest_comment = ? where id = ?";
		int affectedRows = jdbcTemplate.update(sql, comment, id);
		logger.debug("updateSalesRecordLatestComment result : {}", affectedRows);
		return affectedRows != 0;	
	}

	@Override
	public Boolean updateSalesSendEmailStatus(Integer id, Integer status) {
		String sql = "update sales set has_send_email = ? where id = ?";
		int affectedRows = jdbcTemplate.update(sql, status, id);
		logger.debug("updateSalesSendEmailStatus result : {}", affectedRows);
		return affectedRows != 0;	
	}

	@Override
	public Sales getSalesById(Integer id) {
		Sales sale = null;
		try {
			String sql = "select * from sales where id = ?";
			sale = jdbcTemplate.queryForObject(sql, new Object[]{id}, new SalesMapper());
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return sale;
	}
	


}
