package com.cn21.FrequencyControl.dto;

import java.util.Date;

/**
 * Created by Steven on 2016/8/15.
 */
public class BlacklistDto {
    private String appMarking;
    private String username;
    private String limitedIp;
    private Date firDate;
    private Date secDate;
    private Date thrDate;
    private Date absoulteDate;

    public String getAppMarking() {
        return appMarking;
    }

    public void setAppMarking(String appMarking) {
        this.appMarking = appMarking;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLimitedIp() {
        return limitedIp;
    }

    public void setLimitedIp(String limitedIp) {
        this.limitedIp = limitedIp;
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
}
