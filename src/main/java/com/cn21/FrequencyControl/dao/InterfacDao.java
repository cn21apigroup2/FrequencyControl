package com.cn21.FrequencyControl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cn21.FrequencyControl.module.InterfaceControl;
import com.cn21.FrequencyControl.module.Parameter;
@Repository
public interface InterfacDao {
	int addInterfac(InterfaceControl interfac);

	List<InterfaceControl> getInterfacListByAppId(long appId) ;
		
	List<InterfaceControl> getDeletedInterfacListByAppId(long appId);	

	InterfaceControl getInterfacByInterId(long interId);
	
	InterfaceControl getOverallControl(long appId,String apiName);

	int updateInterfac(InterfaceControl interfac);

	int updateInterFrequency(InterfaceControl interfac);
	
	int updateInterTimeout(InterfaceControl interfac);
	
	int updateInterUnit(InterfaceControl interfac);
	
	int deleteInterfac(long interId);

	int regainInterfac(long interface_id);
	
	List<Parameter> getParameters(long interface_id);
}
