package com.cn21.FrequencyControl.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn21.FrequencyControl.module.Interfac;

/**
 * @author zhangqingxiang
 * @date 2016年8月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-config.xml"})
public class InterfacDaoTest {

	@Autowired
	private InterfacDao interfacDao;
	private Interfac getInterfac(){
		Interfac interfac = new Interfac();
		interfac.setApp_id(1);
		interfac.setApi_name("大头1");
		interfac.setFrequency(1234);
		interfac.setInterface_id(1);
		interfac.setTimeout(23);
		interfac.setUnit("s");		
		return interfac;
	}
	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.InterfacDao#addInterfac(com.cn21.FrequencyControl.module.Interfac)}.
	 */
	@Test
	public void testAddInterfac() {
		int expected=1;
		int actual = interfacDao.addInterfac(getInterfac());
		assertEquals(expected,actual);
	}
	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.InterfacDao#getInterfacListByAppId(int)}.
	 */
	@Test
	public void testGetInterfacListByAppId() {
		int expected=5;
		int expectedAppId=1;
		List<Interfac> actual = interfacDao.getInterfacListByAppId(1);
		assertEquals(expected,actual.size());
		Interfac actualInterfac = actual.get(4);
		Interfac expectedInterfac = getInterfac();	
		assertTrue(expectedInterfac.getFrequency()==actualInterfac.getFrequency());
		assertTrue(expectedInterfac.getApi_name().equals(actualInterfac.getApi_name()));
		assertTrue(expectedInterfac.getApp_id()==actualInterfac.getApp_id());
		assertTrue(expectedInterfac.getTimeout()==actualInterfac.getTimeout());		
		assertTrue(expectedInterfac.getDeleted()==actualInterfac.getDeleted());
		
	}

	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.InterfacDao#getInterfacByInterId(java.lang.String)}.
	 */
	@Test
	public void testGetInterfacByInterId() {
		Interfac expected=getInterfac();
		Interfac actual = interfacDao.getInterfacByInterId(expected.getInterface_id());
		assertTrue(expected.getApi_name().equals(actual.getApi_name()));
		assertTrue(expected.getFrequency()==actual.getFrequency());
		assertTrue(expected.getApp_id()==actual.getApp_id());
		assertTrue(expected.getInterface_id()==actual.getInterface_id());
		assertTrue(expected.getDeleted()==actual.getDeleted());
	}

	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.InterfacDao#updateInterFrequency(com.cn21.FrequencyControl.module.Interfac)}.
	 */
	@Test
	public void testUpdateInterFrequency() {
		Interfac interfacByInterId = interfacDao.getInterfacByInterId(getInterfac().getInterface_id());
		String newFrequency="1232";
		long  l = Long.valueOf(newFrequency).longValue();
		interfacByInterId.setFrequency(l);
		int actual = interfacDao.updateInterFrequency(interfacByInterId);
		int expected=1;
		assertEquals(actual,expected);
	}
	/**
	 * Test method for {@link com.cn21.FrequencyControl.dao.ApplicationDao#deleteApplication(java.lang.String)}.
	 */
	@Test
	public void testDeleteInterfac() {
		int actual = interfacDao.deleteInterfac(getInterfac().getInterface_id());
		int expected=1;
		assertEquals(expected,actual);
	}
}
