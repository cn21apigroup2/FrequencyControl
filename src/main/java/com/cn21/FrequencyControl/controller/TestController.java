/**
 *  @Title: Test.java 
 *  @Package com.cn21.FrequencyControl 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月8日 下午5:21:52 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cn21.FrequencyControl.module.Example;
import com.cn21.FrequencyControl.service.ExampleService;

/**
 * @author chenxiaofeng
 * @date 2016年8月8日
 */
@Controller
@RequestMapping("/")
public class TestController {
	@Autowired
	private ExampleService exampleService;

	/**
	 * 欢迎页面
	 * @return void
	 */
	@RequestMapping(value = "")
	public ModelAndView welcome() {
		Example example = exampleService.showExample("陈小锋");//获取例子
		ModelAndView modelAndView = new ModelAndView("/welcome");
		modelAndView.addObject("example", example);
		return modelAndView;
	}

}
