package com.wisdom.company.dao.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.SalarySocialSecurity;
import com.wisdom.company.dao.ISalarySocialSecurityDao;
import com.wisdom.company.mapper.SalarySocialSecurityMapper;

@Repository("salarySocialSecurityDao")
public class SalarySocialSecurityDaoImpl implements ISalarySocialSecurityDao {

	private static final Logger logger = LoggerFactory
			.getLogger(SalarySocialSecurityDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public SalarySocialSecurity getSSSByCompanyIdAndCityNameAndRegType(
			long companyId, String cityName, int type) {
		logger.debug("companyId id : {}, cityName : {}, type : {}", companyId, cityName, type);
		String sql = "select * from sss where company_id = ? and city_name=? and registry_type=?";
		SalarySocialSecurity sss = null;
		try {
			sss = jdbcTemplate.queryForObject(sql, new Object[] { companyId, cityName, type },
					new SalarySocialSecurityMapper());
		} catch (Exception e) {
			logger.debug("result is 0. exception : {}" + e.toString());
		}
		return sss;
	}

	@Override
	public boolean addSalarySocialSecurity(SalarySocialSecurity sss) {
		String sql = "insert into sss (company_id, city_name, registry_type, pension_cratio, pension_pratio, pension_base, medical_cratio, medical_pratio, medical_base, unemploy_cratio, unemploy_pratio, unemploy_base, injury_cratio, injury_pratio, injury_base, birth_cratio, birth_pratio, birth_base, accfund_cratio, accfund_pratio, accfund_base, salary_cratio, salary_pratio, salary_base, bigmedical_cratio, bigmedical_pratio, bigmedical_base, create_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		logger.debug("SalarySocialSecurity : {}", sss.toString());
		if(sss.getCompanyId() == null || sss.getCityName() == null ||
				sss.getRegistryType() == null) {
			logger.debug("SalarySocialSecurity info is wrong.");
			return false;
		}
		int affectedRows = jdbcTemplate.update(sql, 
				sss.getCompanyId(),
				sss.getCityName(),
				sss.getRegistryType(),
				sss.getPensionCratio() == null ? "" : sss.getPensionCratio(),
				sss.getPensionPratio() == null ? "" : sss.getPensionPratio(),
				sss.getPensionBase() == null ? "" : sss.getPensionBase(),
				sss.getMedicalCratio() == null ? "" : sss.getMedicalCratio(),
				sss.getMedicalPratio() == null ? "" : sss.getMedicalPratio(),
				sss.getMedicalBase() == null ? "" : sss.getMedicalBase(),
				sss.getUnemployCratio() == null ? "" : sss.getUnemployCratio(),
				sss.getUnemployPratio() == null ? "" : sss.getUnemployPratio(),
				sss.getUnemployBase() == null ? "" : sss.getUnemployBase(),
				sss.getInjuryCratio() == null ? "" : sss.getInjuryCratio(),
				sss.getInjuryPratio() == null ? "" : sss.getInjuryPratio(),
				sss.getInjuryBase() == null ? "" : sss.getInjuryBase(),
				sss.getBirthCratio() == null ? "" : sss.getBirthCratio(),
				sss.getBirthPratio() == null ? "" : sss.getBirthPratio(),
				sss.getBirthBase() == null ? "" : sss.getBirthBase(),
				sss.getAccfundCratio() == null ? "" : sss.getAccfundCratio(),
				sss.getAccfundPratio() == null ? "" : sss.getAccfundPratio(),
				sss.getAccfundBase() == null ? "" : sss.getAccfundBase(),
				sss.getSalaryCratio() == null ? "" : sss.getSalaryCratio(),
				sss.getSalaryPratio() == null ? "" : sss.getSalaryPratio(),
				sss.getSalaryBase() == null ? "" : sss.getSalaryBase(),
				sss.getBigmedicalCratio() == null ? "" : sss.getBigmedicalCratio(),
				sss.getBigmedicalPratio() == null ? "" : sss.getBigmedicalPratio(),
				sss.getBigmedicalBase() == null ? "" : sss.getBigmedicalBase(),
				sss.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : sss.getCreateTime());
		logger.debug("addSalarySocialSecurity result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteSalarySocialSecurity(SalarySocialSecurity sss) {
		String sql = "delete from sss where company_id = ? and city_name=? and registry_type=?";
		int affectedRows = jdbcTemplate.update(sql, sss.getCompanyId(), sss.getCityName(), sss.getRegistryType());
		logger.debug("deleteSalarySocialSecurity result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateSalarySocialSecurity(SalarySocialSecurity sss) {
		String sql = "update sss set pension_cratio=?, pension_pratio=?, pension_base=?, medical_cratio=?, medical_pratio=?, medical_base=?, unemploy_cratio=?, unemploy_pratio=?, unemploy_base=?, injury_cratio=?, injury_pratio=?, injury_base=?, birth_cratio=?, birth_pratio=?, birth_base=?, accfund_cratio=?, accfund_pratio=?, accfund_base=?, salary_cratio=?, salary_pratio=?, salary_base=?, bigmedical_cratio=?, bigmedical_pratio=?, bigmedical_base=? where company_id = ? and city_name=? and registry_type=?";
		int affectedRows = jdbcTemplate.update(sql,
				sss.getPensionCratio() == null ? "" : sss.getPensionCratio(),
						sss.getPensionPratio() == null ? "" : sss.getPensionPratio(),
						sss.getPensionBase() == null ? "" : sss.getPensionBase(),
						sss.getMedicalCratio() == null ? "" : sss.getMedicalCratio(),
						sss.getMedicalPratio() == null ? "" : sss.getMedicalPratio(),
						sss.getMedicalBase() == null ? "" : sss.getMedicalBase(),
						sss.getUnemployCratio() == null ? "" : sss.getUnemployCratio(),
						sss.getUnemployPratio() == null ? "" : sss.getUnemployPratio(),
						sss.getUnemployBase() == null ? "" : sss.getUnemployBase(),
						sss.getInjuryCratio() == null ? "" : sss.getInjuryCratio(),
						sss.getInjuryPratio() == null ? "" : sss.getInjuryPratio(),
						sss.getInjuryBase() == null ? "" : sss.getInjuryBase(),
						sss.getBirthCratio() == null ? "" : sss.getBirthCratio(),
						sss.getBirthPratio() == null ? "" : sss.getBirthPratio(),
						sss.getBirthBase() == null ? "" : sss.getBirthBase(),
						sss.getAccfundCratio() == null ? "" : sss.getAccfundCratio(),
						sss.getAccfundPratio() == null ? "" : sss.getAccfundPratio(),
						sss.getAccfundBase() == null ? "" : sss.getAccfundBase(),
						sss.getSalaryCratio() == null ? "" : sss.getSalaryCratio(),
						sss.getSalaryPratio() == null ? "" : sss.getSalaryPratio(),
						sss.getSalaryBase() == null ? "" : sss.getSalaryBase(),
						sss.getBigmedicalCratio() == null ? "" : sss.getBigmedicalCratio(),
						sss.getBigmedicalPratio() == null ? "" : sss.getBigmedicalPratio(),
						sss.getBigmedicalBase() == null ? "" : sss.getBigmedicalBase(),
						sss.getCompanyId(),
						sss.getCityName(),
						sss.getRegistryType());
		logger.debug("updateDept result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean setTemplate(long companyId, String cityName, int type, String templatePath) {
		String sql = "update sss set template=? where company_id = ? and city_name=? and registry_type=?";
		int affectedRows = jdbcTemplate.update(sql, templatePath, companyId, cityName, type);
		logger.debug("updateDept result : {}", affectedRows);
		return affectedRows != 0;
	}

}
