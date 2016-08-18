package com.cn21.FrequencyControl.controller;

import com.cn21.FrequencyControl.service.BlacklistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * Created by steven on 2016/8/17.
 */
@Controller
@RequestMapping("/blacklist")
public class BlacklistController {

    @Resource
    private BlacklistService bs;

    /**
     * 查询整个APP的黑名单列表
     * @param appKey
     * @return
     */
    @RequestMapping(value = "/show",method = RequestMethod.POST)
    public ModelAndView show(String appKey){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("result",bs.query(appKey));
                return  modelAndView;

    }


    /**
     * 通过用户名查询是否存在于黑名单
     * @param appKey
     * @param username
     * @return
     */
    @RequestMapping(value = "/showByUsername",method = RequestMethod.POST)
    public ModelAndView showByUsername(String appKey,String username){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("result",bs.queryByUsername(appKey,username));
        return  modelAndView;

    }

    /**
     * 通过IP查询是否存在于黑名单
     * @param appKey
     * @param ip
     * @return
     */
    @RequestMapping(value = "/showByIp",method = RequestMethod.POST)
    public ModelAndView showByIp(String appKey,String ip){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("result",bs.query(appKey));
        return  modelAndView;

    }

    /**
     * 重置黑名单的一条记录
     * @param appKey
     * @param username
     * @param ip
     * @return
     */
    @RequestMapping(value = "/reset" , method = RequestMethod.POST)
    public String reset(String appKey,String username,String ip){
        bs.reset(appKey,username,ip);
        return null;
    }

}
