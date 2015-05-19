package com.wisdom.company.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wisdom.common.model.SalarySocialSecurity;

public class SalarySocialSecurityMapper implements RowMapper<SalarySocialSecurity> {
	public SalarySocialSecurity mapRow(ResultSet rs, int index) throws SQLException {
		SalarySocialSecurity u = new SalarySocialSecurity(
				rs.getLong("id"), rs.getLong("company_id"),
				rs.getString("city_name"), rs.getInt("registry_type"), 
				rs.getString("pension_cratio"), rs.getString("pension_pratio"), rs.getString("pension_base"),
				rs.getString("medical_cratio"), rs.getString("medical_pratio"), rs.getString("medical_base"),
				rs.getString("unemploy_cratio"), rs.getString("unemploy_pratio"), rs.getString("unemploy_base"),
				rs.getString("injury_cratio"), rs.getString("injury_pratio"), rs.getString("injury_base"),
				rs.getString("birth_cratio"), rs.getString("birth_pratio"), rs.getString("birth_base"),
				rs.getString("accfund_cratio"), rs.getString("accfund_pratio"), rs.getString("accfund_base"),
				rs.getString("salary_cratio"), rs.getString("salary_pratio"), rs.getString("salary_base"),
				rs.getString("bigmedical_cratio"), rs.getString("bigmedical_pratio"), rs.getString("bigmedical_base"),
				rs.getString("template"), rs.getTimestamp("create_time"));
		return u;
	}
}
