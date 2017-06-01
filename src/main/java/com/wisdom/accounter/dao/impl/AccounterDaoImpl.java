package com.wisdom.accounter.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.accounter.dao.IAccounterDao;
import com.wisdom.accounter.mapper.AccounterMapper;
import com.wisdom.accounter.mapper.CustomerManagementMapper;
import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.CustomerManagement;
import com.wisdom.common.model.CustomerTaoBao;

@Repository("accounterDao")
public class AccounterDaoImpl implements IAccounterDao {

	private static final Logger logger = LoggerFactory
			.getLogger(AccounterDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Accounter getAccounterByUserId(String userId) {
		String sql = "select * from accounter where user_id = ?";
		Accounter user = null;
		try {
			user = jdbcTemplate.queryForObject(sql,
					new Object[] { userId }, new AccounterMapper());
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return user;
	}

	@Override
	public List<Accounter> getAccounterByCompanyId(long companyId) {
		List<Accounter> list = null;
		try {
			String sql = "select * from accounter where companyId=?";
			list = jdbcTemplate.query(sql, new Object[] { companyId },
					new RowMapperResultSetExtractor<Accounter>(
							new AccounterMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addAccounter(Accounter accounter) {
		String sql = "insert into accounter (user_id, area, city, province, image, certificate, industry, career, create_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, accounter.getUserId(),
				accounter.getArea(), accounter.getCity(),
				accounter.getProvince(), accounter.getImage(),
				accounter.getCertificate(), accounter.getIndustry(),
				accounter.getCareer(), accounter.getCreateTime());
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteAccounter(Accounter accounter) {
		String sql = "delete from accounter where id = ?";
		int affectedRows = jdbcTemplate.update(sql, accounter.getId());
		logger.debug("deleteAccounter result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateAccounter(Accounter accounter) {
		String sql = "update accounter set area=?, city=?, province=?, image=?, certificate=?, industry=?, career=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, 
				accounter.getArea(), accounter.getCity(),
				accounter.getProvince(), accounter.getImage(),
				accounter.getCertificate(), accounter.getIndustry(),
				accounter.getCareer(), accounter.getUserId());
		logger.debug("updateAccounter result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean isAccounterExistByUserId(String userId) {
		String sql = "select * from accounter where user_id = ?";
		Accounter user = null;
		try {
			user = jdbcTemplate.queryForObject(sql,
					new Object[] { userId }, new AccounterMapper());
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return user != null ? true : false;
	}

	@Override
	public List<Accounter> getAllAccounter() {
		List<Accounter> list = null;
		try {
			String sql = "select * from accounter";
			list = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<Accounter>(
							new AccounterMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<Accounter> getAllAccounterByCondition(String province,
			String city, String area, String industry, String career) {
		List<Accounter> list = null;
		try {
			String sql = "select * from accounter where province=? and city=? and area=? and industry=? and career=?";
			list = jdbcTemplate.query(sql, new Object[] { province, city, area, industry, career },
					new RowMapperResultSetExtractor<Accounter>(
							new AccounterMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<CustomerManagement> getAllCustomer() {
		List<CustomerManagement> list = null;
		try {
			//String sql = "SELECT * from(SELECT c1.id,c1.name,s.create_time,c1.tax_status,c2.expired_time,u.user_name FROM company AS c1 LEFT JOIN company_pay AS c2 ON c1.id = c2.company_id LEFT JOIN sheet_balance AS s ON c1.id = s.company_id LEFT JOIN `user` AS u ON c1.accounter_id = u.user_id WHERE LENGTH(c1.accounter_id)<>0  ORDER  BY s.create_time DESC ) AS ss  GROUP BY ss.name";
			String sql = "select * from (SELECT * from(SELECT c1.id,c1.name,s.create_time,c1.tax_status,c2.expired_time,u.user_name FROM company AS c1 LEFT JOIN company_pay AS c2 ON c1.id = c2.company_id LEFT JOIN sheet_balance AS s ON c1.id = s.company_id LEFT JOIN `user` AS u ON c1.accounter_id = u.user_id WHERE LENGTH(c1.accounter_id)<>0  ORDER  BY s.create_time DESC ) AS ss  GROUP BY ss.name) as final LEFT JOIN (select *, count(ctb.company_id) as comment_count from customer_taobao as ctb where date_format(ctb.create_time, '%Y-%m-%d') >= date_format(date_sub(sysdate(), interval 1 month), '%Y-%m-%d') group by ctb.company_id) as tmp on final.id=tmp.company_id";
			list = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<CustomerManagement>(
							new CustomerManagementMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllCompanyExpense(String userId, int start, int length) {
		// TODO Auto-generated method stub
		String sql = "select c.name, date_format(ti.created_time,'%Y-%m-%d') as created_time, ti.file_name, tia.type, sum(tia.amount) as amount, '公司发票' as item_type from company c inner join (test_invoice ti left join test_invoice_artifact tia on ti.item_id=tia.item_id) on c.id=ti.company_id where c.accounter_id='" + userId + "' group by ti.id"
				+ " union all ("
				+ "select c.name, date_format(cbs.create_time,'%Y-%m-%d') as created_time, cbs.file_name, '无' as type, 0.0 as amount, '公司银行对账单' as item_type from company c inner join company_bank_sta cbs on c.id=cbs.company_id where c.accounter_id='" + userId + "'"
				+ ") union all ("
				+ "select c.name, date_format(cs.create_time,'%Y-%m-%d') as created_time, cs.file_name, '无' as type, 0.0 as amount, '公司销售清单' as item_type  from company c inner join company_sales cs on c.id=cs.company_id where c.accounter_id='" + userId + "'"
				+ ") union all ("
				+ "select c.name, date_format(cs.create_time,'%Y-%m-%d') as created_time, cs.salary_file as file_name, '无' as type, 0.0 as amount, '公司工资单' as item_type from company c inner join company_salary cs on c.id=cs.company_id where c.accounter_id='" + userId + "'"
				+ ") order by created_time desc limit " + start + "," + length;
		logger.debug("sql : {}", sql);
		
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
		return result;
	}

	@Override
	public int getAllCompanyExpenseRecordTotal(String userId) {
		String sql = "select count(*) from (select c.name, ti.created_time, ti.file_name, tia.type, sum(tia.amount) as amount, '公司发票' as item_type from company c inner join (test_invoice ti left join test_invoice_artifact tia on ti.item_id=tia.item_id) on c.id=ti.company_id where c.accounter_id='" + userId + "' group by ti.id"
				+ " union all ("
				+ "select c.name, cbs.create_time as created_time, cbs.file_name, '无' as type, 0.0 as amount, '公司银行对账单' as item_type from company c inner join company_bank_sta cbs on c.id=cbs.company_id where c.accounter_id='" + userId + "'"
				+ ") union all ("
				+ "select c.name, cs.create_time as created_time, cs.file_name, '无' as type, 0.0 as amount, '公司销售清单' as item_type  from company c inner join company_sales cs on c.id=cs.company_id where c.accounter_id='" + userId + "'"
				+ ") union all ("
				+ "select c.name, cs.create_time as created_time, cs.salary_file as file_name, '无' as type, 0.0 as amount, '公司工资单' as item_type from company c inner join company_salary cs on c.id=cs.company_id where c.accounter_id='" + userId + "'"
				+ ")) record";
		logger.debug("sql : {}", sql);
		int total = jdbcTemplate.queryForObject(sql, Integer.class);
		return total;
	}

	@Override
	public List<Map<String, Object>> getAllCompanyExpenseDataTableByCondition(String userId, Map<String, String> conditionMap, int start, int length) {
		String sql = "select * from (select c.name, date_format(ti.created_time, '%Y-%m-%d') as created_time, ti.file_name, tia.type, sum(tia.amount) as amount, '公司发票' as item_type from company c inner join (test_invoice ti left join test_invoice_artifact tia on ti.item_id=tia.item_id) on c.id=ti.company_id where c.accounter_id='" + userId + "' group by ti.id"
				+ " union all ("
				+ "select c.name, date_format(cbs.create_time, '%Y-%m-%d') as created_time, cbs.file_name, '无' as type, 0.0 as amount, '公司银行对账单' as item_type from company c inner join company_bank_sta cbs on c.id=cbs.company_id where c.accounter_id='" + userId + "'"
				+ ") union all ("
				+ "select c.name, date_format(cs.create_time, '%Y-%m-%d') as created_time, cs.file_name, '无' as type, 0.0 as amount, '公司销售清单' as item_type  from company c inner join company_sales cs on c.id=cs.company_id where c.accounter_id='" + userId + "'"
				+ ") union all ("
				+ "select c.name, date_format(cs.create_time, '%Y-%m-%d') as created_time, cs.salary_file as file_name, '无' as type, 0.0 as amount, '公司工资单' as item_type from company c inner join company_salary cs on c.id=cs.company_id where c.accounter_id='" + userId + "'"
				+ ")) total where ";
		String conditions = "";
		if(!StringUtils.isEmpty(conditionMap.get("companyName"))) {
			if(!StringUtils.isEmpty(conditions)) {
				conditions = conditions + " and ";
			}
			conditions = conditions + "total.name like '%" + conditionMap.get("companyName") + "%'";
		}
		
		if(!StringUtils.isEmpty(conditionMap.get("created_time"))) {
			if(!StringUtils.isEmpty(conditions)) {
				conditions = conditions + " and ";
			}
			conditions = conditions + "total.created_time like '%" + conditionMap.get("created_time") + "%'";
		}
		
		if(!StringUtils.isEmpty(conditionMap.get("item_type"))) {
			if(!StringUtils.isEmpty(conditions)) {
				conditions = conditions + " and ";
			}
			conditions = conditions + "total.item_type like '%" + conditionMap.get("item_type") + "%'";
		}
		
		sql = sql + conditions + " order by created_time desc limit "+ start + "," + length;
		logger.debug("sql : {}", sql);
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
		logger.debug("query result : {}", result.size());
		return result;
	}

	@Override
	public int getAllCompanyExpenseByConditionRecordTotal(String userId, Map<String, String> conditionMap) {
		String sql = "select count(*) from (select * from (select c.name, ti.created_time, ti.file_name, tia.type, sum(tia.amount) as amount, '公司发票' as item_type from company c inner join (test_invoice ti left join test_invoice_artifact tia on ti.item_id=tia.item_id) on c.id=ti.company_id where c.accounter_id='" + userId + "' group by ti.id"
				+ " union all ("
				+ "select c.name, cbs.create_time as created_time, cbs.file_name, '无' as type, 0.0 as amount, '公司银行对账单' as item_type from company c inner join company_bank_sta cbs on c.id=cbs.company_id where c.accounter_id='" + userId + "'"
				+ ") union all ("
				+ "select c.name, cs.create_time as created_time, cs.file_name, '无' as type, 0.0 as amount, '公司销售清单' as item_type  from company c inner join company_sales cs on c.id=cs.company_id where c.accounter_id='" + userId + "'"
				+ ") union all ("
				+ "select c.name, cs.create_time as created_time, cs.salary_file as file_name, '无' as type, 0.0 as amount, '公司工资单' as item_type from company c inner join company_salary cs on c.id=cs.company_id where c.accounter_id='" + userId + "'"
				+ ")) total where ";
		String conditions = "";
		if(!StringUtils.isEmpty(conditionMap.get("companyName"))) {
			if(!StringUtils.isEmpty(conditions)) {
				conditions = conditions + " and ";
			}
			conditions = conditions + "total.name like '%" + conditionMap.get("companyName") + "%'";
		}
		
		if(!StringUtils.isEmpty(conditionMap.get("created_time"))) {
			if(!StringUtils.isEmpty(conditions)) {
				conditions = conditions + " and ";
			}
			conditions = conditions + "total.created_time like '%" + conditionMap.get("created_time") + "%'";
		}
		
		if(!StringUtils.isEmpty(conditionMap.get("item_type"))) {
			if(!StringUtils.isEmpty(conditions)) {
				conditions = conditions + " and ";
			}
			conditions = conditions + "total.item_type like '%" + conditionMap.get("item_type") + "%'";
		}
		
		sql = sql + conditions + ") recordTotal";
		logger.debug("sql : {}", sql);
		int total = jdbcTemplate.queryForObject(sql, Integer.class);
		return total;
	}
	
	public boolean addCustomerComment(CustomerTaoBao ctb) {
		String sql = "insert into customer_taobao (company_id, file_name, taobao_accounter, create_time)"
				+ " values (?, ?, ?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, ctb.getCompanyId(),
				ctb.getFileName(), ctb.getTaobaoAccounter(), ctb.getCreateTime());
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public int getCustomerTaoBaoCountByMonth(long companyId, int type) {
		int count = 0;
		try {
			//String sql = "SELECT * from(SELECT c1.id,c1.name,s.create_time,c1.tax_status,c2.expired_time,u.user_name FROM company AS c1 LEFT JOIN company_pay AS c2 ON c1.id = c2.company_id LEFT JOIN sheet_balance AS s ON c1.id = s.company_id LEFT JOIN `user` AS u ON c1.accounter_id = u.user_id WHERE LENGTH(c1.accounter_id)<>0  ORDER  BY s.create_time DESC ) AS ss  GROUP BY ss.name";
			String sql = "select count(*) from customer_taobao as ctb where ctb.company_id=? and date_format(ctb.create_time, '%Y-%m-%d') >= date_format(date_sub(sysdate(), interval ? month), '%Y-%m-%d')";
			count = jdbcTemplate.queryForObject(sql, new Object[] { companyId, type }, Integer.class);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return count;
	}

}
