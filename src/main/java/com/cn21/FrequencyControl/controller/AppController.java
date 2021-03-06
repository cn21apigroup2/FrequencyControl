/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cn21.FrequencyControl.controller.common.Page;
import com.cn21.FrequencyControl.module.Application;
import com.cn21.FrequencyControl.service.ApplicationService;
import com.cn21.FrequencyControl.service.InterfacService;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
@Controller
@RequestMapping("/app")
public class AppController {
	private static final Logger logger=Logger.getLogger(AppController.class);
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private InterfacService interfacService;

	/**
	 * 获取用户app列表
	 * @return void
	 */
	@RequestMapping(value = "/list/{userId}")
	@ResponseBody
	public String showAllUserApps(@PathVariable Long userId) {
		List<Application> applications = applicationService.getApplicationListByUserId(userId);//获取用户应用列表
		logger.info("get Application List By UserId");
		JSONArray resultJson = new JSONArray();
		resultJson.add(applications);
		return resultJson.toString();
	}
	/**
	 * 按页获取获取用户app列表 
	 * @return void
	 */
	@RequestMapping(value = "/page/{userId}/{pageNo}/{pageSize}")
	public ModelAndView showUserAppsByPage(@PathVariable Long userId,@PathVariable int pageNo,@PathVariable int pageSize) {
		Page<Application> page = applicationService.getPageByUserId(userId, pageNo, pageSize);//获取用户应用列表
		ModelAndView modelAndView = new ModelAndView("/app/appListByPage");
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("page", page);
		return modelAndView;
	}
	/**
	 * 保存创建的app
	 * @return void
	 */
	@RequestMapping(value = "/save/{userId}")
	@ResponseBody
	public String saveUserApps(@PathVariable long userId,
			HttpServletRequest request, HttpServletResponse respons) {
		Application application=applicationService.generateApp(request,userId);//根据用户提交表单生成app
		applicationService.createApplication(application);//持久化到数据库
		application=applicationService.getApplicationByAppKey(application.getApp_key());
		interfacService.createOverallControl(application.getApp_id());//增加默认的全局频次控制
		return null;
	}
	
	/**
	 * 去创建app
	 * @return void
	 */
	@RequestMapping(value = "/create/{userId}")
	public ModelAndView createUserApps(@PathVariable long userId) {
		ModelAndView modelAndView = new ModelAndView("/app/createApp");
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}
	
	/**
	 * 修改app
	 * @return void
	 */
	@RequestMapping(value = "/modify/{userId}/{appId}")
	public ModelAndView modifyUserApps(@PathVariable long userId,@PathVariable long appId) {
		Application application = applicationService.getApplicationByAppId(appId);//
		ModelAndView modelAndView = new ModelAndView("/app/modifyApp");
		modelAndView.addObject("application", application);
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}
	/**
	 * 保存修改app
	 * @return void
	 */
	@RequestMapping(value = "/saveModify/{userId}/{appId}")
	@ResponseBody
	public String saveModifyApps(@PathVariable long userId,@PathVariable long appId,
			HttpServletRequest request, HttpServletResponse respons) {
		Application application=applicationService.getApplicationByAppId(appId);
		application.setApp_description(request.getParameter("appDescription"));
		application.setApp_name(request.getParameter("appName"));
		application.setPlatform(request.getParameter("appPlatform"));
		applicationService.modifyApplication(application);//保存修改
		Map<String,String> map = new HashMap<String,String>();
		map.put("appName",request.getParameter("appName"));
		map.put("appDescription",request.getParameter("appDescription"));
		map.put("appPlatform",request.getParameter("appPlatform"));
		JSONArray resultJson = new JSONArray();
		resultJson.add(map);
		return resultJson.toString();
	}
	/**
	 * 删除app
	 * @return void
	 */
	@RequestMapping(value = "/delete/{userId}/{appId}")
	public ModelAndView deleteUserApps(@PathVariable long userId,@PathVariable long appId) {
		applicationService.deleteApplication(appId);//删除应用
		ModelAndView modelAndView = new ModelAndView("/app/appSave");
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}
	
	/**
	 * 列出已删除的app
	 * @return void
	 */
	@RequestMapping(value = "/listDel/{userId}")
	public ModelAndView showAllDeletedApps(@PathVariable long userId) {
		List<Application> applications = applicationService.getDeletedApplicationListByUserId(userId);//获取已删除应用列表
		ModelAndView modelAndView = new ModelAndView("/app/appList");
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("applications", applications);
		modelAndView.addObject("deleted", 1);
		return modelAndView;
	}
	
	/**
	 * 恢复已删除的app
	 * @return void
	 */
	@RequestMapping(value = "/resume/{userId}/{appId}")
	public ModelAndView resumeDeletedApps(@PathVariable long userId,@PathVariable long appId) {
		applicationService.regainApplication(appId);//恢复已删除应用
		List<Application> applications = applicationService.getDeletedApplicationListByUserId(userId);//获取已删除应用列表
		ModelAndView modelAndView = new ModelAndView("/app/appList");
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("applications", applications);
		modelAndView.addObject("deleted", 1);
		return modelAndView;
	}
	
}
