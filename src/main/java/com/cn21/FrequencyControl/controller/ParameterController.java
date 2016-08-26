package com.cn21.FrequencyControl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cn21.FrequencyControl.module.Parameter;
import com.cn21.FrequencyControl.service.ParameterService;

/**
 * @author zhangqingxiang
 * @date 2016年8月22日
 */
@Controller
@RequestMapping("/parameter")
public class ParameterController {
	@Autowired
	private ParameterService parameterService;
	
	/**
	 * 获取参数列表
	 * @return void
	 */
	@RequestMapping(value = "/list/{userId}/{interId}")
	public ModelAndView showAllParameter(@PathVariable long userId,@PathVariable long interId) {
		List<Parameter> parameters = parameterService.getParameterListByInterId(interId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/parameter/parameterList");
		modelAndView.addObject("interId", interId);
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("parameters", parameters);
		return modelAndView;
	}
	
	/**
	 * 保存参数
	 * @return void
	 */
	@RequestMapping(value = "/save/{userId}/{interfaceId}")
	public ModelAndView saveParameter(@PathVariable long interfaceId,@PathVariable long userId,
			HttpServletRequest request, HttpServletResponse respons) {
		Parameter parameter=parameterService.generateParameter(request, interfaceId);//根据用户提交表单生成app
		parameterService.createParameter(parameter);//持久化到数据库
		ModelAndView modelAndView = new ModelAndView("/parameter/parameterSave");
		modelAndView.addObject("userId", userId);	
		modelAndView.addObject("interfaceId", interfaceId);		
		return modelAndView;
	}
	/**
	 * 添加参数
	 * @return void
	 */
	@RequestMapping(value = "/create/{userId}/{interId}")
	public ModelAndView createParameters(@PathVariable long userId,@PathVariable long interId) {
		ModelAndView modelAndView = new ModelAndView("/parameter/createParameter");
		modelAndView.addObject("interId", interId);
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}

	/**
	 * 修改参数
	 * @return void
	 */
	@RequestMapping(value = "/modify/{userId}/{interId}/{paraId}")
	public ModelAndView modifyParameter(@PathVariable long userId,@PathVariable long interId,@PathVariable long paraId) {
		Parameter parameter = parameterService.getParameterByParaId(paraId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/parameter/modifyParameter");
		modelAndView.addObject("parameter", parameter);
		modelAndView.addObject("interId", interId);
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}
	/**
	 * 保存修改后的参数
	 * @return void
	 */
	@RequestMapping(value="/modifySave/{userId}/{interId}/{paraId}")
	public ModelAndView saveModifyParameter(@PathVariable long userId,@PathVariable long interId,@PathVariable long paraId,
			HttpServletRequest request,HttpServletResponse response ) {
		Parameter parameter = parameterService.getParameterByParaId(paraId);//获取例子		
		parameter.setParameter_key(request.getParameter("parameterKey"));
		parameter.setParameter_value(request.getParameter("parameterValue"));
		parameterService.modifyParameter(parameter);
		
		ModelAndView modelAndView = new ModelAndView("/parameter/parameterSave");
		modelAndView.addObject("interfaceId", interId);	
		modelAndView.addObject("userId", userId);	
		return modelAndView;
	}
	/**
	 * 删除参数
	 * @return void
	 */
	@RequestMapping(value = "/delete/{userId}/{interId}/{paraId}")
	public ModelAndView deleteParameter(@PathVariable long userId,@PathVariable long interId,@PathVariable long paraId) {
		parameterService.deleteParameter(paraId);
		List<Parameter> parameters = parameterService.getParameterListByInterId(interId);
		ModelAndView modelAndView = new ModelAndView("/parameter/parameterList");
		modelAndView.addObject("parameters", parameters);
		modelAndView.addObject("interId", interId);		
		modelAndView.addObject("userId", userId);	
		return modelAndView;
	} 
}
