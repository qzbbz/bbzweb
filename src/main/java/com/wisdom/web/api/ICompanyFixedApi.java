package com.wisdom.web.api;

import java.util.List;
import java.util.Map;

public interface ICompanyFixedApi {

	public List<Map<String, String>> companyFixedInformation(String userId, String month);
}
