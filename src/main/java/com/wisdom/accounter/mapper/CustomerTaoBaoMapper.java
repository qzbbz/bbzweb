package com.wisdom.accounter.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.AccounterCareer;
import com.wisdom.common.model.CustomerManagement;
import com.wisdom.common.model.CustomerTaoBao;

public class CustomerTaoBaoMapper implements RowMapper<CustomerTaoBao> {
	public CustomerTaoBao mapRow(ResultSet rs, int index) throws SQLException {
		CustomerTaoBao c = new CustomerTaoBao(rs.getLong("id"), rs.getLong("company_id"), rs.getString("file_name"),rs.getString("taobao_accounter"),
				rs.getTimestamp("create_time"));
		return c;
	}
}
