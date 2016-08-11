/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn21.FrequencyControl.dao.ExampleDao;
import com.cn21.FrequencyControl.module.Example;
import com.cn21.FrequencyControl.service.ExampleService;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
@Service
public class ExampleServiceImpl implements ExampleService{
	@Autowired
	private ExampleDao exampleDao;

	public Example showExample(String name) {
		// TODO Auto-generated method stub
		return exampleDao.getExampleByName(name);
	}


}
