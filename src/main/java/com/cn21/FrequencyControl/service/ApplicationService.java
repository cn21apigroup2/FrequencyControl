/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cn21.FrequencyControl.module.Application;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
public interface ApplicationService {

	/**
	 * 根据userId获取应用列表
	 * @param userId
	 * @return
	 */
	List<Application> getApplicationListByUserId(Long userId);
	
	/**
	 * 根据userId获取已删除应用列表
	 * @param userId
	 * @return
	 */
	List<Application> getDeletedApplicationListByUserId(Long userId);
	
	/**
	 * 根据appkey恢复被删除的应用
	 * @param appKey
	 * @return
	 */
	boolean regainApplication(String appKey);
	
	/**
	 * 创建应用
	 * @param application
	 * @return
	 */
	boolean createApplication(Application application);
	
	/**
	 * 修改应用描述
	 * @param application
	 * @return
	 */
	boolean modifyApplicationDesc(Application application);
	
	/**
	 * 修改应用平台
	 * @param application
	 * @return
	 */
	boolean modifyApplicationPlatform(Application application);
	
	/**
	 * 删除应用
	 * @param application
	 * @return
	 */
	boolean deleteApplication(String appKey);
	
	/**
	 * 根据appKey获取应用
	 * @param appKey
	 * @return
	 */
	Application getApplicationByAppKey(String appKey);

	Application generateApp(HttpServletRequest request);
	

}
