package com.cn21.FrequencyControl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cn21.FrequencyControl.module.Parameter;

/**
 * @author zhangqingxiang
 * @date 2016年8月23日
 */
public interface ParameterService {
 
	/**
	 * 根据interId获取参数列表
	 * @param interId
	 * @return
	 */
	List<Parameter> getParameterListByInterId(long interId);

	/**
	 * 创建parameter
	 * @param parameter
	 * @return
	 */
	boolean createParameter(Parameter parameter);
	/**
	 * 修改参数
	 * @param parameter
	 * @return
	 */
	boolean modifyParameterKey(Parameter parameter);

	/**
	 * 修改参数值
	 * @param parameter
	 * @return
	 */
	boolean modifyParameterValue(Parameter parameter);
	
	/**
	 * 更新参数
	 * @param parameter
	 * @return
	 */
	boolean modifyParameter(Parameter parameter);
	
	void deleteParameter(long paraId);
	
	boolean updateParameter(Parameter parameter);
	
	Parameter getParameterByParaId(long paraId);
	
	Parameter generateParameter(HttpServletRequest request,long interId);

}
