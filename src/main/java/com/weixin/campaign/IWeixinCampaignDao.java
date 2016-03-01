package com.weixin.campaign;

import java.util.List;

public interface IWeixinCampaignDao {

	public List<WeixinCampaignSubjectModel> getSubjects(String subjectDate);
	
	public boolean checkAndUpdateRecord(WeixinCampaignUserModel wcum);
	
	public boolean updateRecord(WeixinCampaignUserModel wcum);
	
	public boolean insertRecord(WeixinCampaignUserModel wcum);
	
	public List<WeixinCampaignUserModel> getAllUsers();
	
	public void resetAllUserAnswerStatus();
	
	public WeixinCampaignUserModel getUserModelByOpenId(String openId);
}
