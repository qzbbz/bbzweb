package com.wisdom.company.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyInfo;
import com.wisdom.common.model.ExpenseType;
import com.wisdom.company.dao.ICompanyDao;
import com.wisdom.company.mapper.CompanyInfoMapper;
import com.wisdom.company.mapper.CompanyMapper;
import com.wisdom.company.mapper.ExpenseTypeMapper;

@Repository("companyDao")
public class CompanyDaoImpl implements ICompanyDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Company getCompanyByCompanyId(long companyId) {
		logger.debug("companyId : {}", companyId);
		String sql = "select * from company where id = ?";
		Company company = null;
		try {
			company = jdbcTemplate.queryForObject(sql,
				new Object[] { companyId }, new CompanyMapper());
		} catch(Exception e) {
			logger.debug("result is 0.");
		}
		return company;
	}

	@Override
	public long addCompany(Company company) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		long id = jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
	        	String sql = "insert into company (name, parent_id, month_expense, perfect_moment, company_code,create_time)"
	    				+ " values (?, ?, ?, ?, ?, ?)";
	               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
	               ps.setString(1, company.getName());  
	               ps.setLong(2, company.getParentId());  
	               ps.setString(3, company.getMonthExpense());
	               ps.setString(4, company.getPerfectMoment());
	               ps.setString(5, company.getCompanyCode());
	               ps.setTimestamp(6, company.getCreateTime());
	               return ps;  
	        }  
	    }, keyHolder);
		logger.debug("addCompany result : {}", keyHolder.getKey().longValue());
		return keyHolder.getKey().longValue();
	}

	@Override
	public boolean deleteCompany(Company company) {
		String sql = "delete from company where id = ?";
		int affectedRows = jdbcTemplate.update(sql, company.getId());
		logger.debug("deleteCompany result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompany(Company company) {
		String sql = "update company set name=?, parent_id=?, month_expense=?, perfect_moment=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, company.getName(),
				company.getParentId(), company.getMonthExpense(),
				company.getPerfectMoment(), company.getId());
		logger.debug("updateCompany result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyName(String companyName, long companyId) {
		String sql = "update company name set name=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, companyName, companyId);
		logger.debug("updateCompanyName result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<Company> getSubCompanyListByCompanyId(long companyId) {
		List<Company> list = null;
		try {
			String sql = "select * from company where parent_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId},
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}
	
	@Override
	public List<Company> getSubCompanyListByCompanyIdOrder(long companyId) {
		List<Company> list = null;
		try {
			String sql = "select * from company where parent_id=? order by company_code desc";
			list = jdbcTemplate.query(sql, new Object[]{companyId},
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<Company> getAllCompany() {
		List<Company> list = null;
		try {
			String sql = "select * from company";
			list = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean updateCompanyTakeType(long companyId, String takeType) {
		String sql = "update company set take_type=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, takeType, companyId);
		logger.debug("updateCompanyTakeType result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyAccounter(long companyId, String accounterId) {
		String sql = "update company set accounter_id=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, accounterId, companyId);
		logger.debug("updateCompanyAccounter result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<Company> getCompanyListByAccounterId(String accounterId) {
		List<Company> list = null;
		try {
			String sql = "select * from company where accounter_id=?";
			list = jdbcTemplate.query(sql, new Object[]{accounterId},
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<Company> getCompanyAndAccounter() {
		List<Company> companyList = null;
		try {
			String sql = "select * from company c left join user u on c.id = u.company_id where u.type_id = 2";
			companyList = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return companyList;
	}
	//update accounter_id from company table
	@Override
	public boolean updateAccounterForCompany(long companyId, String accounterId, String taxpayer_type, String invoice_amount, String vice_accounter_id) {
		String sql="update company set accounter_id=?, taxpayer_type=?, invoice_amount=?, vice_accounter_id=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, accounterId, taxpayer_type, invoice_amount, vice_accounter_id, companyId);
		logger.debug("updateCompanyAccounter accounter_id result : {}", affectedRows);
		return  affectedRows != 0;
	}
	//get company information by name
	@Override
	public List<Company> getCompanyByName(String companyName) {
		List<Company> companyList = null;
		String sql="select * from company where name = ?";
		try{
			companyList=jdbcTemplate.query(sql, new Object[]{companyName},
					new RowMapperResultSetExtractor<Company>(
							new CompanyMapper()));
		}catch(Exception e){
			logger.error(e.toString());
		}
		return companyList;
	}

	@Override
	public List<CompanyInfo> getCompanyInfoAndUserIDAndPhone() {
		List<CompanyInfo> companyList = null;
		try {
			String sql = "select distinct c.id,c.name,c.perfect_moment,c.month_expense,c.create_time,u.user_id, u.audit_status,u.type_id,up.phone  from  (company c left join user u on c.id = u.company_id AND u.type_id = 2) LEFT JOIN user_phone up on u.user_id=up.user_id WHERE up.phone is NOT NULL ";
			companyList = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<CompanyInfo>(
							new CompanyInfoMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return companyList;
	}

	@Override
	public List<Map<String, Object>> getCompanyAndAccounterByKey(String key) {
		List<Map<String, Object>> companyList = null;
		String sql="select * from company c left join user u on c.id = u.company_id where u.type_id = 2 and c.name like '%"+key+"%' order by c.create_time desc";
		try{
			companyList=jdbcTemplate.queryForList(sql);
		}catch(Exception e){
			logger.error(e.toString());
		}
		return companyList;
	}

	@Override
	public List<Map<String, Object>> getComByIndexAndKey(int i, int length, String key) {
		List<Map<String, Object>> companyList = null;
		String sql="select company.id, company.accounter_id, company.vice_accounter_id, company.name, company.taxpayer_type, company.invoice_amount,user.user_name, vuser.user_name as vice_accounter, company_phone.phone from company left join user on company.accounter_id=user.user_id LEFT JOIN user as vuser on company.vice_accounter_id=vuser.user_id LEFT JOIN (select user_phone.phone,company.id from company LEFT JOIN user on user.type_id=2 and user.company_id=company.id LEFT JOIN user_phone on user_phone.user_id=user.user_id) as company_phone on company_phone.id=company.id where company.name like '%"+key+"%' or company.id like '%"+key+"%' ORDER BY company.create_time desc limit ?,?";
		try{
			companyList=jdbcTemplate.queryForList(sql, i, length);
		}catch(Exception e){
			logger.error(e.toString());
		}
		return companyList;
	}

	@Override
	public List<Map<String, Object>> getComPayTotalPageByKey(String key) {
		List<Map<String, Object>> companyList = null;
		String sql="select a.id, a.name, b.pay_amount, b.service_time, b.create_time, b.expired_time from company a left join company_pay b on a.id = b.company_id where a.parent_id = -1 and (a.name like '%"+key+"%' or a.id like '%" + key + "%')";
		try{
			companyList=jdbcTemplate.queryForList(sql);
		}catch(Exception e){
			logger.error(e.toString());
		}
		return companyList;
	}

	@Override
	public List<Map<String, Object>> getComPayInfoByIndexAndKey(int i, int length, String key) {
		List<Map<String, Object>> companyList = null;
		String sql="select a.id, a.name, b.pay_amount, b.service_time, b.create_time, DATE_FORMAT(b.expired_time,'%Y-%m-%d') as expired_time, b.gongshangnianjian, b.gongshangnianjian_expired_time, b.huisuanqingjiao, b.huisuanqingjiao_expired_time from company a left join company_pay b on a.id = b.company_id where a.parent_id = -1 and (a.name like '%"+key+"%' or a.id like '%"+key+"%') order by a.create_time desc limit ?,?";
		try{
			companyList=jdbcTemplate.queryForList(sql, i, length);
		}catch(Exception e){
			logger.error(e.toString());
		}
		return companyList;
	}

}
