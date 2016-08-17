package com.cn21.FrequencyControl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cn21.FrequencyControl.module.User;
import com.cn21.FrequencyControl.service.UserService;

import net.sf.json.JSONObject;

/**
 * @Author zhangqingxiang
 * @Date 创建时间：2015-12-10
 * @Version 1.0
 * 
 * @Project_Package_Description springmvc || com.demo.controller
 * @Function_Description 核心控制类，处理页面的请求以及业务
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	/*@RequestMapping(value = "/index")
	public ModelAndView index(User user) {
		userService.insertUser(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("info");
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(String name, String password,
			HttpServletRequest request) {
		User user = new User();
		ModelAndView modelAndView = new ModelAndView();
		user = userService.findByNameAndPassword(name, password);
		String msg = null;

		if (user != null) {
			msg = "登录成功";
			modelAndView.setViewName("redirect:/user/findallusers");
			request.getSession().setAttribute("user", user);
		} else {
			msg = "输入的用户名或密码不正确";
			modelAndView.setViewName("login");
			modelAndView.addObject("msg", msg);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/extlogin")
	public Object extlogin(String name, String password,
			HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String randCode = request.getParameter("randCode");
		User user = new User();
		ModelAndView modelAndView = new ModelAndView();
		user = userService.findByNameAndPassword(name, password);
		String msg = null;
		// 获得的当前正确的验证码
		String rand = (String) request.getSession().getAttribute("rand");
		if (rand.equals(randCode)) {
			if (user != null) {
				msg = "登录成功";
				modelAndView.setViewName("redirect:/user/findallusers");
				request.getSession().setAttribute("user", user);
				
			} else {
				msg = "输入的用户名或密码不正确";
				modelAndView.setViewName("login");
			}

		}
		map.put("msg", msg);
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject;
	}*/

	@RequestMapping(value = "/findallusers")
	public ModelAndView findAllUsers(HttpServletRequest request) {
		List<User> users = new ArrayList<User>();
		ModelAndView modelAndView = new ModelAndView();
		users = userService.findAllUser();
		modelAndView.setViewName("listuser");
		modelAndView.addObject("users", users);
		modelAndView.addObject("user", request.getSession()
				.getAttribute("user"));
		return modelAndView;
	}

	@RequestMapping(value = "/getUser")
	public ModelAndView getUser(int id) {
		User user = new User();
		ModelAndView modelAndView = new ModelAndView();
		user = userService.findById(id);
		modelAndView.setViewName("edituser");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/delUser")
	public ModelAndView delUser(int id) {
		ModelAndView modelAndView = new ModelAndView();
		userService.deleteUser(id);
		modelAndView.setViewName("redirect:/user/findallusers");
		return modelAndView;
	}

	@RequestMapping(value = "/updateUser")
	public ModelAndView updateUser(User user) {
		ModelAndView modelAndView = new ModelAndView();
		userService.updateUser(user);
		modelAndView.setViewName("redirect:/user/findallusers");
		return modelAndView;
	}

	@RequestMapping(value = "/toaddUser")
	public ModelAndView toaddUser() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adduser");
		return modelAndView;
	}

	@RequestMapping(value = "/addUser")
	public ModelAndView addUser(User user) {
		ModelAndView modelAndView = new ModelAndView();
		userService.insertUser(user);
		modelAndView.setViewName("redirect:/user/findallusers");
		return modelAndView;
	}

}