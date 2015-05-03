package com.wisdom.dispatch.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.ChannelType;
import com.wisdom.common.model.Dispatcher;
import com.wisdom.dispatch.dao.IDispatcherDao;
import com.wisdom.dispatch.mapper.ChannelTypeMapper;
import com.wisdom.dispatch.mapper.DispatcherMapper;

@Repository("dispatcherDao")
public class DispatcherDaoImpl implements IDispatcherDao {

	private static final Logger logger = LoggerFactory
			.getLogger(DispatcherDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Dispatcher> getDispatcherByStatus(int status) {
		List<Dispatcher> list = null;
		try {
			String sql = "select * from dispatcher where status=?";
			list = jdbcTemplate.query(sql, new Object[]{status},
					new RowMapperResultSetExtractor<Dispatcher>(
							new DispatcherMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}
	
	@Override
	public List<Dispatcher> getDispatcherByStatusAndNum(int status,int num) {
		List<Dispatcher> list = null;
		try {
			String sql = "select * from dispatcher where status=? order by create_time desc limit ?";
			list = jdbcTemplate.query(sql, new Object[]{status,num},
					new RowMapperResultSetExtractor<Dispatcher>(
							new DispatcherMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addDispatcher(Dispatcher dispatcher) {
		String sql = "insert into dispatcher (object_type_id, invoice_id, channel_type_id, status, reciever, create_time, update_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, dispatcher.getObjectTypeId(),
				dispatcher.getInvoiceId(), dispatcher.getChannelTypeId(),
				dispatcher.getStatus(), dispatcher.getReciever(),
				dispatcher.getCreateTime(), dispatcher.getUpdateTime());
		logger.debug("addDispatcher result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateDispatcherByInvoiceId(Dispatcher dispatcher) {
		String sql = "update dispatcher set status=?, update_time=? where invoice_id=?";
		int affectedRows = jdbcTemplate.update(sql, dispatcher.getStatus(),
				dispatcher.getUpdateTime(), dispatcher.getInvoiceId());
		logger.debug("updateDispatcherByInvoiceId result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public Dispatcher getDispatcherByInvoiceId(long invoiceId) {
		String sql = "select * from dispatcher where invoice_id = ?";
		try{
			Dispatcher record = jdbcTemplate.queryForObject(sql, new Object[] { invoiceId },
					new DispatcherMapper());
			return record;
		}catch(Exception e){
			logger.error("getChannelTypeById error:");
		}
		return null;
	}

}
