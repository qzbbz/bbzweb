package com.wisdom.sales.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Sales;
import com.wisdom.common.model.SalesComment;
import com.wisdom.sales.dao.ISalesCommentDao;
import com.wisdom.sales.mapper.SalesCommentMapper;
import com.wisdom.sales.mapper.SalesMapper;


@Repository("salesCommentDao")
public class SalesCommentDaoImpl implements ISalesCommentDao {

	private static final Logger logger = LoggerFactory
			.getLogger(SalesCommentDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<SalesComment> getAllSalesCommentsBySalesId(Integer salesId) {
		List<SalesComment> list = null;
		try {
			String sql = "select * from sales_comment where sales_id = '" + salesId.toString() + "'";
			list = jdbcTemplate.query(sql, 
					new RowMapperResultSetExtractor<SalesComment>(
							new SalesCommentMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public Boolean addSalesComment(SalesComment comment) {
		String sql = "insert into sales_comment (sales_id, comment, updated_time)"
				+ " values (?, ?, NOW())";
		int affectedRows = jdbcTemplate.update(sql, comment.getSalesId(),
				comment.getComment());
		logger.debug("addRecommender result : {}", affectedRows);
		return affectedRows != 0;
		
	}


}
