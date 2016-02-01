package com.weixin.campaign;

import java.util.List;

public interface IWeixinCampaignDao {

	public List<WeixinCampaignSubjectModel> getSubjects(int subjectCount);
	
	public boolean checkAndUpdateRecord(WeixinCampaignUserModel wcum);
	
	public List<WeixinCampaignUserModel> getAllUsers();
	
	public void resetAllUserAnswerStatus();
}
