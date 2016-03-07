package com.weixin.dao.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.weixin.dao.IWeixinDao;
import com.weixin.model.WeixinWaitAuditInvoiceModel;
import com.weixin.model.WeixinWaitWorkGoingOutModel;
import com.weixin.model.mapper.WeixinWaitAuditInvoiceModelMapper;
import com.weixin.model.mapper.WeixinWaitWorkGoingOutModelMapper;
@Repository("weixinDao")
public class WeixinDaoImpl implements IWeixinDao {

	private static final Logger logger = LoggerFactory
			.getLogger(WeixinDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

    /* 
     * XiaoMing add weixin WorkGoingOut 
     */
    @Override
    public List<WeixinWaitWorkGoingOutModel> getWeixinWaitWorkGoingOut(String openId, int status) {
        List<WeixinWaitWorkGoingOutModel> list = null;
        try {
            String sql = "SELECT u.user_id, u.reasons, u.create_time, w.`start`, w.`end`, w.distance, w.amount, w.price, w.date, uu.user_name as approval_name FROM  user_openid as o , user_work_goingout as u,work_goingout as w, `user` as uu WHERE  o.openid = 'oSTV_t9z_fYa7AQVYO0y5-OMFavQ' AND u.`status` = 0 AND o.user_id = u.user_id AND u.work_goingout_id = w.id AND u.approval_id = uu.user_id ;";
            list = jdbcTemplate.query(sql, new Object[] { openId, status },
                    new RowMapperResultSetExtractor<WeixinWaitWorkGoingOutModel>(
                            new WeixinWaitWorkGoingOutModelMapper()));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return list;
    }
    
    @Override
    public List<WeixinWaitWorkGoingOutModel> getWeixinAuditWorkGoingOutByUserId(String userId, int status) {
        List<WeixinWaitWorkGoingOutModel> list = null;
        try {
            String sql = "SELECT  u.user_id, u.work_goingout_id, u.reasons, u.create_time, w.`start`, w.`end`, w.distance, w.amount, w.price, w.date, uu.user_name as approval_name FROM  user_openid as o , user_work_goingout as u,work_goingout as w, `user` as uu WHERE  u.user_id = ? AND u.`status` = ? AND o.user_id = u.user_id AND u.work_goingout_id = w.id AND u.user_id = uu.user_id ;";
            list = jdbcTemplate.query(sql, new Object[] { userId, status },
                    new RowMapperResultSetExtractor<WeixinWaitWorkGoingOutModel>(
                            new WeixinWaitWorkGoingOutModelMapper()));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return list;
    }

    
    @Override
    public List<WeixinWaitWorkGoingOutModel> getWeixinAuditWorkGoingOut(String openId, int status) {
        List<WeixinWaitWorkGoingOutModel> list = null;
        try {
            String sql = "SELECT  u.user_id, u.work_goingout_id, u.reasons, u.create_time, w.`start`, w.`end`, w.distance, w.amount, w.price, w.date, uu.user_name as approval_name FROM  user_openid as o , user_work_goingout as u,work_goingout as w, `user` as uu WHERE  o.openid = ? AND u.`status` = ? AND o.user_id = u.user_id AND u.work_goingout_id = w.id AND u.user_id = uu.user_id ;";
            list = jdbcTemplate.query(sql, new Object[] { openId, status },
                    new RowMapperResultSetExtractor<WeixinWaitWorkGoingOutModel>(
                            new WeixinWaitWorkGoingOutModelMapper()));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return list;
    }
    
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

    @Override
    public boolean updateUserWorkGoingOut(String workId, String approval_status, String reasons) {
        String sql = "update user_work_goingout set status=1, approval_status=?, reasons=?, update_time=? where work_goingout_id=?";
        Timestamp d = new Timestamp(System.currentTimeMillis()); 
        try {
            int affectedRows = jdbcTemplate.update(sql, approval_status, reasons , d, workId
                );
            logger.debug("updateUserWorkGoingOut result : {}", affectedRows);
            return affectedRows != 0;
        } catch (DataAccessException e) {
            logger.error("updateUserWorkGoingOut error." + e.getMessage());
        }
        return false;
    }

    @Override
    public List<WeixinWaitWorkGoingOutModel> getAuditingWorkGoingOut(String openId, int status) {
        List<WeixinWaitWorkGoingOutModel> list = null;
        try {
            String sql = "SELECT  u.user_id, u.work_goingout_id, u.reasons, u.create_time, w.`start`, w.`end`, w.distance, w.amount, w.price, w.date, uu.user_name as approval_name FROM  user_openid as o , user_work_goingout as u,work_goingout as w, `user` as uu WHERE  o.openid =? AND u.`status` = ? AND o.user_id = u.approval_id AND u.work_goingout_id = w.id AND u.user_id = uu.user_id";
            list = jdbcTemplate.query(sql, new Object[] { openId, status },
                    new RowMapperResultSetExtractor<WeixinWaitWorkGoingOutModel>(
                            new WeixinWaitWorkGoingOutModelMapper()));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return list;
    }

}