/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.dao;

import com.cn21.FrequencyControl.module.Example;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
public interface ExampleDao {

Example getExampleByName(String name);
}
