/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cn21.FrequencyControl.module.Application;
import com.cn21.FrequencyControl.service.ApplicationService;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
@Controller
@RequestMapping("/app")
public class AppController {
	@Autowired
	private ApplicationService applicationService;

	/**
	 * 获取用户app列表
	 * @return void
	 */
	@RequestMapping(value = "/list/{userId}")
	public ModelAndView showAllUserApps(@PathVariable Long userId) {
		List<Application> applications = applicationService.getApplicationListByUserId(userId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/app/appList");
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("applications", applications);
		return modelAndView;
	}
	/**
	 * 创建app
	 * @return void
	 */
	@RequestMapping(value = "/save/{userId}")
	public ModelAndView saveUserApps(@PathVariable Long userId,
			HttpServletRequest request, HttpServletResponse respons) {
		Application application=applicationService.generateApp(request);
		List<Application> applications = applicationService.getApplicationListByUserId(userId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/createApp");
		modelAndView.addObject("applications", applications);
		return modelAndView;
	}
	
	/**
	 * 创建app
	 * @return void
	 */
	@RequestMapping(value = "/create/{userId}")
	public ModelAndView createUserApps(@PathVariable Long userId) {
		ModelAndView modelAndView = new ModelAndView("/app/createApp");
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}
	
	/**
	 * 修改app
	 * @return void
	 */
	@RequestMapping(value = "/modify/{userId}/{appId}")
	public ModelAndView createUserApps(@PathVariable Long userId,@PathVariable Long appId) {
		List<Application> applications = applicationService.getApplicationListByUserId(userId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/modifyApp");
		modelAndView.addObject("applications", applications);
		return modelAndView;
	}
	
	/**
	 * 删除app
	 * @return void
	 */
	@RequestMapping(value = "/delete/{userId}/{appId}")
	public ModelAndView deleteUserApps(@PathVariable Long userId,@PathVariable Long appId) {
		List<Application> applications = applicationService.getApplicationListByUserId(userId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/deleteApp");
		modelAndView.addObject("applications", applications);
		return modelAndView;
	}
	
	/**
	 * 列出已删除的app
	 * @return void
	 */
	@RequestMapping(value = "/listDel/{userId}")
	public ModelAndView showAlldeletedApps(@PathVariable Long userId,@PathVariable Long appId) {
		List<Application> applications = applicationService.getApplicationListByUserId(userId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/deleteApps");
		modelAndView.addObject("applications", applications);
		return modelAndView;
	}
	
}
