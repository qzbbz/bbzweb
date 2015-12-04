package com.wisdom.company.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.SheetIncomeDetail;
import com.wisdom.company.dao.ISheetIncomeDetailDao;
import com.wisdom.company.mapper.SheetIncomeDetailMapper;
@Repository("sheetIncomeDetailDao")
public class SheetIncomeDetailDaoImpl implements ISheetIncomeDetailDao {
	private static final Logger logger = LoggerFactory
			.getLogger(SheetIncomeDetailDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private List<String> list = null;
	@Override
	public  List<SheetIncomeDetail> operateSqlByYear(String date, String[] porjects,long companyId) {
		List<String> list=new ArrayList<String>();
		List<SheetIncomeDetail> listSheetIncome=new ArrayList<SheetIncomeDetail>();
		StringBuilder conditionString=disposePorjectCondition(porjects);
		conditionString.append(" AND ?<=date_time<=?");
		conditionString.append(" AND company_id="+companyId);
		list=disposePorjectValue(list,porjects,date);
		String sql = "select * from sheet_income_detail where "+conditionString;
		Object strings[]=new Object[list.size()];
		for(int i=0,j=list.size();i<j;i++){
		strings[i]=list.get(i);
		}
		try{
			listSheetIncome=jdbcTemplate.query(sql,strings,new RowMapperResultSetExtractor<SheetIncomeDetail>(new SheetIncomeDetailMapper()));
		}catch(Exception e){
			logger.error(e.toString());
		}
		return listSheetIncome;
	}
	@Override
	public  List<SheetIncomeDetail> operateSqlByMonth(String date, String[] porjects,long companyId) {
		List<String> list=new ArrayList<String>();
		List<SheetIncomeDetail> listSheetIncome=new ArrayList<SheetIncomeDetail>();
		StringBuilder conditionString=disposePorjectCondition(porjects);
		conditionString.append(" AND date_time LIKE ?");
		conditionString.append(" AND company_id="+companyId);
		list=disposePorjectValue(list,porjects,date);
		Object strings[]=new Object[list.size()];
		for(int i=0,j=list.size();i<j;i++){
		strings[i]=list.get(i);
		}
		String sql = "select * from sheet_income_detail where "+conditionString;
		try{
			listSheetIncome=jdbcTemplate.query(sql,strings,new RowMapperResultSetExtractor<SheetIncomeDetail>(new SheetIncomeDetailMapper()));
		}catch(Exception e){
			logger.error(e.toString());
		}
		return listSheetIncome;
	}
	@Override
	public StringBuilder disposePorjectCondition(String[] porjects) {
		StringBuilder projectString=new StringBuilder();
		projectString.append("project LIKE \""+porjects[0]+"%\"");
		return projectString;
	}
	@Override
	public List<String> disposePorjectValue(List<String> newList,String[] porjects,String date) {
			int num=date.indexOf("-");
			newList.add(date.substring(0,num)+"-"+"01");
			newList.add(date);
		return newList;
	}
	

}
