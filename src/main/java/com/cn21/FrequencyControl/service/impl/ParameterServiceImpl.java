package com.cn21.FrequencyControl.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn21.FrequencyControl.dao.ParameterDao;
import com.cn21.FrequencyControl.module.Parameter;
import com.cn21.FrequencyControl.service.ParameterService;

@Service
@Transactional
public class ParameterServiceImpl implements ParameterService{

	@Autowired
	private ParameterDao parameterDao;
	
	@Override
	public List<Parameter> getParameterListByInterId(long interId) {
		// TODO Auto-generated method stub
		return parameterDao.getParameterListByInterId(interId);
	}

	@Override
	public boolean createParameter(Parameter parameter) {
		// TODO Auto-generated method stub
		int successCount=parameterDao.addParameter(parameter);
		if(successCount==1)return true;
		return false;
	}

	@Override
	public boolean modifyParameterKey(Parameter parameter) {
		// TODO Auto-generated method stub
		int successCount=parameterDao.updateParameterKey(parameter);
		if(successCount==1)return true;
		return false;
	}

	@Override
	public boolean modifyParameterValue(Parameter parameter) {
		// TODO Auto-generated method stub
		int successCount=parameterDao.updateParameterValue(parameter);
		if(successCount==1)return true;
		return false;
	}

	@Override
	public boolean modifyParameter(Parameter parameter) {
		// TODO Auto-generated method stub
		int successCount=parameterDao.updateParameter(parameter);
		if(successCount==1)return true;
		return false;
	}

	@Override
	public void deleteParameter(long paraId) {
		// TODO Auto-generated method stub
		parameterDao.deleteParameter(paraId);
	}

	@Override
	public boolean updateParameter(Parameter parameter) {
		// TODO Auto-generated method stub
		int successCount=parameterDao.updateParameter(parameter);
		if(successCount==1)return true;
		return false;
	}

	@Override
	public Parameter getParameterByParaId(long paraId) {
		// TODO Auto-generated method stub
		return parameterDao.getParameterByParaId(paraId);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Parameter generateParameter(HttpServletRequest request, long interId) {
		// TODO Auto-generated method stub
		String parameterKey = request.getParameter("parameterKey");
		String parameterValue = request.getParameter("parameterValue");
		Parameter parameter = new Parameter();
		parameter.setParameter_key(parameterKey);
		parameter.setParameter_value(parameterValue);
		parameter.setInterface_id(interId);
		return parameter;
	}
	

}
