package com.cn21.FrequencyControl.module;

import java.util.Date;

/**
 * Created by Steven on 2016/8/15.
 */
public class Blacklist {
    private int blacklistId;
    private int userId;
    private int appId;
    private String appMarking;
    private String customerId;
    private String limitedIp;
    private short times;
    private Date firDate;
    private Date secDate;
    private Date thrDate;
    private Date absoulteDate;
    private short state;

    public int getBlacklistId() {
        return blacklistId;
    }

    public void setBlacklistId(int blacklistId) {
        this.blacklistId = blacklistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppMarking() {
        return appMarking;
    }

    public void setAppMarking(String appMarking) {
        this.appMarking = appMarking;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public Date getFirDate() {
        return firDate;
    }

    public void setFirDate(Date firDate) {
        this.firDate = firDate;
    }

    public Date getSecDate() {
        return secDate;
    }

    public void setSecDate(Date secDate) {
        this.secDate = secDate;
    }

    public Date getThrDate() {
        return thrDate;
    }

    public void setThrDate(Date thrDate) {
        this.thrDate = thrDate;
    }

    public Date getAbsoulteDate() {
        return absoulteDate;
    }

    public void setAbsoulteDate(Date absoulteDate) {
        this.absoulteDate = absoulteDate;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }
}
