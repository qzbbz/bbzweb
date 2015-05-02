package com.wisdom.dispatch.schedule.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wisdom.common.model.Dispatcher;
import com.wisdom.dispatch.schedule.NotifySchedule;
import com.wisdom.dispatch.service.IDispatcherService;
import com.wisdom.dispatch.service.impl.JavaMailService;
import com.wisdom.weixin.service.IMessageProcessService;

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
			String body = "您有一个新的要审批的报销单据!";
			String fromUser = "";
			int channelType = dispatch.getChannelTypeId();
			if(channelType == 0 ){
				blRet = javaMailService.sendMailOut(dispatch.getUserId(), subject, body, fromUser);
				if(!blRet){
					log.error("send mail to User failed,User:" + userId);
				}
//				blRet =  
			}else if(channelType==2){
				
			}else{
				blRet = javaMailService.sendMailOut(userId, subject, body, fromUser);
				if(!blRet){
					log.error("send mail to User failed,User:" + userId);
				}
			}
			if(blRet){//更新发票通知状态
				if(dispatcherService.updateDispatcherStatus(dispatch.getInvoiceId())){
					
				}else{
					log.error("更新发票状态失败，");
				}
			}
		}
		return true;
	}

}
