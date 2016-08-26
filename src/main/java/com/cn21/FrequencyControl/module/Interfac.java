package com.cn21.FrequencyControl.module;

import java.util.List;

public class Interfac {
    
	private long interface_id;
    private long app_id;
    private String api_name;
    private int frequency;
    private int timeout;
    private char unit;
    private short deleted;
    private List<Parameter> parameters;
	/**
	 * @return the interface_id
	 */
	public long getInterface_id() {
		return interface_id;
	}
	/**
	 * @param interface_id the interface_id to set
	 */
	public void setInterface_id(long interface_id) {
		this.interface_id = interface_id;
	}
	/**
	 * @return the app_id
	 */
	public long getApp_id() {
		return app_id;
	}
	/**
	 * @param app_id the app_id to set
	 */
	public void setApp_id(long app_id) {
		this.app_id = app_id;
	}
	/**
	 * @return the api_name
	 */
	public String getApi_name() {
		return api_name;
	}
	/**
	 * @param api_name the api_name to set
	 */
	public void setApi_name(String api_name) {
		this.api_name = api_name;
	}
	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}
	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	/**
	 * @return the unit
	 */
	public char getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(char unit) {
		this.unit = unit;
	}
	/**
	 * @return the deleted
	 */
	public short getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(short deleted) {
		this.deleted = deleted;
	}
	/**
	 * @return the parameters
	 */
	public List<Parameter> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
    
}
