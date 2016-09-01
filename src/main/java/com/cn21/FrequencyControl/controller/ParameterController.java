package com.cn21.FrequencyControl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.cn21.FrequencyControl.module.Application;
import com.cn21.FrequencyControl.module.Parameter;
import com.cn21.FrequencyControl.service.ApplicationService;
import com.cn21.FrequencyControl.service.ParameterService;
import com.cn21.FrequencyControl.socket.ServerThread;

/**
 * @author zhangqingxiang
 * @date 2016年8月22日
 */
@Controller
@RequestMapping("/parameter")
public class ParameterController {
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private ServerThread serverThread;
	
	/**
	 * 获取参数列表
	 * @return void
	 */
	@RequestMapping(value = "/list/{interId}")
	@ResponseBody
	public String showAllParameter(@PathVariable long interId) {
		List<Parameter> parameters = parameterService.getParameterListByInterId(interId);//获取例子
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(parameters);
		System.out.print(jsonArray.toString());
		return jsonArray.toString();
	}
	
	/**
	 * 保存参数
	 * @return void
	 */
	@RequestMapping(value = "/save/{appId}/{interfaceId}")
	@ResponseBody
	public String saveParameter(@PathVariable long appId,@PathVariable long interfaceId,
			HttpServletRequest request, HttpServletResponse respons) {
		Parameter parameter=parameterService.generateParameter(request, interfaceId);//根据用户提交表单生成app
		parameterService.createParameter(parameter);//持久化到数据库
		// 通知客户端更新
		Application application = applicationService.getApplicationByAppId(appId);
		serverThread.notifyPullApiLimited(application.getApp_key());
		return Long.toString(interfaceId);
	}
	/**
	 * 添加参数
	 * @return void
	 */
	@RequestMapping(value = "/create/{userId}/{appId}/{interId}")
	public ModelAndView createParameters(@PathVariable long userId,@PathVariable long appId,@PathVariable long interId) {
		ModelAndView modelAndView = new ModelAndView("/parameter/createParameter");
		modelAndView.addObject("interId", interId);
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("appId", appId);
		return modelAndView;
	}

	/**
	 * 修改参数
	 * @return void
	 */
	@RequestMapping(value = "/modify/{userId}/{appId}/{interId}/{paraId}")
	public ModelAndView modifyParameter(@PathVariable long userId,@PathVariable long appId,@PathVariable long interId,@PathVariable long paraId) {
		Parameter parameter = parameterService.getParameterByParaId(paraId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/parameter/modifyParameter");
		modelAndView.addObject("parameter", parameter);
		modelAndView.addObject("interId", interId);
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("appId", appId);
		return modelAndView;
	}
	/**
	 * 保存修改后的参数
	 * @return void
	 */
	@RequestMapping(value="/modifySave/{appId}/{paraId}")
	@ResponseBody
	public String saveModifyParameter(@PathVariable long appId,@PathVariable long paraId,
			HttpServletRequest request,HttpServletResponse response ) {
		Parameter parameter = parameterService.getParameterByParaId(paraId);//获取例子		
		parameter.setParameter_key(request.getParameter("parameterKey"));
		parameter.setParameter_value(request.getParameter("parameterValue"));
		parameterService.modifyParameter(parameter);
		// 通知客户端更新
		Application application = applicationService.getApplicationByAppId(appId);
		serverThread.notifyPullApiLimited(application.getApp_key());
		return null;
	}
	/**
	 * 删除参数
	 * @return void
	 */
	@RequestMapping(value = "/delete/{appId}/{paraId}")
	@ResponseBody
	public String deleteParameter(@PathVariable long appId,@PathVariable long paraId) {
		parameterService.deleteParameter(paraId);
		// 通知客户端更新
		Application application = applicationService.getApplicationByAppId(appId);
		serverThread.notifyPullApiLimited(application.getApp_key());
		return null;
	} 
}
