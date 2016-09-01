/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn21.FrequencyControl.controller.common.Page;
import com.cn21.FrequencyControl.dao.ApplicationDao;
import com.cn21.FrequencyControl.module.Application;
import com.cn21.FrequencyControl.service.ApplicationService;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
@Service
@Transactional
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
		return applicationDao.getDeletedAppListByUserId(userId);
	}

	/* (non-Javadoc)
	 * 根据appkey恢复被删除的应用
	 * @see com.cn21.FrequencyControl.service.ApplicationService#regainApplication(java.lang.String)
	 */
	@Override
	public boolean regainApplication(long appId) {
		// TODO Auto-generated method stub
		int successCount = applicationDao.regainApplication(appId);
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
	public boolean deleteApplication(long appId) {
		// TODO Auto-generated method stub
		int successCount = applicationDao.deleteApplication(appId);
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

	/* (non-Javadoc)
	 * 生成application
	 * @see com.cn21.FrequencyControl.service.ApplicationService#generateApp(javax.servlet.http.HttpServletRequest, long)
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Application generateApp(HttpServletRequest request,long userId) {
		String appName = request.getParameter("appName");
		String appDescription = request.getParameter("appDescription");
		String appPlatform = request.getParameter("appPlatform");
		Map<String, String> appKeyAndSecret = generateAppKeyAndSecret(userId);
		Application application = new Application();
		application.setApp_name(appName);
		application.setApp_description(appDescription);
		application.setPlatform(appPlatform);
		application.setApp_key(appKeyAndSecret.get("appKey"));
		application.setSecret(appKeyAndSecret.get("secret"));
		application.setUser_id(userId);
		return application;
	}
	/**
	 * appKey和secret的生成算法
	 * @param userId
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private Map<String,String> generateAppKeyAndSecret(long userId){
		Map<String, String> result = new HashMap<String, String>();
		userId = userId % 1000000000;// userId控制在9位数以内
		Lock lock = new ReentrantLock();
		String uuid = null;
		lock.lock();//互斥访问
		try {
			uuid = UUID.randomUUID().toString();
		} finally {
			lock.unlock();
		}
		result.put("appKey", userId + uuid.substring(0, 8));// appKey为userId低9位+UUID高8位
		result.put("secret", userId + uuid.substring(24));// secret为userId低9位+UUID低12位
		return result;
	}

	@Override
	public Application getApplicationByAppId(long appId) {
		// TODO Auto-generated method stub
		return applicationDao.getApplicationByAppId(appId);
	}

	@Override
	public boolean modifyApplication(Application application) {
		// TODO Auto-generated method stub
		int successCount=applicationDao.updateApplication(application);
		if(successCount==1) return true;
		return false;
	}

	/* (non-Javadoc)
	 * 分页查询
	 * @see com.cn21.FrequencyControl.service.ApplicationService#getPageByUserId(long, int, int)
	 */
	@Override
	public Page<Application> getPageByUserId(long userId, int pageNo,
			int pageSize) {
		if(pageNo<=0) pageNo=Page.DEFAULT_PAGE_NO;
		Page<Application> page = new Page<Application>();
		int totalCount = applicationDao.getTotalCount(userId);
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userId", userId);
		paramMap.put("beginIndex", (pageNo-1)*pageSize);
		paramMap.put("pageSize", pageSize);
		List<Application> records = applicationDao.getPageByUserId(paramMap);
		page.setRecords(records);
		page.setTotalSize(totalCount);
		page.setPageSize(pageSize); //分页查询setTotalSize setPageSize 必须在setPageNo和setMaxPage之前设置
		page.setPageNo(pageNo);
		page.setMaxPage(page.getTotalPages());
		return page;
	}


}
