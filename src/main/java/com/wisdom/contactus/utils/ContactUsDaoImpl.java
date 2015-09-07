package com.wisdom.contactus.utils;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wisdom.common.model.ContactUs;
import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.user.dao.IUserDeptDao;
import com.wisdom.user.mapper.UserDeptMapper;

@Repository("contactusDao")
public class ContactUsDaoImpl implements IContactUsDao {

	private static final Logger logger = LoggerFactory
			.getLogger(ContactUsDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean addContactUs(ContactUs contactUs) {
		String sql = "insert into contactus (user_name, user_phone, user_company_name, create_time)"
				+ " values (?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql,
				contactUs.getUserName() == null ? "" : contactUs.getUserName(),
				contactUs.getUserPhone() == null ? "" : contactUs.getUserPhone(),
				contactUs.getUserCompanyName() == null ? "" : contactUs.getUserCompanyName(),
				new Timestamp(System.currentTimeMillis()));
		logger.debug("addContactUs result : {}", affectedRows);
		return affectedRows != 0;

	}

}