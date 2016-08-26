package com.cn21.FrequencyControl.module;

public class Parameter {

	private long parameter_id;
	private long interface_id;
	private String parameter_key;
	private String parameter_value;
	
	public long getParameter_id() {
		return parameter_id;
	}
	public void setParameter_id(long parameter_id) {
		this.parameter_id = parameter_id;
	}
	public long getInterface_id() {
		return interface_id;
	}
	public void setInterface_id(long interface_id) {
		this.interface_id = interface_id;
	}
	public String getParameter_key() {
		return parameter_key;
	}
	public void setParameter_key(String parameter_key) {
		this.parameter_key = parameter_key;
	}
	public String getParameter_value() {
		return parameter_value;
	}
	public void setParameter_value(String parameter_value) {
		this.parameter_value = parameter_value;
	}
}
