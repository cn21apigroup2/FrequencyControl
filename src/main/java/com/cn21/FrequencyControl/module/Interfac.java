package com.cn21.FrequencyControl.module;

public class Interfac {
    
	private long interface_id;
    private long app_id;
    private String api_name;
    private long frequency;
    private long timeout;
    private String unit;
    private short deleted;
	
	public long getInterface_id() {
		return interface_id;
	}
	public void setInterface_id(long interface_id) {
		this.interface_id = interface_id;
	}
	public long getApp_id() {
		return app_id;
	}
	public void setApp_id(long app_id) {
		this.app_id = app_id;
	}
	public String getApi_name() {
		return api_name;
	}
	public void setApi_name(String api_name) {
		this.api_name = api_name;
	}
	public long getFrequency() {
		return frequency;
	}
	public void setFrequency(long apiFrequency) {
		this.frequency = apiFrequency;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public short getDeleted() {
		return deleted;
	}
	public void setDeleted(short deleted) {
		this.deleted = deleted;
	}
	
}
