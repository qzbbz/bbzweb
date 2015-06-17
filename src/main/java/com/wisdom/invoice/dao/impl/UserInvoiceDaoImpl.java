package com.wisdom.invoice.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wisdom.common.model.Employment;
import com.wisdom.common.model.InvoiceInfo;
import com.wisdom.common.model.UserInvoice;
import com.wisdom.company.mapper.EmploymentMapper;
import com.wisdom.invoice.dao.IUserInvoiceDao;
import com.wisdom.invoice.domain.InvoiceInfoVo;
import com.wisdom.invoice.mapper.InvoiceInfoMapper;
import com.wisdom.invoice.mapper.UserInvoiceMapper;

@Repository("userInvoiceDao")
public class UserInvoiceDaoImpl implements IUserInvoiceDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserInvoiceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<UserInvoice> getUserInvoiceByUserId(String userId) {
		List<UserInvoice> list = null;
		try {
			String sql = "select * from user_invoice where user_id = ?";
			list = jdbcTemplate.query(sql, new Object[] { userId },
					new RowMapperResultSetExtractor<UserInvoice>(
							new UserInvoiceMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public UserInvoice getUserInvoiceByInvoiceId(long invoiceId) {
		String sql = "select * from user_invoice where invoice_id = ?";
		try {
			UserInvoice userInvoice = jdbcTemplate.queryForObject(sql,
					new Object[] { invoiceId }, new UserInvoiceMapper());
			return userInvoice;
		} catch (DataAccessException e) {
			logger.error(e.toString());
		}
		return null;
	}
	
	@Override
	public UserInvoice getUserInvoiceByUserIdAndInvoiceId(String userId,long invoiceId){
		String sql = "select * from user_invoice where user_id=? and invoice_id = ?";
		try {
			UserInvoice userInvoice = jdbcTemplate.queryForObject(sql,
					new Object[] { userId,invoiceId }, new UserInvoiceMapper());
			return userInvoice;
		} catch (DataAccessException e) {
			logger.error(e.toString());
		}
		return null;
	}

	@Override
	public boolean addUserInvoice(UserInvoice userInvoice) {
		String sql = "insert into user_invoice (user_id, invoice_id, approval_id, status, update_time, create_time)"
				+ " values (?, ?, ?, ?, ?, ?)";
		try {
			int affectedRows = jdbcTemplate
					.update(sql,
							userInvoice.getUserId(),
							userInvoice.getInvoiceId(),
							userInvoice.getApprovalId() == null ? ""
									: userInvoice.getApprovalId(),
							userInvoice.getStatus() == null ? 0 : userInvoice
									.getStatus(),
							userInvoice.getUpdateTime() == null ? new Timestamp(
									System.currentTimeMillis()) : userInvoice
									.getUpdateTime(),
							userInvoice.getCreateTime() == null ? new Timestamp(
									System.currentTimeMillis()) : userInvoice
									.getCreateTime());
			logger.debug("addUserInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUserInvoice(UserInvoice userInvoice) {
		String sql = "delete from user_invoice where id = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, userInvoice.getId());
			logger.debug("deleteUserInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateUserInvoice(UserInvoice userInvoice) {
		String sql = "update user_invoice set reasons=?, status=?, approval_status=?,approval_id=?,update_time=? where invoice_id=?";
		try {
			int affectedRows = jdbcTemplate
					.update(sql,
							userInvoice.getReasons() == null ? "" : userInvoice.getReasons(),
							userInvoice.getStatus() == null ? 0 : userInvoice
									.getStatus(),
							userInvoice.getApprovalStatus() == null ? 0
									: userInvoice.getApprovalStatus(),
							userInvoice.getApprovalId() == null ? ""
									: userInvoice.getApprovalId(),
							userInvoice.getUpdateTime() == null ? new Timestamp(
									System.currentTimeMillis()) : userInvoice
									.getUpdateTime(), userInvoice.getInvoiceId());
			logger.debug("updateUserInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 我的收件箱使用：默认按照我是审批人搜索
	 */
	@Override
	public List<InvoiceInfo> getInvoiceInfoByCondition(String userId,
			String date, String submitter, Double amount, Integer expenseType,Integer page,Integer pageSize) {
		if(StringUtils.isEmpty(userId)){
			logger.debug("input param error: {}",userId);
			return null;
		}
		Object[] objs = new Object[8];
		StringBuilder sql = new StringBuilder();
		sql.append("select invoice_id,invoice_code,title,expense_type_id,amount,detail_desc,date,cost_center,"
				+ "status process_status,approval_status,approval_id,user_id ");
		sql.append(" from user_invoice,invoice ");
		sql.append(" where user_invoice.approval_id=?");
		objs[0] = userId;
		
		sql.append(" and user_invoice.invoice_id=invoice.id ");
		
		int index=1;
		if(!StringUtils.isEmpty(date)){
			sql.append(" and date=?");
			objs[index] = date;
			index++;
		}

		if(!StringUtils.isEmpty(submitter)){
			sql.append(" and user_invoice.user_id=?");
			objs[index] = submitter;
			index++;
		}
//		if(!StringUtils.isEmpty(amount)){
		if(null != amount && amount > 0){
			sql.append(" and invoice.amount=?");
			objs[index] = amount;
			index++;
		}
		
		if(null != expenseType){
			sql.append(" and invoice.expense_type_id=?");
			objs[index] = expenseType;
			index++;
		}
		
		if(null != page && null != pageSize && pageSize > 0 && page > 0){
			int start = (page-1)*pageSize;
			int end = page*pageSize;
			sql.append(" limit ? , ? ");
			objs[index] = start;
			index++;
			objs[index] = end;
			index++;
		}
		
		Object[] paramsEnd = new Object[index];  
        System.arraycopy(objs, 0, paramsEnd, 0, index); 
		List<InvoiceInfo> list = null;
		try {
			list = jdbcTemplate.query(sql.toString(), paramsEnd,
					new RowMapperResultSetExtractor<InvoiceInfo>(
							new InvoiceInfoMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<UserInvoice> getUserInvoiceByUserIdAndStatusAndApprovalStatus(
			String userId, int status, int approvalStatus) {
		List<UserInvoice> list = null;
		try {
			String sql = "select * from user_invoice where user_id = ? and status = ? and approval_status=?";
			list = jdbcTemplate.query(sql, new Object[] { userId, status, approvalStatus },
					new RowMapperResultSetExtractor<UserInvoice>(
							new UserInvoiceMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

}