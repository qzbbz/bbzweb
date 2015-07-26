package com.wisdom.dispatch.schedule.impl;

import java.util.List;

import org.omg.CORBA.Current;
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
	
	//@Scheduled(cron="0/30 * * * * ?") //每30秒
	@Scheduled(fixedDelay=30000) //每30秒
	@Override
	public void invoiceApprovalNotify() {
		//查询一批记录
		log.error("schedule working");
		List<Dispatcher> dispatchList =  dispatcherService.getDispatcherByStatusAndNum(0,5);
		if(null == dispatchList || !(dispatchList.size() > 0)){
			log.debug("no record need to be send");
			return;
		}
		for(Dispatcher dispatch:dispatchList){
			boolean blRet = false;
			boolean blWeixinRet = false;
			String userId = dispatch.getUserId();
			String userName = dispatch.getUserName();
			if(StringUtils.isEmpty(userName)){
				userName =  userId;
			}
			String mailBody = "您有一个来自" + userName + "的新报销单据需要您审批，请登录系统查看!";
			String weixinBody ="您有一个来自" + userName + "的新报销单据需要您审批，请到收件箱查看!";
			log.debug("dispatch.getUserName" + userName);
			String fromUser = "";
			int channelType = dispatch.getChannelTypeId();
			if(channelType == 0 ){
				blRet = javaMailService.sendMailOut(dispatch.getReciever(), subject, mailBody, fromUser);
				if(!blRet){
					log.error("send mail to User failed,User:" + dispatch.getReciever());
				}
				if(!StringUtils.isEmpty(dispatch.getOpenId())){
					log.debug("callpushTextMessage,threadId:" + Thread.currentThread().getId());
					blWeixinRet =  weixinPushService.pushTextMessage(dispatch.getOpenId(), weixinBody);
					if(!blWeixinRet){
						log.error("push weixin message error,openId:" + dispatch.getOpenId());
					}
				}
			}else if(channelType==2){
				blWeixinRet =  weixinPushService.pushTextMessage(dispatch.getOpenId(), weixinBody);
				if(!blWeixinRet){
					log.error("push weixin message error,openId:" + dispatch.getOpenId());
				}
			}else{
				blRet = javaMailService.sendMailOut(dispatch.getReciever(), subject, mailBody, fromUser);
				if(!blRet){
					log.error("send mail to User failed,User:" + userId);
				}
			}
			
			if(blRet || blWeixinRet){//更新发票通知状态
				if(dispatcherService.updateDispatcherStatus(dispatch.getInvoiceId())){
					log.debug("update dispatch status success");
				}else{
					log.error("update dispatch status failed");
				}
			}
		}
		return;
	}

}
