package com.cn21.FrequencyControl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn21.FrequencyControl.module.Application;
import com.cn21.FrequencyControl.module.User;

/**
 * @Author Aaron
 * @Date 创建时间：2016-8-16
 * @Version 1.0 
 *
 * @Project_Package_Description springmvc || com.cn21.FrequencyControl.dao
 * @Function_Description DAO层接口
 *
 */
@SuppressWarnings("unused")
@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=false,isolation=Isolation.DEFAULT)
public interface ApplicationDAO {
	public int insertApplication(Application application);
	public void updateApplication(Application application);
	public void deleteApplication(int app_id);
	public List<Application> findAllApplication();	
	public Application findByName(String user_id);
	public Application findById(int app_id);
	public Application findByUser_id(String user_id);
}
