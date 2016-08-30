package com.cn21.FrequencyControl.dao;

import com.cn21.FrequencyControl.dto.BlacklistDto;
import com.cn21.FrequencyControl.module.Blacklist;
import com.cn21.FrequencyControl.service.BlacklistService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 2016/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-config.xml"})
public class BlacklistServiceTest {
    @Resource
    private BlacklistService blacklistService;
    @Resource
    private BlacklistDao bd;
    @Test
    public void addTest(){
        Blacklist blacklist = new Blacklist();
        blacklist.setAppKey("ADSDI2NNGR00ASD8");
        blacklist.setCustomerId("Destiny-4");
        blacklist.setLimitedIp("192.168.199.4");
        short times = 1;
        blacklist.setTimes(times);
        blacklist.setFirDate(new Timestamp(System.currentTimeMillis()));
        blacklistService.add(blacklist);
    }
    //@Test
    public void queryByUsrnameTest(){
        Blacklist bd;
        bd=blacklistService.queryByUsername("ADSDI2NNGR00ASD8","Destiny");
        if(bd==null){
            System.out.println("null");
        }else {
            System.out.println(bd.toString());
        }
    }
    //@Test
    public void queryByIpTest(){
        Blacklist bd;
        bd=blacklistService.queryByIp("ADSDI2NNGR00ASD8","192.168.199.256");
        if(bd==null){
            System.out.println("null");
        }else {
            System.out.println(bd.toString());
        }
    }
    //@Test
    public void queryTest(){
        List<Blacklist> list;
        list=blacklistService.query("ADSDI2NNGR00ASD8");
        if(list.isEmpty()){
            System.out.println("null");
        }else {
            System.out.println(list.get(0).toString());
        }
    }

    public void testingByUsernameTest(){
        Map<String,Date> list;
        //list=blacklistService.TestingAccess("ADSDI2NN2S00ASD8","DESTINY","");
    }

    public void testingByIpTest(){}
    @Test
    public void resetTest(){
        //blacklistService.reset("ADSDI2NNGR00ASD8","Destiny","192.168.199.256");
        bd.resetByIp("ADSDI2NNGR00ASD8", "192.168.199.1");
    }
    @Test
    public void modifyTest(){
        Blacklist blacklist = new Blacklist();
        blacklist.setAppKey("ADSDI2NNGR00ASD8");
        blacklist.setBlacklistId(1);
        blacklist.setCustomerId("Destiny");
        blacklist.setLimitedIp("192.168.199.256");
        short times = 0;
        blacklist.setTimes(times);
        bd.modify(blacklist);


    }
    //@Test
    public void deleteTest(){

        bd.delete(2);
    }

}
