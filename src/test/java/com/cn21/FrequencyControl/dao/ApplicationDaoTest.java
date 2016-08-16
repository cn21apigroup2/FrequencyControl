/**
 *  @Title: ApplicationDaoTest.java 
 *  @Package com.cn21.FrequencyControl.dao 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月16日 上午11:58:26 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn21.FrequencyControl.module.Application;

/**
 * @author chenxiaofeng
 * @date 2016年8月16日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-config.xml"})
public class ApplicationDaoTest {
	@Autowired
	private ApplicationDao applicationDao;
	private Application getApplication(){
		Application application = new Application();
		application.setUser_id(1);
		application.setApp_key("123456");
		application.setPlatform("IOS");
		application.setSecret("123456");
		application.setCreate_date(new Timestamp(System.currentTimeMillis()));
		application.setIs_reviewed(0);
		application.setApp_description("天翼云是中国电信推出的云存储服务,为用户提供跨平台的文件存储、"
				+ "备份、同步及分享服务,是国内领先的免费网盘,安全、可靠、稳定、快速。天翼云为用户守护数据资产。");
		return application;
	}
	
	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ApplicationDao#addApplication(com.cn21.FrequencyControl.module.Application)}.
	 */
	@Test
	public void testAddApplication() {
		int expected=1;
		int actual = applicationDao.addApplication(getApplication());
		assertEquals(expected,actual);
	}

	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ApplicationDao#getAppListByUserId(int)}.
	 */
	@Test
	public void testGetAppListByUserId() {
		int expected=1;
		int expectedUserId=1;
		List<Application> actual = applicationDao.getAppListByUserId(1);
		assertEquals(expected,actual.size());
		Application actualApplication = actual.get(0);
		Application expectedApplication = getApplication();
		assertTrue(expectedApplication.getApp_description().equals(actualApplication.getApp_description()));
		assertTrue(expectedApplication.getPlatform().equals(actualApplication.getPlatform()));
		assertTrue(expectedApplication.getApp_key().equals(actualApplication.getApp_key()));
		assertTrue(expectedApplication.getSecret().equals(actualApplication.getSecret()));
		assertTrue(expectedApplication.getCreate_date().toString().equals(actualApplication.getCreate_date().toString()));
		assertTrue(expectedApplication.getIs_reviewed()==actualApplication.getIs_reviewed());
		assertTrue(expectedApplication.getDeleted()==actualApplication.getDeleted());
		
	}

	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ApplicationDao#getApplicationByAppKey(java.lang.String)}.
	 */
	@Test
	public void testGetApplicationByAppKey() {
		Application expected=getApplication();
		Application actual = applicationDao.getApplicationByAppKey(expected.getApp_key());
		assertTrue(expected.getApp_description().equals(actual.getApp_description()));
		assertTrue(expected.getPlatform().equals(actual.getPlatform()));
		assertTrue(expected.getApp_key().equals(actual.getApp_key()));
		assertTrue(expected.getCreate_date().toString().equals(actual.getCreate_date().toString()));
		assertTrue(expected.getIs_reviewed()==actual.getIs_reviewed());
		assertTrue(expected.getSecret().equals(actual.getSecret()));
		assertTrue(expected.getDeleted()==actual.getDeleted());
	}

	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ApplicationDao#updateAppDescription(com.cn21.FrequencyControl.module.Application)}.
	 */
	@Test
	public void testUpdateAppDescription() {
		Application applicationByAppKey = applicationDao.getApplicationByAppKey(getApplication().getApp_key());
		String newDescription="天翼云是中国电信旗下面向最终消费者的云存储产品，是基于云计算技术的个人/家庭云数据中心，"
				+ "能够提供文件同步、备份及分享等服务的网络云存储平台。您可以通过网页、PC客户端及移动客户端随时随地的把照片、"
				+ "音乐、视频、文档等轻松地保存到网络，无须担心文件丢失。通过天翼云，多终端上传和下载、管理、分享文件变得轻而易举。";
		applicationByAppKey.setApp_description(newDescription);
		int actual = applicationDao.updateAppDescription(applicationByAppKey);
		int expected=1;
		assertEquals(actual,expected);
	}

	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ApplicationDao#updateAppPlatform(com.cn21.FrequencyControl.module.Application)}.
	 */
	@Test
	public void testUpdateAppPlatform() {
		Application applicationByAppKey = applicationDao.getApplicationByAppKey(getApplication().getApp_key());
		String newPlatform="Android";
		applicationByAppKey.setPlatform(newPlatform);
		int actual = applicationDao.updateAppPlatform(applicationByAppKey);
		int expected=1;
		assertEquals(actual,expected);
	}

	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ApplicationDao#deleteApplication(java.lang.String)}.
	 */
	@Test
	public void testDeleteApplication() {
		int actual = applicationDao.deleteApplication(getApplication().getApp_key());
		int expected=1;
		assertEquals(expected,actual);
	}

}
