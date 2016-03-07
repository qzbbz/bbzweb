package com.wisdom.sales.mapper;


	import java.sql.ResultSet;
	import java.sql.SQLException;

	import org.springframework.jdbc.core.RowMapper;


import com.wisdom.common.model.Sales;

	public class SalesMapper  implements RowMapper<Sales> {
		public Sales mapRow(ResultSet rs, int index) throws SQLException {
			Sales u = new Sales(rs.getInt("id"),
					rs.getString("saller_account"), rs.getString("user_name"), 
					rs.getString("user_company"), rs.getString("user_phone"), 
					rs.getString("latest_comment"), rs.getString("accountant"), 
					rs.getString("updated_time"), rs.getString("status"), 
					rs.getString("accountant_id"), rs.getInt("has_send_email"), rs.getString("saller_id"));
			return u;
		}
	}

