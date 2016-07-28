package com.wisdom.web.api.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.TestInvoiceRecord;
import com.wisdom.company.service.ICompanyBillService;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyBillApi;

@Service("companyBillApiService")
public class CompanyBillApiImpl implements ICompanyBillApi {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyBillApiImpl.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyBillService companyBillService;
	
	@Autowired
	private IInvoiceService invoiceService;
	
	@Override
	public Map<String, String> uploadCompanyBill(Map<String, String> params, MultipartFile file) {
		Map<String, String> retMap = new HashMap<>();
		try {
			String userId = params.get("userId");
			String date=params.get("date");
			String supplyName = params.get("supplyName");
			String isFixedAssets = params.get("isFixedAssets");
			long companyId = userService.getCompanyIdByUserId(userId);
			String fileName = getGernarateFileName(file, userId);
			FileUtils.copyInputStreamToFile(file.getInputStream(),
					new File(params.get("realPath"), fileName));
			CompanyBill cb = new CompanyBill();
			cb.setCompanyId(companyId);
			cb.setFileName(fileName);
			cb.setBillDate(date);
			/*cb.setSupplyName(supplyName);
			Integer fixedAssetFlag = Integer.valueOf(isFixedAssets);
			cb.setIsFixedAssets(fixedAssetFlag);*/
			cb.setCreateTime(new Timestamp(System.currentTimeMillis()));
			companyBillService.addCompanyBill(cb);
			
			
			//Create invoice
			long invoiceId = invoiceService.addInvoice(companyId, fileName, date, 0, "company");
			Company company = companyService.getCompanyByCompanyId(companyId);
			/**
			 * Thread (request url by get)
			 */
			logger.debug("Thread has start");
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					requestByGetMethod("http://121.40.63.208:8899/invoiceidentify?invoiceId=" + invoiceId);
//				}// run
//			}).start();
			//Send to queue
			invoiceService.publishUnrecognizedInvoive(invoiceId, companyId, fileName, company.getName());
			retMap.put("error_code", "0");
		} catch (Exception e) {
			logger.debug("uploadCompanyBill exception : {}", e.toString());
			retMap.put("error_code", "1");
			retMap.put("error_message", "上传发票失败，请稍后重试！");
		}
		return retMap;
	}
	/**
	 * Http Get for url eg:"http://121.40.63.208:8899/invoiceidentify?invoiceId="
	 */
	private void requestByGetMethod(String url) {
		logger.debug("Execute requestByGetMethod , income paratemeter url {} ", url);
		CloseableHttpClient httpClient = getHttpClient();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			logger.debug("return status code {}", httpResponse.getStatusLine());
			EntityUtils.consume(entity);
		} catch (Exception e) {
			logger.debug("HttpClient request By get happen Exception {}", e.toString());
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.debug("HttpClient close Exception {}", e.toString());
			}
		}
	}
	// return CloseableHttpClient
	private CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}
	
	@Override
	public List<Map<String, String>> getCompanyBillByCondition(
			Map<String, String> params) {
		String userId = params.get("userId");
		if(userId == null || userId.isEmpty()) return null;
		long companyId = userService.getCompanyIdByUserId(userId);
		if(companyId <= 0) return null;
		String companyName = companyService.getCompanyName(companyId);
		Map<String, String> extraParams = new HashMap<>();
		extraParams.put("company_name", companyName);
		if(params.get("conditionType") == null || params.get("conditionType").isEmpty() ||
				("0").equals(params.get("conditionType")) ||
				params.get("conditionValue") == null || params.get("conditionValue").isEmpty()) {			
			List<TestInvoiceRecord> list = invoiceService.getAllInvoicesByCompanyId(companyId);
			return createInvoiceList(list, extraParams);

		}
		if(("1").equals(params.get("conditionType")) && params.get("conditionValue") != null) {
			List<TestInvoiceRecord> list = invoiceService.getInvoicesByDate(params.get("conditionValue"), companyId);
			return createInvoiceList(list, extraParams);
		}
		return null;
	}

	private List<Map<String, String>> createCompanyBillList(List<CompanyBill> list, Map<String, String> extraParams) {
		List<Map<String, String>> retList = new ArrayList<>();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(list == null) return null;
		for(CompanyBill cb : list) {
			Map<String, String> map = new HashMap<>();
			Timestamp stamp = cb.getCreateTime();
			map.put("id", String.valueOf(cb.getId()));
			map.put("date", sdf.format(stamp));
			Double amount = cb.getAmount() == null?0:cb.getAmount();
			DecimalFormat format = new DecimalFormat("#0.000");
			map.put("amount", format.format(amount));
			map.put("expense_type", cb.getType());
			map.put("file_name", cb.getFileName() == null ? "" : cb.getFileName());
			map.put("supplyName", cb.getSupplyName());
			map.put("isFixedAssets", String.valueOf(cb.getIsFixedAssets()));
			map.putAll(extraParams);
			retList.add(map);
		}
		return retList.size() > 0 ? retList : null;
	}
	
	private String getGernarateFileName(MultipartFile file, String userId) {
		Random rdm = new Random(System.currentTimeMillis());
		String extendName = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf(".") + 1);
		return userId + System.currentTimeMillis() + Math.abs(rdm.nextInt())
				% 1000 + (extendName == null ? ".unknown" : "." + extendName);
	}

	@Override
	public boolean deleteCompanyBill(String idList, String realPath) {
		String[] ids = idList.split(",");
		for (String id : ids) {
			CompanyBill cb = companyBillService.getCompanyBillById(Long.valueOf(id));
			if(cb == null) continue;
			File file = new File(realPath + "/" + cb.getFileName());
			FileUtils.deleteQuietly(file);
			companyBillService.deleteCompanyBillById(Long.valueOf(id));
		}
		return true;
	}

	@Override
	public boolean modifyCompanyBill(String id, String amount, String type, String supplyName, String isFixedAssets) {
		try {
			Long id_ = Long.valueOf(id);
			Double amount_ = Double.valueOf(amount);
			int isFixedAssets_ = Integer.valueOf(isFixedAssets);
			companyBillService.updateCompanyBill(amount_, type, id_, supplyName, isFixedAssets_);
		} catch(Exception e) {
			logger.debug("Exception : {}", e.toString());
		}
		return true;
	}

	@Override
	public List<Map<String, String>> accounterGetCompanyBillByCondition(Map<String, String> params) {
		List<Map<String, String>> resultList = new ArrayList<>();
		String userId = params.get("userId");
		if(userId == null || userId.isEmpty()) return resultList;
		List<Company> companyList = companyService.getCompanyListByAccounterId(userId);
		if(companyList == null) return resultList;
		for(Company company : companyList) {
			long companyId = company.getId();
			if(companyId <= 0) continue;
			String companyName = companyService.getCompanyName(companyId);
			Map<String, String> extraParams = new HashMap<>();
			extraParams.put("company_name", companyName);
			List<TestInvoiceRecord> list = invoiceService.getAllInvoicesByCompanyId(companyId);
			if(list == null || list.size() == 0) continue;
			resultList.addAll(createInvoiceList(list, extraParams));
		}
		return resultList;
	}

	@Override
	public List<Map<String, String>> getAllInvoicesByUserId(String userId) {

		if(userId == null || userId.isEmpty()) return null;
		long companyId = userService.getCompanyIdByUserId(userId);
		if(companyId <= 0) return null;
		String companyName = companyService.getCompanyName(companyId);
		Map<String, String> extraParams = new HashMap<>();
		extraParams.put("company_name", companyName);
		List<TestInvoiceRecord> list = invoiceService.getAllInvoicesByCompanyId(companyId);
		return createInvoiceList(list, extraParams);

	}
	
	
	@Override
	public List<Map<String, String>> getAllInvoicesByCondition(Map<String, String> params, String userId) {
		if(userId == null || userId.isEmpty()) return null;
		long companyId = userService.getCompanyIdByUserId(userId);
		if(companyId <= 0) return null;
		String companyName = companyService.getCompanyName(companyId);
		Map<String, String> extraParams = new HashMap<>();
		extraParams.put("company_name", companyName);
		if(params.get("conditionType") == null || params.get("conditionType").isEmpty() ||
				("0").equals(params.get("conditionType")) ||
				params.get("conditionValue") == null || params.get("conditionValue").isEmpty()) {
			List<TestInvoiceRecord> list = invoiceService.getAllInvoicesByCompanyId(companyId);
			return createInvoiceList(list, extraParams); 
		}
		if(("1").equals(params.get("conditionType")) && params.get("conditionValue") != null) {
			List<TestInvoiceRecord> list = invoiceService.getInvoicesByDate(params.get("conditionValue"), companyId);
			return createInvoiceList(list, extraParams);
		}
		return null;
	}
	
	private List<Map<String, String>> createInvoiceList(List<TestInvoiceRecord> list, Map<String, String> extraParams) {
		List<Map<String, String>> retList = new ArrayList<>();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(list == null) return null;
		for(TestInvoiceRecord invoiceRecord : list) {
			Map<String, String> map = new HashMap<>();
			Timestamp stamp = invoiceRecord.getCreatedTime();
			map.put("id", String.valueOf(invoiceRecord.getInvoiceId()));
			map.put("date", sdf.format(stamp));
			Double amount = invoiceRecord.getAmount();
			DecimalFormat format = new DecimalFormat("#0.000");
			Double tax = invoiceRecord.getTax();
			map.put("tax", format.format(tax));
			map.put("amount", format.format(amount));
			map.put("expense_type", invoiceRecord.getType());
			map.put("file_name", invoiceRecord.getFileName() == null ? "" : invoiceRecord.getFileName());
			map.put("supplyName", invoiceRecord.getSupplierName());
			map.put("isFixedAssets", String.valueOf(invoiceRecord.getIsFa()));
			map.put("status", invoiceRecord.getStatus());
			map.put("number", invoiceRecord.getNumber() == null ? "1":String.valueOf(invoiceRecord.getNumber()));
			map.putAll(extraParams);
			retList.add(map);
		}
		return retList.size() > 0 ? retList : null;
	}

	@Override
	public boolean deleteInvoice(String idList, String realPath) {
		String[] ids = idList.split(",");
		for (String id : ids) {
			// delete from cb
			/*CompanyBill cb = companyBillService.getCompanyBillById(Long.valueOf(id));
			if(cb == null) continue;
			File file = new File(realPath + "/" + cb.getFileName());
			FileUtils.deleteQuietly(file);
			companyBillService.deleteCompanyBillById(Long.valueOf(id));*/
			// delete from invoice
			invoiceService.deleteTestInvoice(Long.valueOf(id));
		}
		return true;
	}


	
	
}
