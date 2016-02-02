package com.weixin.campaign;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.User;
import com.wisdom.user.mapper.UserMapper;

@Repository("weixinCampaignDao")
public class WeixinCampaignDaoImpl implements IWeixinCampaignDao {

	private static final Logger logger = LoggerFactory.getLogger(WeixinCampaignDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<WeixinCampaignSubjectModel> getSubjects(String subjectDate) {
		List<WeixinCampaignSubjectModel> list = null;
		try {
			String sql1 = "select * from weixin_campaign_subject where subject_date = ?";
			list = jdbcTemplate.query(sql1, new Object[]{subjectDate}, 
					new RowMapperResultSetExtractor<WeixinCampaignSubjectModel>(new WeixinCampaignSubjectMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean checkAndUpdateRecord(WeixinCampaignUserModel wcum) {
		String sql = "replace into weixin_campaign_user (user_id, user_name, finish_count, right_count, update_time, create_time)"
				+ " values (?, ?, ?, ?, ?, ?)";
		int ret = 0;
		try {
			ret = jdbcTemplate.update(sql, wcum.getUserId(), wcum.getUserName(), wcum.getFinishCount(),
					wcum.getRightCount(), wcum.getUpdateTime(), wcum.getCreateTime());
		} catch (Exception e) {
			logger.error(e.toString());
		}
		logger.debug("checkAndUpdateRecord result : {}", ret);
		return ret != 0;
	}

	@Override
	public List<WeixinCampaignUserModel> getAllUsers() {
		List<WeixinCampaignUserModel> list = null;
		try {
			String sql = "select * from weixin_campaign_user order by right_count desc, answer_rate desc";
			list = jdbcTemplate.query(sql,
					new RowMapperResultSetExtractor<WeixinCampaignUserModel>(new WeixinCampaignUserMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public void resetAllUserAnswerStatus() {
		String sql = "update weixin_campaign_user set has_answer = 0";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql);
		} catch(Exception ex) {
			logger.error(ex.toString());
		}
		logger.debug("resetAllUserAnswerStatus result : {}", affectedRows);	
	}

	@Override
	public WeixinCampaignUserModel getUserModelByOpenId(String openId) {
		String sql = "select * from weixin_campaign_user where user_id = ?";
		WeixinCampaignUserModel user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[] { openId },
					new WeixinCampaignUserMapper());
		} catch (Exception e) {
			logger.error("result is 0, exception : " + e.toString());
		}
		return user;
	}

	@Override
	public boolean updateRecord(WeixinCampaignUserModel wcum) {
		String sql = "update weixin_campaign_user set finish_count=?, right_count=?, update_time=?, answer_rate=?, has_answer=? where user_id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, wcum.getFinishCount(), 
					wcum.getRightCount(), wcum.getUpdateTime(), wcum.getAnswerRate(),
					wcum.getHasAnswer(), wcum.getUserId());
		} catch(Exception ex) {
			logger.error(ex.toString());
		}
		logger.debug("insertRecord : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean insertRecord(WeixinCampaignUserModel wcum) {
		String sql = "insert into weixin_campaign_user (user_id, user_name, finish_count, right_count, update_time, answer_rate, has_answer, create_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, wcum.getUserId(),
					wcum.getUserName(), wcum.getFinishCount(), wcum.getRightCount(),
					wcum.getUpdateTime(), wcum.getAnswerRate(), wcum.getHasAnswer(),
					wcum.getCreateTime());
		} catch(Exception ex) {
			logger.error(ex.toString());
		}
		logger.debug("insertRecord : {}", affectedRows);
		return affectedRows != 0;
	}
}
