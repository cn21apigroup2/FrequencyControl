package com.cn21.FrequencyControl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cn21.FrequencyControl.module.Interfac;


/**
 * @author zhangqingxiang
 * @date 2016年8月22日
 */
public interface InterfacService {
    
	/**
	 * 根据appId获取接口列表
	 * @param appId
	 * @return
	 */
	List<Interfac> getInterfacListByAppId(long appId);
	/**
	 * 根据appId获取已删除接口列表
	 * @param appId
	 * @return
	 */
	List<Interfac> getDeletedInterfacListByAppId(long appId);
	/**
	 * 根据interface_id恢复被删除的应用
	 * @param appId
	 * @return
	 */
	boolean regainInterfac(long interface_id);
	/**
	 * 创建interface
	 * @param interface
	 * @return
	 */
	boolean createInterfac(Interfac interfac);
	/**
	 * 修改接口次数
	 * @param interface
	 * @return
	 */
	boolean modifyInterFrequency(Interfac interfac);

	/**
	 * 修改控制时间
	 * @param interface
	 * @return
	 */
	boolean modifyInterTimeout(Interfac interfac);
	
	/**
	 * 修改时间单位
	 * @param interface
	 * @return
	 */
	boolean modifyInterUnit(Interfac interfac);
	
	/**
	 * 更新接口
	 * @param interface
	 * @return
	 */
	boolean modifyInterfac(Interfac interfac);
	
	boolean deleteInterfac(long interId);
	
	boolean updateInterfac(Interfac interfac);
	
	Interfac getInterfacByInterId(long interId);
	Interfac generateInterfac(HttpServletRequest request,long appId);
}
