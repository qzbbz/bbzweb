package com.wisdom.dispatch.schedule.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Current;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Dispatcher;
import com.wisdom.common.model.Sales;
import com.wisdom.common.model.User;
import com.wisdom.dispatch.schedule.NotifySchedule;
import com.wisdom.dispatch.service.IDispatcherService;
import com.wisdom.dispatch.service.impl.JavaMailService;
import com.wisdom.sales.service.ISalesService;
import com.wisdom.user.service.IUserService;
import com.wisdom.weixin.service.IMessageProcessService;
import com.wisdom.weixin.service.IWeixinPushService;

@Component
public class NotifyScheduleImpl implements NotifySchedule {

	private static final Logger log = LoggerFactory.getLogger(NotifyScheduleImpl.class);

	@Autowired
	private IMessageProcessService weixinMessageProcessService;
	@Autowired
	private JavaMailService javaMailService;
	@Autowired
	private ISalesService salesService;
	@Autowired
	private IDispatcherService dispatcherService;
	@Autowired
	private IWeixinPushService weixinPushService;
	@Autowired
	private IUserService userService;
	

	private final String subject = "您有一个新的要审批的报销单据!";

	// @Scheduled(cron="0/30 * * * * ?") //每30秒
	// @Scheduled(fixedDelay=30000) //每30秒
	@Override
	public void invoiceApprovalNotify() {
		// 查询一批记录
		log.debug("schedule working");
		List<Dispatcher> dispatchList = dispatcherService.getDispatcherByStatusAndNum(0, 5);
		if (null == dispatchList || !(dispatchList.size() > 0)) {
			log.debug("no record need to be send");
			return;
		}
		for (Dispatcher dispatch : dispatchList) {
			boolean blRet = false;
			boolean blWeixinRet = false;
			String userId = dispatch.getUserId();
			String userName = dispatch.getUserName();
			if (StringUtils.isEmpty(userName)) {
				userName = userId;
			}
			String mailBody = "您有一个来自" + userName + "的新报销单据需要您审批，请登录系统查看!";
			String weixinBody = "您有一个来自" + userName
					+ "的新报销单据需要您审批，<a href='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx333ea15ba860f932&redirect_uri=http%3a%2f%2fwww.bangbangzhang.com%2fgetOpenIdRedirect%3fview%3daudit.html&response_type=code&scope=snsapi_base&state=1#wechat_redirect'>点击查看!</a>";
			log.debug("dispatch.getUserName" + userName);
			String fromUser = "";
			int channelType = dispatch.getChannelTypeId();
			if (channelType == 0) {
				blRet = javaMailService.sendMailOut(dispatch.getReciever(), subject, mailBody, fromUser);
				if (!blRet) {
					log.error("send mail to User failed,User:" + dispatch.getReciever());
				}
				if (!StringUtils.isEmpty(dispatch.getOpenId())) {
					log.debug("callpushTextMessage,threadId:" + Thread.currentThread().getId());
					blWeixinRet = weixinPushService.pushTextMessage(dispatch.getOpenId(), weixinBody);
					if (!blWeixinRet) {
						log.error("push weixin message error,openId:" + dispatch.getOpenId());
					}
				}
			} else if (channelType == 2) {
				blWeixinRet = weixinPushService.pushTextMessage(dispatch.getOpenId(), weixinBody);
				if (!blWeixinRet) {
					log.error("push weixin message error,openId:" + dispatch.getOpenId());
				}
			} else {
				blRet = javaMailService.sendMailOut(dispatch.getReciever(), subject, mailBody, fromUser);
				if (!blRet) {
					log.error("send mail to User failed,User:" + userId);
				}
			}

			dispatcherService.updateDispatcherStatus(dispatch.getInvoiceId(), 1);

			/*
			 * if(blRet || blWeixinRet){//更新发票通知状态
			 * if(dispatcherService.updateDispatcherStatus(dispatch.getInvoiceId
			 * ())){ log.debug("update dispatch status success"); }else{
			 * log.error("update dispatch status failed"); } }
			 */
		}
		return;
	}

	@Scheduled(fixedDelay = 30000)
	@Override
	public void notifyAccounterCallCustomer() {
		// 查询一批记录
		log.debug("notifyAccounterCallCustomer");
		Date date = new Date();
		SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");
		String updateTime = sFormat.format(date);
		List<Sales> list = salesService.getSalesRecordsByUpdatedTime(updateTime);
		if(list == null ) return;
		for(Sales sale : list) {
			if(sale.getHasSendEmail() == 0) {
				String mailBody = "您有一个客户需要今天联系，客户公司名称：" + sale.getUserCompany() + ",联系电话：" + sale.getUserPhone() + ",联系人姓名：" + sale.getUserName() + ",记录编号:" + sale.getId() ;
				String mailSubject = "联系客户提醒邮件";
				User user = userService.getUserByUserId(sale.getAccountantId());
				String msgMail = user.getMsgEmail();
				if(msgMail == null || msgMail == ""){
					msgMail = sale.getAccountant();
				}

				//Get supervisor
				String supervisors = "";
					List<String> userList = userService.getApprovalUserList(sale.getAccountantId());
					if (null == userList || !(userList.size() > 0)) {
						 supervisors = new String("qiuchen@bangbangzhang.com"); // TODO TEST
					}else{
						StringBuilder str = new StringBuilder();
						for (String o : userList) {
							str.append(o).append(";");
						}
						supervisors = str.toString().substring(0, str.length() - 1);
					}
					boolean blRet = javaMailService.sendMailOut(supervisors + ";" + msgMail, mailSubject, mailBody, "帮帮账");
				if (!blRet) {
					log.error("send mail to User failed");
				} else {
					salesService.updateSalesSendEmailStatus(sale.getId(), 1);
				}
			}
		}
		return;
	}

}
