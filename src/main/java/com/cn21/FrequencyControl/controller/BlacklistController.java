package com.cn21.FrequencyControl.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
		Blacklist blacklist = bs.query(appKey).get(0);
		resultJson.add(bs.query(appKey));
		System.out.print(resultJson.toString());
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
  		@RequestMapping("/pull")
  		@ResponseBody
  		public String pull(HttpServletRequest request) {
  			String appKey = request.getParameter("appKey");
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
  		@RequestMapping("/update")
  		@ResponseBody
  		public String update(HttpServletRequest request) {
  			String blist = request.getParameter("blacklists");
  			String appKey = request.getParameter("appKey");
  			List<Blacklist> blacklists=new ArrayList<Blacklist>();
  			JSONArray parseArray = JSONArray.parseArray(blist);
  			ObjectMapper objectMapper = new ObjectMapper();
  			for(int i=0;i<parseArray.size();i++){
  				try {
  					blacklists.add(objectMapper.readValue(parseArray.getString(i), Blacklist.class));
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
  			boolean success=true;
  			for (Blacklist blacklist : blacklists) {
  				Blacklist fromDb = bs.queryByUsername(blacklist.getAppKey(), blacklist.getCustomerId());
  				if(fromDb!=null) bs.update(blacklist);
  				else bs.add(blacklist);
			}
  			JSONObject result = new JSONObject();
  			result.put("success", success?1:0);
  			return result.toJSONString();
  		}

}
