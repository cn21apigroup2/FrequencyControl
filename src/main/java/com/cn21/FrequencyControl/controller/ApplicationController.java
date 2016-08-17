package com.cn21.FrequencyControl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cn21.FrequencyControl.module.Application;
import com.cn21.FrequencyControl.module.User;
import com.cn21.FrequencyControl.service.ApplicationService;
import com.cn21.FrequencyControl.service.UserService;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;
	@RequestMapping(value = "/listapplication")
	public ModelAndView findAllApplications(HttpServletRequest request) {
		List<Application> applications = new ArrayList<Application>();
		ModelAndView modelAndView = new ModelAndView();
		applications = applicationService.findAllApplication();
		modelAndView.setViewName("listuser");
		modelAndView.addObject("applications", applications);
		modelAndView.addObject("application", request.getSession()
				.getAttribute("application"));
		return modelAndView;
	}

	@RequestMapping(value = "/getApplication")
	public ModelAndView getApplication(int app_id) {
		Application application = new Application();
		ModelAndView modelAndView = new ModelAndView();
		application = applicationService.findById(app_id);
		modelAndView.setViewName("editapplication");
		modelAndView.addObject("application", application);
		return modelAndView;
	}

	@RequestMapping(value = "/delApplication")
	public ModelAndView delApplication(int app_id) {
		ModelAndView modelAndView = new ModelAndView();
		applicationService.deleteApplication(app_id);
		modelAndView.setViewName("redirect:/application/findallapplications");
		return modelAndView;
	}

	@RequestMapping(value = "/updateApplication")
	public ModelAndView updateApplication(Application application) {
		ModelAndView modelAndView = new ModelAndView();
		applicationService.updateApplication(application);
		modelAndView.setViewName("redirect:/application/findallapplications");
		return modelAndView;
	}

	@RequestMapping(value = "/toaddApplication")
	public ModelAndView toaddApplication() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addapplication");
		return modelAndView;
	}

	@RequestMapping(value = "/addApplication")
	public ModelAndView addApplication(Application application) {
		ModelAndView modelAndView = new ModelAndView();
		applicationService.insertApplication(application);
		modelAndView.setViewName("redirect:/application/findallapplications");
		return modelAndView;
	}

}
