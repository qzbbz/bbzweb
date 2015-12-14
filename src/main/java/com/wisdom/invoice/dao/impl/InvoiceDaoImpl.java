package com.wisdom.invoice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.Invoice;
import com.wisdom.common.model.TestInvoice;
import com.wisdom.common.model.TestInvoiceRecord;
import com.wisdom.common.model.UserInvoice;
import com.wisdom.company.mapper.CompanyBillMapper;
import com.wisdom.invoice.dao.IInvoiceDao;
import com.wisdom.invoice.mapper.InvoiceMapper;
import com.wisdom.invoice.mapper.TestInvoiceMapper;
import com.wisdom.invoice.mapper.TestInvoiceRecordMapper;
import com.wisdom.invoice.mapper.UserInvoiceMapper;

@Repository("invoiceDao")
public class InvoiceDaoImpl implements IInvoiceDao {

	private static final Logger logger = LoggerFactory
			.getLogger(InvoiceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Invoice getInvoiceById(long id) {
		String sql = "select * from invoice where id = ?";
		try {
			Invoice invoice = jdbcTemplate.queryForObject(sql,
					new Object[] { id }, new InvoiceMapper());
			logger.debug("getInvoiceById");
			return invoice;
		} catch (DataAccessException e) {
			logger.error("getInvoiceById error" + e.getMessage());
		}
		return null;
	}
	@Override
	public Invoice getInvoiceByIdAndStatus(long invoiceId,int status){
		String sql = "select * from invoice where id = ? and commit_status = ?";
		try {
			Invoice invoice = jdbcTemplate.queryForObject(sql,
					new Object[] { invoiceId,status }, new InvoiceMapper());
			logger.debug("getInvoiceByIdAndStatus,invoiceId:" + invoiceId + ",status:" + status);
			return invoice;
		} catch (DataAccessException e) {
			logger.error("getInvoiceById error" + e.getMessage());
		}
		return null;
	}

	@Override
	public long addInvoiceAndGetKey(final Invoice invoice) {
		logger.debug("Enter addInvoiceAndGetKey");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String title = invoice.getTitle() == null ? "" : invoice.getTitle();
		String dateTime = invoice.getDate() == null ? 
				new Timestamp(System.currentTimeMillis()).toString() : String.valueOf(invoice.getDate());
		logger.debug("TITLE : " + title);
		logger.debug("DATE:" + dateTime);
		logger.debug("AMOUNT:" + invoice.getAmount());
		try {
			int id = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					String sql = "insert into invoice (expense_type_id,commit_status,title,amount,detail_desc,date,cost_center,create_time)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, invoice.getExpenseTypeId() == null? 0: invoice.getExpenseTypeId());
					ps.setInt(2, invoice.getStatus() == null? 0: invoice.getStatus());
					ps.setString(
							3,
							invoice.getTitle() == null ? "" : invoice
									.getTitle());
					ps.setDouble(4, invoice.getAmount()==null?0.0:invoice.getAmount());
					ps.setString(5, invoice.getDesc() == null?"":invoice.getDesc());
					ps.setTimestamp(
							6,
							invoice.getDate() == null ? 
									new Timestamp(System.currentTimeMillis()) : invoice.getDate());
					ps.setString(7, invoice.getCostCenter() == null?"":invoice.getCostCenter());
					ps.setTimestamp(
							8,
							invoice.getCreateTime() == null ?
									new Timestamp(System.currentTimeMillis()) : invoice.getCreateTime());
					return ps;
				}
			}, keyHolder);
			logger.debug("addInvoiceAndGetKey result : {}", keyHolder.getKey().longValue());
			return keyHolder.getKey().longValue();
		} catch (DataAccessException e) {
			logger.error("addInvoiceAndGetKey error." + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean deleteInvoice(Invoice invoice) {
		String sql = "delete from invoice where id = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, invoice.getId());
			logger.debug("deleteInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateInvoice(Invoice invoice) {
		String sql = "update invoice set title=?, invoice_code=?, amount=?, date=? ,detail_desc=? ,expense_type_id=? where id=?";
		try {
			int affectedRows = jdbcTemplate.update(
					sql,
					invoice.getTitle() == null ? "" : invoice.getTitle(),
					invoice.getInvoiceCode() == null ? "" : invoice.getInvoiceCode(),
					invoice.getAmount()==null?0:invoice.getAmount(),
					invoice.getDate() == null ? new Timestamp(System
							.currentTimeMillis()) : invoice.getDate(), 
					invoice.getDesc() == null? "" : invoice.getDesc(),
					invoice.getExpenseTypeId() == null?0 : invoice.getExpenseTypeId(), 
					invoice.getId());
					
			logger.debug("updateInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<Invoice> getUserInvoiceByStatus(String userId,String status){
		String sql = "select a.id, a.commit_status, a.invoice_code, a.expense_type_id, a.title, a.amount, a.detail_desc, a.date, a.cost_center, a.identify_status, a.create_time from user_invoice b, invoice a where b.user_id=? and b.invoice_id = a.id and a.commit_status=?";
		try {
			List list = jdbcTemplate.query(sql,
					new Object[] { userId,status },new RowMapperResultSetExtractor<Invoice>(
							new InvoiceMapper()));
			logger.debug("getInvoiceById");
			return list;
		} catch (DataAccessException e) {
			logger.error("getInvoiceById error" + e.getMessage());
		}
		return null;
	
	}
	@Override
	public List<Invoice> getUserInvoiceByStatusByPage(String userId,
			String status, int begin, int end) {
		
		String sql = "select a.id id,expense_type_id,commit_status,title,amount,detail_desc,date " +
				" from user_invoice b, invoice a where b.user_id=? and b.invoice_id = a.id and a.commit_status=? limit ? and ?";
	try {
		List list = jdbcTemplate.query(sql,
				new Object[] { userId,status,begin,end },new RowMapperResultSetExtractor<Invoice>(
						new InvoiceMapper()));
		logger.debug("getInvoiceById");
		return list;
	} catch (DataAccessException e) {
		logger.error("getInvoiceById error" + e.getMessage());
	}
		return null;
	}
	
	@Override
	public boolean updateInvoiceStatus(long invoiceId,int status) {
		String sql = "update invoice set commit_status=? where id=?";
		try {
			int affectedRows = jdbcTemplate.update(
					sql,status,invoiceId);
					
			logger.debug("updateInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Invoice getInvoiceByCode(String invoiceCode) {
		String sql = "select * from invoice where invoice_code = ? ";
		try {
			Invoice invoice = jdbcTemplate.queryForObject(sql,
					new Object[] { invoiceCode }, new InvoiceMapper());
			logger.debug("getInvoiceByCode,invoiceId:" + invoiceCode );
			return invoice;
		} catch (DataAccessException e) {
			logger.error("getInvoiceByCode error" + e.getMessage());
		}
		return null;
	}
	@Override
	public boolean updateInvoiceIdentifyStatus(long invoiceId, int status) {
		String sql = "update invoice set identify_status=? where id=?";
		try {
			int affectedRows = jdbcTemplate.update(
					sql,status,invoiceId);
					
			logger.debug("updateInvoiceIdentifyStatus result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteInvoiceByInvoiceId(long invoiceId) {
		String sql = "delete from invoice where id = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, invoiceId);
			logger.debug("deleteInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean setIsFAOfInvoice(long invoiceId, boolean isFA, String itemId) {
		String sql = "update test_invoice set is_fixed_assets = ? , modified_time = NOW(), item_id = ? where id = ?";
		Integer fA = 0;
		if(isFA){
			fA = 1;
		}
		try {
			int affectedRows = jdbcTemplate.update(sql, fA, itemId, invoiceId);
			logger.debug("updateInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean addInvoiceArtifact(long invoiceId, double amount, String type, String supplierName, String itemId) {
		String sql = "insert into test_invoice_artifact (invoice_id, amount, type, supplier_name, item_id, created_time) values (?, ?, ?, ?, ?, NOW())";
		try {
			int affectedRows = jdbcTemplate
					.update(sql,
							invoiceId,
							amount,
							type,
							supplierName,
							itemId);
			logger.debug("addInvoiceArtifact result : {}", affectedRows);
			return affectedRows != 0;
		} catch (Exception e) {
			logger.error("addInvoiceArtifact error:" + e.getMessage());
		}
		return false;
	}
	@Override
	public boolean deleteInvoiceArtifactByInvoiceId(long invoiceId) {
		String sql = "delete from test_invoice_artifact where invoice_id = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, invoiceId);
			logger.debug("deleteInvoiceArtifactByInvoiceId result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public long addInvoice(TestInvoice invoice) {
		logger.debug("Enter addInvoiceAndGetKey");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		//String title = invoice.getTitle() == null ? "" : invoice.getTitle();
		//String dateTime = invoice.getDate() == null ? 
		//		new Timestamp(System.currentTimeMillis()).toString() : String.valueOf(invoice.getDate());
		//logger.debug("DATE:" + dateTime);
		//logger.debug("AMOUNT:" + invoice.getAmount());
		try {
			int id = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					String sql = "insert into test_invoice (company_id, file_name, bill_date, is_fixed_assets, created_time, modified_time, cost_center)"
							+ " values (?, ?, ?, ?, NOW(), NOW(), ?)";
					PreparedStatement ps = connection.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, invoice.getCompanyId() == null? 0: invoice.getCompanyId());
					ps.setString(2, invoice.getFileName() == null? "": invoice.getFileName());
					ps.setString(3, invoice.getBillDate() == null? "": invoice.getBillDate());
					ps.setInt(4, invoice.getIsFixedAssets() == 0? 0: 1);
					ps.setString(5, invoice.getCostCenter() == null? "":invoice.getCostCenter());
					return ps;
				}
			}, keyHolder);
			logger.debug("addInvoiceAndGetKey result : {}", keyHolder.getKey().longValue());
			return keyHolder.getKey().longValue();
		} catch (DataAccessException e) {
			logger.error("addInvoiceAndGetKey error." + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	@Override
	public boolean removeRedundantInvoiceArtifact(Timestamp toTime) {
		String sql = "delete from test_invoice_artifact where id in (select aid from (select a.id as aid from test_invoice v left join test_invoice_artifact a on v.id = a.invoice_id where v.item_id <> a.item_id and (a.created_time < ? ) or a.created_time is null) as delete_items)";
		DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String toTimeStr = timeFormat.format(toTime);  
		try {
			int affectedRows = jdbcTemplate.update(sql, toTimeStr);
			logger.debug("deleteInvoiceArtifactByInvoiceId result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<TestInvoiceRecord> getAllInvoicesByCompanyId(long companyId) {

			List<TestInvoiceRecord> list = null;
			try {
				String sql = "select i.id as invoice_id, i.company_id as company_id, group_concat(a.type separator ', ') as type, sum(a.amount) as amount, i.created_time as created_time, i.is_fixed_assets as is_fixed_assets, i.bill_date as bill_date, a.supplier_name as supplier_name , i.file_name as file_name, i.status as status from test_invoice_artifact a  right join test_invoice i on i.item_id = a.item_id where  i.company_id = ? group by i.id";
				list = jdbcTemplate.query(sql, new Object[]{companyId}, 
						new RowMapperResultSetExtractor<TestInvoiceRecord>(
								new TestInvoiceRecordMapper()));
			} catch (Exception e) {
				logger.error(e.toString());
			}
			return list;
	}
	@Override
	public boolean updateInoviceStatus(long invoiceId, String status) {
		String sql = "update test_invoice set status = ? where id = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, status, invoiceId);
			logger.debug("updateInvoiceStatus result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<TestInvoiceRecord> getInvoicesByDate(String date, long companyId) {
		List<TestInvoiceRecord> list = null;
		try {
			String sql = "select i.id as invoice_id, i.company_id as company_id, group_concat(a.type separator ', ') as type, sum(a.amount) as amount, i.created_time as created_time, i.is_fixed_assets as is_fixed_assets, i.bill_date as bill_date, a.supplier_name as supplier_name , i.file_name as file_name, i.status as status from test_invoice_artifact a  right join test_invoice i on i.item_id = a.item_id where  i.company_id = ?";
			sql += " and i.created_time like '%" + date + "%' group by i.id";
			list = jdbcTemplate.query(sql, new Object[]{companyId}, 
					new RowMapperResultSetExtractor<TestInvoiceRecord>(
							new TestInvoiceRecordMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}
	@Override
	public boolean deleteTestInvoice(long invoiceId) {
		String sql = "delete from test_invoice where id = ?";
		try {
			int affectedRows = jdbcTemplate.update(sql, invoiceId);
			logger.debug("deleteTestInvoice result : {}", affectedRows);
			return affectedRows != 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public TestInvoiceRecord getInvoiceRecordById(long invoiceId) {
		TestInvoiceRecord record = null;
		try {
			String sql = "select i.id as invoice_id, i.company_id as company_id, group_concat(a.type separator ', ') as type, sum(a.amount) as amount, i.created_time as created_time, i.is_fixed_assets as is_fixed_assets, i.bill_date as bill_date, a.supplier_name as supplier_name , i.file_name as file_name, i.status as status from test_invoice_artifact a  right join test_invoice i on i.item_id = a.item_id where  i.id=? limit 1";
			record = (TestInvoiceRecord) jdbcTemplate.query(sql, new Object[]{invoiceId}, 
					new RowMapperResultSetExtractor<TestInvoiceRecord>(
							new TestInvoiceRecordMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return record;
	}
}
