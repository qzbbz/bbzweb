package com.wisdom.dispatch.schedule.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyPay;
import com.wisdom.company.service.ICompanyPayService;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.dispatch.schedule.CheckSchedule;
import com.wisdom.invoice.service.IInvoiceService;

@Component
public class CheckScheduleImpl implements CheckSchedule {
	
	private static final Logger log = LoggerFactory
			.getLogger(CheckScheduleImpl.class);
	
	
	@Autowired ICompanyPayService companyPayService;
	
	@Autowired ICompanyService companyService;
	
	@Autowired IInvoiceService invoiceService;
	
	@Scheduled(fixedDelay=86400000) //每1天
	@Override
	public void companyPayCheckService() {
		//Get all expired payments
		List<CompanyPay> list = companyPayService.getExpiredCompanyPay();
		for(CompanyPay companyPay: list){
			Company company = companyService.getCompanyByCompanyId(companyPay.getCompanyId());
			
			if(company!= null && !company.getAccounterId().equals("")){
				//Remove the accountant from the company
				companyService.updateCompanyAccounter(companyPay.getCompanyId(), "");
				log.info("Remove accountant from " + company.getName());
			}
		}
	}

	/*@Scheduled(fixedDelay=60*60*1000) //每小时
	@Override
	public void redundantInvoiceArtifactCheckService() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.HOUR_OF_DAY, -1);
		Timestamp toTime = new Timestamp(0);
		toTime.setTime(cal.getTime().getTime());
		invoiceService.removeRedundantInvoiceArtifact(toTime);
		log.info("Remove redundant invoice artifacts.");
		
	}*/
}