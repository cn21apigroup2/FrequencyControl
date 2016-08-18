/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.dao;

import java.util.List;
import java.util.Map;

import com.cn21.FrequencyControl.module.Application;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
public interface ApplicationDao {

	int addApplication(Application application);

	List<Application> getAppListByUserId(long userId);

	List<Application> getDeletedAppListByUserId(long userId);

	Application getApplicationByAppKey(String appKey);

	Application getApplicationByAppId(long appId);

	int updateAppDescription(Application application);
	
	int updateApplication(Application application);

	int updateAppPlatform(Application application);

	int deleteApplication(long app_id);

	int regainApplication(long app_id);
	
	List<Application> getPageByUserId(Map<String,Object> paramMap);
	
	int getTotalCount(long userId);
}
