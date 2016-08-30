package com.cn21.FrequencyControl.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn21.FrequencyControl.module.InterfaceControl;
import com.cn21.FrequencyControl.module.Parameter;

/**
 * @author zhangqingxiang
 * @date 2016年8月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-config.xml"})
public class ParameterDaoTest {
	@Autowired
	private ParameterDao parameterDao;
	private Parameter getParameter(){
		Parameter parameter = new Parameter();
		parameter.setParameter_id(14);
		parameter.setInterface_id(1);
		parameter.setParameter_key("ere");
		parameter.setParameter_value("2");	
		return parameter;
	}
	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ParameterDao#addParameter(com.cn21.FrequencyControl.module.Parameter)}.
	 */
	@Test
	public void testAddParameter() {
		int expected=1;
		int actual = parameterDao.addParameter(getParameter());
		assertEquals(expected,actual);
	}
	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.InterfacDao#getInterfacListByAppId(int)}.
	 */
	@Test
	public void testGetParameterListByInterId() {
		int expected=6;
		int expectedInterId=1;
		List<Parameter> actual = parameterDao.getParameterListByInterId(expectedInterId);
		assertEquals(expected,actual.size());
		Parameter actualParameter = actual.get(5);
		Parameter expectedParameter = getParameter();	
		assertTrue(expectedParameter.getInterface_id()==actualParameter.getInterface_id());
		assertTrue(expectedParameter.getParameter_key().equals(actualParameter.getParameter_key()));
		assertTrue(expectedParameter.getParameter_value().equals(actualParameter.getParameter_value()));		
	}
	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ParameterDao#getParameterByParaId(java.lang.String)}.
	 */
	@Test
	public void testGetParameterByParaId() {
		Parameter expected=getParameter();
		Parameter actual = parameterDao.getParameterByParaId(expected.getParameter_id());
		assertTrue(expected.getInterface_id()==actual.getInterface_id());
		assertTrue(expected.getParameter_key().equals(actual.getParameter_key()));
		assertTrue(expected.getParameter_value().equals(actual.getParameter_value()));
		
	}
	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ParameterDao#updateParameterKey(com.cn21.FrequencyControl.module.Parameter)}.
	 */
	@Test
	public void testUpdateParameterKey() {
		Parameter parameterByParaId = parameterDao.getParameterByParaId(getParameter().getParameter_id());
		String newParameterKey="qwe";
		parameterByParaId.setParameter_key(newParameterKey);
		int actual = parameterDao.updateParameterKey(parameterByParaId);
		int expected=1;
		assertEquals(actual,expected);
	}
	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ParameterDao#deleteParameter(java.lang.String)}.
	 */
	@Test
	public void testDeleteParameter() {
		parameterDao.deleteParameter(14);
	}
}
