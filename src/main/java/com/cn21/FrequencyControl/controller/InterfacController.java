package com.cn21.FrequencyControl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cn21.FrequencyControl.module.Interfac;
import com.cn21.FrequencyControl.service.InterfacService;

/**
 * @author zhangqingxiang
 * @date 2016年8月22日
 */
@Controller
@RequestMapping("/interface")
public class InterfacController {
	@Autowired
	private InterfacService interfacService;
    
	/**
	 * 获取app interface列表
	 * @return void
	 */
	@RequestMapping(value = "/list/{appId}")
	public ModelAndView showAllInterfac( @PathVariable long appId) {
		List<Interfac> interfacs = interfacService.getInterfacListByAppId(appId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceList");
		modelAndView.addObject("appId", appId);
		modelAndView.addObject("interfacs", interfacs);
		return modelAndView;
	}
	/**
	 * 保存interface
	 * @return void
	 */
	@RequestMapping(value = "/save/{appId}")
	public ModelAndView saveUserApps(@PathVariable long appId,
			HttpServletRequest request, HttpServletResponse respons) {
		Interfac interfac=interfacService.generateInterfac(request, appId);//根据用户提交表单生成app
		interfacService.createInterfac(interfac);//持久化到数据库
		List<Interfac> interfacs = interfacService.getInterfacListByAppId(appId);
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceList");
		modelAndView.addObject("interfacs", interfacs);
		modelAndView.addObject("appId", appId);		
		return modelAndView;
	}
	/**
	 * 创建interface
	 * @return void
	 */
	@RequestMapping(value = "/create/{appId}")
	public ModelAndView createInterfacs(@PathVariable long appId) {
		ModelAndView modelAndView = new ModelAndView("/interface/createInterface");
		modelAndView.addObject("appId", appId);
		return modelAndView;
	}
	/**
	 * 修改interface
	 * @return void
	 */
	@RequestMapping(value = "/modify/{appId}/{interId}")
	public ModelAndView modifyInterfac(@PathVariable long appId,@PathVariable long interId) {
		Interfac interfac = interfacService.getInterfacByInterId(interId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/interface/modifyInterface");
		modelAndView.addObject("interfac", interfac);
		modelAndView.addObject("appId", appId);
		return modelAndView;
	}
	/**
	 * 保存修改后的interface
	 * @return void
	 */
	@RequestMapping(value="/modifySave/{appId}/{interId}")
	public ModelAndView saveModifyInterfac(@PathVariable long appId,@PathVariable long interId,
			HttpServletRequest request,HttpServletResponse response ) {
		Interfac interfac = interfacService.getInterfacByInterId(interId);//获取例子		
		String apiFrequency = request.getParameter("apiFrequency");
		String timeout = request.getParameter("timeout");
		long  l = Long.valueOf(apiFrequency).longValue();
		long  a = Long.valueOf(timeout).longValue();				
		interfac.setFrequency(l);
		interfac.setTimeout(a);
		interfac.setApi_name(request.getParameter("apiName"));
		interfac.setUnit(request.getParameter("unit"));
		interfacService.modifyInterfac(interfac);	
		
		List<Interfac> interfacs = interfacService.getInterfacListByAppId(appId);
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceList");
		modelAndView.addObject("interfacs", interfacs);
		modelAndView.addObject("appId", appId);		
		return modelAndView;
	}
	/**
	 * 删除interface
	 * @return void
	 */
	@RequestMapping(value = "/delete/{appId}/{interId}")
	public ModelAndView deleteInterfac(@PathVariable long appId,@PathVariable long interId) {
		interfacService.deleteInterfac(interId);
		List<Interfac> interfacs = interfacService.getInterfacListByAppId(appId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceList");
		modelAndView.addObject("interfacs", interfacs);
		modelAndView.addObject("appId",appId);
		
		return modelAndView;
	} 
	/**
	 * 列出已删除的interface
	 * @return void
	 */
	@RequestMapping(value = "/listDel/{appId}")
	public ModelAndView showAlldeletedInterfacs(@PathVariable long appId) {
		List<Interfac> interfacs = interfacService.getDeletedInterfacListByAppId(appId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceList");
		modelAndView.addObject("interfacs", interfacs);
		modelAndView.addObject("appId",appId);
		modelAndView.addObject("deleted",1);
		return modelAndView;
	}
	/**
	 * 恢复删除的interface
	 * @return void
	 */
	@RequestMapping(value = "/resume/{appId}/{interId}")
	public ModelAndView resumeDeletedInterfac(@PathVariable long appId,@PathVariable long interId){
		interfacService.regainInterfac(interId);
		List<Interfac> interfacs=interfacService.getInterfacListByAppId(appId);
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceList");
		modelAndView.addObject("interfacs", interfacs);
		modelAndView.addObject("appId",appId);
		return modelAndView;
	}
}
