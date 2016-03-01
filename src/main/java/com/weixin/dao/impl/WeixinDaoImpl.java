package com.weixin.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.weixin.dao.IWeixinDao;
import com.weixin.model.WeixinWaitAuditInvoiceModel;
import com.weixin.model.mapper.WeixinWaitAuditInvoiceModelMapper;
@Repository("weixinDao")
public class WeixinDaoImpl implements IWeixinDao {

	private static final Logger logger = LoggerFactory
			.getLogger(WeixinDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<WeixinWaitAuditInvoiceModel> getWeixinAuditInvoice(String openId, int status) {
		List<WeixinWaitAuditInvoiceModel> list = null;
		try {
			String sql = "select a.create_time, b.file_name, a.approval_status, a.reasons, a.invoice_id, c.amount, c.type, d.user_name, d.user_id from user_openid as e, user as d, user_invoice as a, test_invoice as b, test_invoice_artifact as c where a.status=? and e.openid=? and a.user_id=e.user_id and a.invoice_id = b.id and b.item_id = c.item_id and d.user_id=a.approval_id";
			list = jdbcTemplate.query(sql, new Object[] { status, openId },
					new RowMapperResultSetExtractor<WeixinWaitAuditInvoiceModel>(
							new WeixinWaitAuditInvoiceModelMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<WeixinWaitAuditInvoiceModel> getWeixinNeededAuditInvoice(String openId) {
		List<WeixinWaitAuditInvoiceModel> list = null;
		try {
			String sql = "select a.create_time, b.file_name, a.invoice_id, c.amount, c.type, d.user_name, d.user_id, a.approval_status,a.reasons from user_openid as e, user as d, user_invoice as a, test_invoice as b, test_invoice_artifact as c where a.status=0 and e.openid=? and a.approval_id=e.user_id and a.invoice_id = b.id and b.item_id = c.item_id and d.user_id=a.user_id";
			list = jdbcTemplate.query(sql, new Object[] {openId },
					new RowMapperResultSetExtractor<WeixinWaitAuditInvoiceModel>(
							new WeixinWaitAuditInvoiceModelMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<WeixinWaitAuditInvoiceModel> getWeixinSubmitInvoiceByUserId(String userId) {
		List<WeixinWaitAuditInvoiceModel> list = null;
		try {
			String sql = "select a.create_time, b.file_name, a.invoice_id, c.amount, c.type, d.user_id, d.user_name, a.approval_status,a.reasons from user as d, user_invoice as a, test_invoice as b, test_invoice_artifact as c where a.status=0 and d.user_id=? and a.invoice_id = b.id and b.item_id = c.item_id and d.user_id=a.user_id";
			list = jdbcTemplate.query(sql, new Object[] {userId },
					new RowMapperResultSetExtractor<WeixinWaitAuditInvoiceModel>(
							new WeixinWaitAuditInvoiceModelMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}
}