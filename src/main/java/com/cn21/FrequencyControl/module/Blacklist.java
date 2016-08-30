package com.cn21.FrequencyControl.module;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by Steven on 2016/8/15.
 */
public class Blacklist {
    private int blacklistId;
    private String appKey;
    private String customerId;
    private String limitedIp;
    private short times;
    private Timestamp firDate;
    private Timestamp secDate;
    private Timestamp thrDate;
    private Timestamp absoluteDate;
    private short state;

    public int getBlacklistId() {
        return blacklistId;
    }

    public void setBlacklistId(int blacklistId) {
        this.blacklistId = blacklistId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getUsername() {
		return getCustomerId();
	}

	public void setUsername(String username) {
		setCustomerId(username);
	}

    public String getLimitedIp() {
        return limitedIp;
    }

    public void setLimitedIp(String limitedIp) {
        this.limitedIp = limitedIp;
    }

    public short getTimes() {
        return times;
    }

    public void setTimes(short times) {
        this.times = times;
    }

    public Timestamp getFirDate() {
        return firDate;
    }

    public void setFirDate(Timestamp firDate) {
        this.firDate = firDate;
    }

    public Timestamp getSecDate() {
        return secDate;
    }

    public void setSecDate(Timestamp secDate) {
        this.secDate = secDate;
    }

    public Timestamp getThrDate() {
        return thrDate;
    }

    public void setThrDate(Timestamp thrDate) {
        this.thrDate = thrDate;
    }

    public Date getAbsoluteDate() {
        return absoluteDate;
    }

    public void setAbsoluteDate(Timestamp absoluteDate) {
        this.absoluteDate = absoluteDate;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }
    
    /**
	 * 把jsonArray字符串转化出pojo
	 * @param message
	 * @return
	 */
	public static List<Blacklist> parse(String message) {
		List<Blacklist> result=new ArrayList<Blacklist>();
		JSONArray parseArray = JSONArray.parseArray(message);
		ObjectMapper objectMapper = new ObjectMapper();
		for(int i=0;i<parseArray.size();i++){
			try {
				result.add(objectMapper.readValue(parseArray.getString(i), Blacklist.class));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
