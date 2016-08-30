package com.cn21.FrequencyControl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn21.FrequencyControl.dto.BlacklistDto;
import com.cn21.FrequencyControl.module.Blacklist;
import com.cn21.FrequencyControl.service.BlacklistService;


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
    @RequestMapping(value = "/show",method = RequestMethod.GET)
	@ResponseBody
    public String show(String appKey){
		JSONArray resultJson = new JSONArray();
		resultJson.add(bs.query(appKey));
        return  resultJson.toString();

    }


    /**
     * 通过用户名查询是否存在于黑名单
     * @param appKey
     * @param username
     * @return
     */
    @RequestMapping(value = "/showByUsername",method = RequestMethod.GET)
	@ResponseBody
    public String showByUsername(String appKey,String username){
		JSONArray resultJson = new JSONArray();
		List<Blacklist> list = new ArrayList<Blacklist>();
		list.add(bs.queryByUsername(appKey,username));
		resultJson.add(list);
        return  resultJson.toString();

    }

    /**
     * 通过IP查询是否存在于黑名单
     * @param appKey
     * @param ip
     * @return
     */
    @RequestMapping(value = "/showByIp",method = RequestMethod.GET)
	@ResponseBody
    public String showByIp(String appKey,String ip){
		JSONArray resultJson = new JSONArray();
		List<Blacklist> list = new ArrayList<Blacklist>();
		list.add(bs.queryByIp(appKey,ip));
		resultJson.add(list);
        return  resultJson.toString();
    }

    /**
     * 重置黑名单的一条记录
     * @param appKey
     * @param username
     * @param ip
     * @return
     */
    @RequestMapping(value = "/reset" , method = RequestMethod.POST)
	@ResponseBody
    public String reset(String appKey,String username,String ip){
        bs.reset(appKey,username,ip);
        return null;
    }
    
  //---------------------------------------------------------------------	

  		/**
  		 * 客户端jar包拉取黑名单接口
  		 * 
  		 * @param appKey
  		 * @return
  		 */
  		@RequestMapping("/pull/{appKey}")
  		@ResponseBody
  		public String pull(HttpServletRequest request,@PathVariable String appKey) {
  			List<Blacklist> query = bs.query(appKey);
  			JSONArray result = new JSONArray();
  			result.addAll(query);
  			return result.toJSONString();
  		}
  		
  		/**
  		 * 客户端jar同步黑名单接口
  		 * 
  		 * @param appKey
  		 * @return
  		 */
  		@RequestMapping("/update/{appKey}/")
  		@ResponseBody
  		public String update(HttpServletRequest request,@PathVariable String appKey) {
  			String customerId = request.getParameter("customerId");
  			String limitedIp = request.getParameter("limitedIp");
  			String firDate = request.getParameter("firDate");
  			String secDate = request.getParameter("secDate");
  			String thrDate = request.getParameter("thrDate");
  			String times = request.getParameter("times");
  			String state = request.getParameter("state");
  			String absoluteDate = request.getParameter("absoluteDate");
  			Blacklist blackList = bs.queryByUsername(appKey, customerId);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
  			try {
  				if(absoluteDate!=null && !absoluteDate.equals(""))blackList.setAbsoulteDate(simpleDateFormat.parse(absoluteDate));
				if(firDate!=null && !firDate.equals(""))blackList.setFirDate(simpleDateFormat.parse(firDate));;
				if(secDate!=null && !secDate.equals(""))blackList.setSecDate(simpleDateFormat.parse(secDate));;
				if(thrDate!=null && !thrDate.equals(""))blackList.setThrDate(simpleDateFormat.parse(thrDate));;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			if(limitedIp!=null && !limitedIp.equals(""))blackList.setLimitedIp(limitedIp);
  			if(times!=null && !times.equals(""))blackList.setTimes(Short.parseShort(times));
  			if(state!=null && !state.equals(""))blackList.setState(Short.parseShort(state));
  			boolean success=bs.update(blackList);
  			JSONObject result = new JSONObject();
  			result.put("success", success?1:0);
  			return result.toJSONString();
  		}

}
