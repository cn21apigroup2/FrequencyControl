package com.cn21.FrequencyControl.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn21.FrequencyControl.dao.InterfacDao;
import com.cn21.FrequencyControl.module.InterfaceControl;
import com.cn21.FrequencyControl.service.InterfacService;

@Service
public class InterfacServiceImpl implements InterfacService{
	@Autowired
	private InterfacDao interfacDao;
	/**
	 * 根据appId获取interface列表
	 * @param appId
	 * @return
	 */
	public List<InterfaceControl> getInterfacListByAppId(long appId){
		 return interfacDao.getInterfacListByAppId(appId);
	}
	@Override
	public List<InterfaceControl> getDeletedInterfacListByAppId(long appId) {
		// TODO Auto-generated method stub
		return interfacDao.getDeletedInterfacListByAppId(appId);
	}
	@Override
	public boolean regainInterfac(long interface_id) {
		// TODO Auto-generated method stub
		int successCount=interfacDao.regainInterfac(interface_id);
		if(successCount==1)return true;
		return false;
	}
	@Override
	public boolean deleteInterfac(long interface_id) {
		// TODO Auto-generated method stub
		int successCount=interfacDao.deleteInterfac(interface_id);
		if(successCount==1)return true;
		return false;
	}
	@Override
	public boolean updateInterfac(InterfaceControl interfac) {
		// TODO Auto-generated method stub
		int successCount=interfacDao.updateInterfac(interfac);
		if(successCount==1)return true;
		return false;
	}
	@Override
	public InterfaceControl getInterfacByInterId(long interId) {
		// TODO Auto-generated method stub		
		return interfacDao.getInterfacByInterId(interId);
	}
	@Override
	public boolean createInterfac(InterfaceControl interfac) {
		// TODO Auto-generated method stub
		int successCount = interfacDao.addInterfac(interfac);
		if(successCount==1) return true;
		return false;
	}
	@Override
	public InterfaceControl generateInterfac(HttpServletRequest request, long appId) {
		String apiName = request.getParameter("apiName");
		String apiFrequency = request.getParameter("apiFrequency");
		String timeout = request.getParameter("timeout");
		String unit = request.getParameter("unit");
		InterfaceControl interfac = new InterfaceControl();
		interfac.setApi_name(apiName);
		interfac.setFrequency(Integer.parseInt(apiFrequency));
		interfac.setTimeout(Integer.parseInt(timeout));
		interfac.setUnit(unit.charAt(0));
		interfac.setApp_id(appId);
		return interfac;
	}
	@Override
	public boolean modifyInterFrequency(InterfaceControl interfac) {
		// TODO Auto-generated method stub
		int successCount = interfacDao.updateInterFrequency(interfac);
		if(successCount==1) return true;
		return false;
	}
	@Override
	public boolean modifyInterTimeout(InterfaceControl interfac) {
		// TODO Auto-generated method stub
		int successCount = interfacDao.updateInterTimeout(interfac);
		if(successCount==1) return true;
		return false;
	}
	@Override
	public boolean modifyInterUnit(InterfaceControl interfac) {
		// TODO Auto-generated method stub
		int successCount = interfacDao.updateInterUnit(interfac);
		if(successCount==1) return true;
		return false;
	}
	@Override
	public boolean modifyInterfac(InterfaceControl interfac) {
		// TODO Auto-generated method stub
		int successCount = interfacDao.updateInterfac(interfac);
		if(successCount==1) return true;
		return false;
	}
	@Override
	public void createOverallControl(long appId) {
		InterfaceControl interfac = new InterfaceControl();
		interfac.setApi_name(OVERALL_CONTROL);
		interfac.setFrequency(-1);
		interfac.setTimeout(1);
		interfac.setUnit('d');
		interfac.setApp_id(appId);
		interfacDao.addInterfac(interfac);
		
	}
	@Override
	public InterfaceControl getOverAllControl(List<InterfaceControl> interfacs) {
		InterfaceControl overallControl=null;
		for (InterfaceControl interfac : interfacs) {//获取全局控制接口
			if(interfac.getApi_name().equals(InterfacService.OVERALL_CONTROL)){
				overallControl=interfac;
				interfacs.remove(interfac);
				break;
			}
		}
		return overallControl;
	}
}
