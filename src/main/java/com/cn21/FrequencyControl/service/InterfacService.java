package com.cn21.FrequencyControl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cn21.FrequencyControl.module.InterfaceControl;


/**
 * @author zhangqingxiang
 * @date 2016年8月22日
 */
public interface InterfacService {
	public static final String OVERALL_CONTROL="#overall#";
    
	/**
	 * 根据appId获取接口列表
	 * @param appId
	 * @return
	 */
	List<InterfaceControl> getInterfacListByAppId(long appId);
	/**
	 * 根据appId获取已删除接口列表
	 * @param appId
	 * @return
	 */
	List<InterfaceControl> getDeletedInterfacListByAppId(long appId);
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
	boolean createInterfac(InterfaceControl interfac);
	/**
	 * 修改接口次数
	 * @param interface
	 * @return
	 */
	boolean modifyInterFrequency(InterfaceControl interfac);

	/**
	 * 修改控制时间
	 * @param interface
	 * @return
	 */
	boolean modifyInterTimeout(InterfaceControl interfac);
	
	/**
	 * 修改时间单位
	 * @param interface
	 * @return
	 */
	boolean modifyInterUnit(InterfaceControl interfac);
	
	/**
	 * 更新接口
	 * @param interface
	 * @return
	 */
	boolean modifyInterfac(InterfaceControl interfac);
	
	boolean deleteInterfac(long interId);
	
	boolean updateInterfac(InterfaceControl interfac);
	
	InterfaceControl getInterfacByInterId(long interId);
	
	InterfaceControl generateInterfac(HttpServletRequest request,long appId);
	
	void createOverallControl(long appId);
	
	InterfaceControl getOverAllControl(List<InterfaceControl> interfacs);
}
