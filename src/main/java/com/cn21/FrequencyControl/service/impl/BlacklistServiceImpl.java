package com.cn21.FrequencyControl.service.impl;

import com.cn21.FrequencyControl.dao.BlacklistDao;
import com.cn21.FrequencyControl.dto.BlacklistDto;
import com.cn21.FrequencyControl.module.Blacklist;
import com.cn21.FrequencyControl.service.BlacklistService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by steven on 2016/8/17.
 */
@Service
@Transactional
public class BlacklistServiceImpl implements BlacklistService {
    @Resource
    private BlacklistDao bd;

    /**
     * 根据用户名查询黑名单
     * @param appKey
     * @param username
     * @return
     */
    @Override
    public Blacklist queryByUsername(String appKey, String username) {
        return bd.queryByUsername(appKey, username);
    }

    /**
     * 根据IP查询黑名单
     * @param appKey
     * @param ip
     * @return
     */
    @Override
    public Blacklist queryByIp(String appKey, String ip) {
        return bd.queryByIp(appKey, ip);
    }

    /**
     *查询应用的黑名单列表
     * @param appKey
     * @return
     */
    @Override
    public List<Blacklist> query(String appKey) {
        return bd.queryAllByAppKey(appKey);
    }

    /**
     * 通过黑名单验证权限
     * @param appKey
     * @param username
     * @param ip
     * @return
     */
    @Override
    public boolean TestingAccess(String appKey, String username, String ip) {
        if (username != null && !username.equals("")) {
            if (bd.queryByUsername(appKey, username) != null) {
                return false;
            } else {
                return true;
            }
        } else {
            if (ip != null && !ip.equals("")) {
                if (bd.queryByIp(appKey, ip) != null) {
                    return false;
                } else {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 重置一条黑名单记录
     * @param appKey
     * @param username
     * @param ip
     * @return
     */
    @Override
    public Blacklist reset(String appKey, String username, String ip) {
        if(!username.equals("")&&username!=null){
            bd.reset(appKey, username);
            return queryByUsername(appKey,username);
        }else {
            bd.resetByIp(appKey, ip);
            return queryByIp(appKey,ip);
        }
    }

    /**
     * 新增一套黑名单记录
     * @param blacklist
     */
    @Override
    public void add(Blacklist blacklist){
        bd.add(blacklist);
    }

	@Override
	public boolean update(Blacklist blackList) {
		// TODO Auto-generated method stub
		 int successCount=bd.modify(blackList);
		 if(successCount==1) return true;
		 return false;
	}
}
