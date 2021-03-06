package com.wisdom.recommender.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.recommender.dao.IRecommenderDao;
import com.wisdom.recommender.dao.IRecommendRecordDao;
import com.wisdom.recommender.dao.IRecommendInfoDao;
import com.wisdom.recommender.service.IRecommendService;
import com.wisdom.common.model.Recommender;
import com.wisdom.common.model.User;
import com.wisdom.common.model.AccounterIndustry;
import com.wisdom.common.model.RecommendRecord;
import com.wisdom.common.model.RecommendInfo;
import com.wisdom.user.dao.IUserQueryDao;

@Service("recommendService")
public class RecommendServiceImpl implements IRecommendService {

	@Autowired
	private IRecommenderDao recommenderDao;
	@Autowired
	private IRecommendRecordDao recommendRecordDao;
	@Autowired
	private IUserQueryDao userQueryDao;
	@Autowired
	private IRecommendInfoDao recommendInfoDao;
	
	@Override
	public Boolean addRecommender(Recommender recommender) {
		return recommenderDao.addRecommender(recommender);

	}
	@Override
	public Map<String, List<String>> getAllRecommender() {
		Map<String, List<String>> recommenderMap = new HashMap<>();
		List<Recommender> recommenderList = recommenderDao.getAllRecommender();
		//List<Recommender> recommenderList = recommenderDao.getAllRecommender();
		if (recommenderList != null && recommenderList.size() > 0) {
			for (Recommender recommender : recommenderList) {
			    String recommenderId = recommender.getId();
			    List<RecommendInfo> recommenderInfoList = recommendInfoDao.getRecommendInfoByRecommenderId(recommenderId);
			    List<String> info = new ArrayList<>();
			    info.add(recommender.getName());
			    if(recommenderInfoList == null){
			    	info.add("0");
			    }
			    else{
			    	info.add(String.valueOf(recommenderInfoList.size()));
			    }
			    recommenderMap.put(recommenderId, info);
			}
		}
		return recommenderMap;
	}

	@Override
	public Map<String, List<String>> getAllRecommendRecord() {
		Map<String, List<String>> recommendRecordMap = new HashMap<>();
		List<RecommendRecord> recommendRecordList = recommendRecordDao.getAllRecommendRecord();
		if (recommendRecordList != null && recommendRecordList.size() > 0) {
			for (RecommendRecord recommendRecord : recommendRecordList) {
				List<String> list = new ArrayList();
				list.add(recommenderDao.getRecommenderById(recommendRecord.getRecommenderId()).getName());
				String timestamp = recommendRecord.getCreatedTime().toString();
				list.add(timestamp);
				list.add(recommendRecord.getIsPaid().toString());
				recommendRecordMap.put(recommendRecord.getCustomerEmail(), list);
			}
		}
		return recommendRecordMap;
	}
	
	@Override
	public Boolean addRecommendRecord(RecommendRecord recommendRecord) {
		return recommendRecordDao.addRecommendRecord(recommendRecord);
	}
	
	@Override
	public Boolean isRecommenderExisted(String id) {
		if (recommenderDao.getRecommenderById(id) != null) {
			return true;
		}
		else {
			return false;
			
		}
	}
	@Override
	public Boolean isRecommendRecordExisted(String email) {
		RecommendRecord recommendRecord = recommendRecordDao.getRecommendRecordByEmail(email);
		if(recommendRecord != null){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public Boolean isCustomerPaid(String email) {
		RecommendRecord recommendRecord = recommendRecordDao.getRecommendRecordByEmail(email);
		if(recommendRecord.getIsPaid() != 0){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public Boolean setCustomerPaid(String email) {
		if(!isRecommendRecordExisted(email)){
			return false;
		}
		RecommendRecord recommendRecord = recommendRecordDao.getRecommendRecordByEmail(email);
		recommendRecord.setIsPaid(1);
		recommendRecordDao.updateRecommendRecord(recommendRecord);
		return true;
	}
	@Override
	public String getCustomerEmailByCompanyId(long id) {
		User user= userQueryDao.getCompanyAdminUserByCompanyId(id);
		return user.getUserId();
		
	}
	@Override
	public Boolean addRecommendInfo(String code, String ip, Timestamp createdTime) {
		RecommendInfo recommendInfo = new RecommendInfo();
		recommendInfo.setId(code);
		recommendInfo.setIP(ip);
		recommendInfo.setCreatedTime(createdTime);
		
		return recommendInfoDao.addRecommendInfo(recommendInfo);
	}
	
	

}
