package com.wisdom.dispatch.schedule.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wisdom.common.model.Dispatcher;
import com.wisdom.dispatch.schedule.NotifySchedule;
import com.wisdom.dispatch.service.IDispatcherService;
import com.wisdom.dispatch.service.impl.JavaMailService;
import com.wisdom.weixin.service.IMessageProcessService;
import com.wisdom.weixin.service.IWeixinPushService;

@Component
public class NotifyScheduleImpl implements NotifySchedule {
	
	private static final Logger log = LoggerFactory
			.getLogger(NotifyScheduleImpl.class);
	
	@Autowired
	private IMessageProcessService weixinMessageProcessService;
	@Autowired
	private JavaMailService javaMailService;
	@Autowired
	private IDispatcherService dispatcherService;
	@Autowired
	private IWeixinPushService weixinPushService;
	
	private final String subject = "您有一个新的要审批的报销单据!";
	
	@Scheduled(cron="0/30 * * * * ?") //每30秒
	@Override
	public boolean invoiceApprovalNotify() {
		//查询一批记录
		log.error("schedule working");
		List<Dispatcher> dispatchList =  dispatcherService.getDispatcherByStatusAndNum(0,5);
		if(null == dispatchList){
			log.debug("no record need to be send");
			return true;
		}
		for(Dispatcher dispatch:dispatchList){
			boolean blRet = false;
			String userId = dispatch.getUserId();
			String userName = StringUtils.isEmpty(dispatch.getUserName()) ? userId : dispatch.getUserName();
			String body = "您有一个来自" + userName + "的新报销单据需要您审批，请登录系统查看!";
			String fromUser = "";
			int channelType = dispatch.getChannelTypeId();
			if(channelType == 0 ){
				blRet = javaMailService.sendMailOut(dispatch.getUserId(), subject, body, fromUser);
				if(!blRet){
					log.error("send mail to User failed,User:" + userId);
				}
				if(!StringUtils.isEmpty(dispatch.getOpenId())){
					blRet =  weixinPushService.pushTextMessage(dispatch.getOpenId(), body);
					if(!blRet){
						log.error("push weixin message error,openId:" + dispatch.getOpenId());
					}
				}
			}else if(channelType==2){
				blRet =  weixinPushService.pushTextMessage(dispatch.getOpenId(), body);
				if(!blRet){
					log.error("push weixin message error,openId:" + dispatch.getOpenId());
				}
			}else{
				blRet = javaMailService.sendMailOut(userId, subject, body, fromUser);
				if(!blRet){
					log.error("send mail to User failed,User:" + userId);
				}
			}
			if(blRet){//更新发票通知状态
				if(dispatcherService.updateDispatcherStatus(dispatch.getInvoiceId())){
					log.debug("update dispatch status success");
				}else{
					log.error("update dispatch status failed");
				}
			}
		}
		return true;
	}

}
