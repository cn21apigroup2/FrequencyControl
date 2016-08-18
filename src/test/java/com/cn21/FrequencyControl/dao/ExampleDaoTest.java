/**
 *  @Title: ExampleDaoTest.java 
 *  @Package com.cn21.FrequencyControl.dao 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月16日 下午5:30:23 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn21.FrequencyControl.module.Example;

/**
 * @author chenxiaofeng
 * @date 2016年8月16日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-config.xml"})
public class ExampleDaoTest {
	@Autowired
	private  ExampleDao exampleDao;

	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ExampleDao#getExampleByName(java.lang.String)}.
	 */
	@Test
	public void testGetExampleByName() {
		Example expected=new Example();
		expected.setName("陈小锋");
		Example actual = exampleDao.getExampleByName("陈小锋");
		assertTrue(expected.getName().equals(actual.getName()));
		
	}

}
