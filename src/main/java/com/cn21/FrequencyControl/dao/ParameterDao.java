package com.cn21.FrequencyControl.dao;

import java.util.List;

import com.cn21.FrequencyControl.module.Parameter;

/**
 * @author zhangqingxiang
 * @date 2016年8月24日
 */
public interface ParameterDao {

	int addParameter(Parameter parameter);
	
	List<Parameter> getParameterListByInterId(long interId);
	
	Parameter getParameterByParaId(long paraId);
	
    int updateParameterKey(Parameter parameter);
	
	int updateParameter(Parameter parameter);
	
	int updateParameterValue(Parameter parameter);

	void deleteParameter(long paraId);

}
