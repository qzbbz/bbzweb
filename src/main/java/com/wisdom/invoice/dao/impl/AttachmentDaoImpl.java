package com.wisdom.invoice.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Attachment;
import com.wisdom.invoice.dao.IAttachmentDao;
import com.wisdom.invoice.mapper.AttachmentMapper;

@Repository("attachmentDao")
public class AttachmentDaoImpl implements IAttachmentDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AttachmentDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Attachment getAttatchmentByInvoiceId(long invoiceId) {
		String sql = "select * from attachment where invoice_id = ?";
		Attachment attachment = jdbcTemplate.queryForObject(sql,
				new Object[] { invoiceId }, new AttachmentMapper());
		return attachment;
	}

	@Override
	public boolean addAttatchment(Attachment attatchment) {
		String sql = "insert into attachment (invoice_id, image, create_time)"
				+ " values (?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, attatchment.getInvoiceId(),
				attatchment.getImage(), attatchment.getCreateTime());
		logger.debug("addAttatchment result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteAttatchment(Attachment attatchment) {
		String sql = "delete from attachment where invoice_id = ?";
		int affectedRows = jdbcTemplate.update(sql, attatchment.getInvoiceId());
		logger.debug("deleteAttatchment result : {}", affectedRows);
		return affectedRows != 0;
	}

}
