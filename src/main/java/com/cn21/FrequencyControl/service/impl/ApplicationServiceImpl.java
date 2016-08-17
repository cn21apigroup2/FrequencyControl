/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn21.FrequencyControl.dao.ApplicationDao;
import com.cn21.FrequencyControl.module.Application;
import com.cn21.FrequencyControl.service.ApplicationService;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
@Service
public class ApplicationServiceImpl implements ApplicationService{
	@Autowired
	private ApplicationDao applicationDao;

	/* (non-Javadoc)
	 * 根据userId获取应用列表
	 * @see com.cn21.FrequencyControl.service.ApplicationService#getApplicationListByUserId(java.lang.Long)
	 */
	@Override
	public List<Application> getApplicationListByUserId(Long userId) {
		// TODO Auto-generated method stub
		return applicationDao.getAppListByUserId(userId);
	}

	/* (non-Javadoc)
	 * 根据userId获取已删除应用列表
	 * @see com.cn21.FrequencyControl.service.ApplicationService#getDeletedApplicationListByUserId(java.lang.Long)
	 */
	@Override
	public List<Application> getDeletedApplicationListByUserId(Long userId) {
		// TODO Auto-generated method stub
		return applicationDao.getDeletedAppListByUserI(userId);
	}

	/* (non-Javadoc)
	 * 根据appkey恢复被删除的应用
	 * @see com.cn21.FrequencyControl.service.ApplicationService#regainApplication(java.lang.String)
	 */
	@Override
	public boolean regainApplication(String appKey) {
		// TODO Auto-generated method stub
		int successCount = applicationDao.regainApplication(appKey);
		if(successCount==1) return true;
		return false;
	}

	/* (non-Javadoc)
	 * 创建应用
	 * @see com.cn21.FrequencyControl.service.ApplicationService#createApplication(com.cn21.FrequencyControl.module.Application)
	 */
	@Override
	public boolean createApplication(Application application) {
		// TODO Auto-generated method stub
		int successCount = applicationDao.addApplication(application);
		if(successCount==1) return true;
		return false;
	}

	/* (non-Javadoc)
	 * 修改应用描述
	 * @see com.cn21.FrequencyControl.service.ApplicationService#modifyApplication(com.cn21.FrequencyControl.module.Application)
	 */
	@Override
	public boolean modifyApplicationDesc(Application application) {
		// TODO Auto-generated method stub
		int successCount = applicationDao.updateAppDescription(application);
		if(successCount==1) return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * 修改应用平台
	 * @see com.cn21.FrequencyControl.service.ApplicationService#modifyApplication(com.cn21.FrequencyControl.module.Application)
	 */
	@Override
	public boolean modifyApplicationPlatform(Application application) {
		// TODO Auto-generated method stub
		int successCount = applicationDao.updateAppPlatform(application);
		if(successCount==1) return true;
		return false;
	}

	/* (non-Javadoc)
	 * 删除应用
	 * @see com.cn21.FrequencyControl.service.ApplicationService#deleteApplication(com.cn21.FrequencyControl.module.Application)
	 */
	@Override
	public boolean deleteApplication(String appKey) {
		// TODO Auto-generated method stub
		int successCount = applicationDao.deleteApplication(appKey);
		if(successCount==1) return true;
		return false;
	}

	/* (non-Javadoc)
	 * 根据appKey获取应用
	 * @see com.cn21.FrequencyControl.service.ApplicationService#getApplicationByAppKey(java.lang.String)
	 */
	@Override
	public Application getApplicationByAppKey(String appKey) {
		// TODO Auto-generated method stub
		return applicationDao.getApplicationByAppKey(appKey);
	}

	@Override
	public Application generateApp(HttpServletRequest request) {
		String appName = request.getParameter("appName");
		String appDescription = request.getParameter("appDescription");
		String appPlatform = request.getParameter("appPlatform");
		return null;
	}



}
