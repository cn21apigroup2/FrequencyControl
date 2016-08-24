package com.cn21.FrequencyControl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cn21.FrequencyControl.module.Interfac;
@Repository
public interface InterfacDao {
	int addInterfac(Interfac interfac);

	List<Interfac> getInterfacListByAppId(long appId) ;
		
	List<Interfac> getDeletedInterfacListByAppId(long appId);	

	Interfac getInterfacByInterId(long interId);

	int updateInterfac(Interfac interfac);

	int updateInterFrequency(Interfac interfac);
	
	int updateInterTimeout(Interfac interfac);
	
	int updateInterUnit(Interfac interfac);
	
	int deleteInterfac(long interId);

	int regainInterfac(long interface_id);
}