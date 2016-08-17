package com.cn21.FrequencyControl.dao;

import com.cn21.FrequencyControl.dto.BlacklistDto;
import com.cn21.FrequencyControl.module.Blacklist;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Steven on 2016/8/15.
 */
public interface BlacklistDao {


    /**
     * 通过appKey查询黑名单数据
     * @param appKey
     * @return
     */
    List<BlacklistDto> query(String appKey);

    /**
     * 通过用户名查询黑名单数据
     * @param username
     * @return
     */
    BlacklistDto queryByUsername(String appKey,String username);

    /**
     * 通过ip地址查询黑名单数据
     * @param ip
     * @return
     */
    BlacklistDto queryByIp(String appKey,String ip);

    /**
     * 通过用户名判断权限
     * @param username
     * @return
     */
    Map<String,Date> testingByUsername(String appKey,String username);

    /**
     * 通过ip判断权限
     * @param ip
     * @return
     */
    Map<String,Date> testingByIp(String appKey,String ip);

    /**
     * 添加一条黑名单记录
     * @param blacklist
     * @return
     */
    void add(Blacklist blacklist);

    /**
     * 重置一条记录（解除冻结）
     * @param appKey
     * @Param username
     * @return
     */
    void reset(String appKey,String username);

    /**
     * 通过IP重置一条记录
     * @param appKey
     * @param ip
     */
    void resetByIp(String appKey,String ip);
    /**
     * 修改黑名单数据内容
     * @param blacklist
     * @return
     */
    void modify(Blacklist blacklist);

    /**
     * 删除一条记录
     * @param id
     * @return
     */
    void delete(int id);
}
