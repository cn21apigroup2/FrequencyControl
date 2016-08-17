package com.cn21.FrequencyControl.service;

import com.cn21.FrequencyControl.dto.BlacklistDto;
import com.cn21.FrequencyControl.module.Blacklist;

import java.util.List;

/**
 * Created by steven on 2016/8/17.
 */
public interface BlacklistService {


    /**
     * 查询黑名单
     * @param appKey
     * @param username
     * @return
     */
    BlacklistDto queryByUsername(String appKey,String username);


    BlacklistDto queryByIp(String appKey,String ip);

    List<BlacklistDto> query(String appKey);


    /**
     * 通过黑名单验证权限
     * @param appKey
     * @param username
     * @param ip
     * @return
     */
    boolean TestingAccess(String appKey,String username,String ip);


    /**
     * 重置黑名单信息
     * @param appKey
     * @param username
     * @param ip
     * @return
     */
    BlacklistDto reset(String appKey,String username,String ip);

    void add(Blacklist blacklist);
}
