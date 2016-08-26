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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn21.FrequencyControl.module.Interfac;
import com.cn21.FrequencyControl.service.InterfacService;
import com.google.gson.JsonObject;

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
	@RequestMapping(value = "/list/{userId}/{appId}")
	public ModelAndView showAllInterfac(@PathVariable long userId, @PathVariable long appId) {
		List<Interfac> interfacs = interfacService.getInterfacListByAppId(appId);//获取例子
		Interfac overallControl = interfacService.getOverAllControl(interfacs);
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceList");
		modelAndView.addObject("appId", appId);
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("interfacs", interfacs);
		modelAndView.addObject("overallControl", overallControl);
		return modelAndView;
	}
	/**
	 * 保存interface
	 * @return void
	 */
	@RequestMapping(value = "/save/{userId}/{appId}")
	public ModelAndView saveUserApps(@PathVariable long appId,@PathVariable long userId,
			HttpServletRequest request, HttpServletResponse respons) {
		Interfac interfac=interfacService.generateInterfac(request, appId);//根据用户提交表单生成app
		interfacService.createInterfac(interfac);//持久化到数据库
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceSave");
		modelAndView.addObject("appId", appId);	
		modelAndView.addObject("userId", userId);	
		return modelAndView;
	}
	/**
	 * 创建interface
	 * @return void
	 */
	@RequestMapping(value = "/create/{userId}/{appId}")
	public ModelAndView createInterfacs(@PathVariable long userId,@PathVariable long appId) {
		ModelAndView modelAndView = new ModelAndView("/interface/createInterface");
		modelAndView.addObject("appId", appId);
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}
	/**
	 * 修改interface
	 * @return void
	 */
	@RequestMapping(value = "/modify/{userId}/{appId}/{interId}")
	public ModelAndView modifyInterfac(@PathVariable long userId,@PathVariable long appId,@PathVariable long interId) {
		Interfac interfac = interfacService.getInterfacByInterId(interId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/interface/modifyInterface");
		modelAndView.addObject("interfac", interfac);
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("appId", appId);
		return modelAndView;
	}
	/**
	 * 保存修改后的interface
	 * @return void
	 */
	@RequestMapping(value="/modifySave/{userId}/{appId}/{interId}")
	public ModelAndView saveModifyInterfac(@PathVariable long userId,@PathVariable long appId,@PathVariable long interId,
			HttpServletRequest request,HttpServletResponse response ) {
		Interfac interfac = interfacService.getInterfacByInterId(interId);//获取例子		
		String apiFrequency = request.getParameter("apiFrequency");
		String timeout = request.getParameter("timeout");
		interfac.setFrequency(Integer.parseInt(apiFrequency));
		interfac.setTimeout(Integer.parseInt(timeout));
		interfac.setApi_name(request.getParameter("apiName"));
		interfac.setUnit(request.getParameter("unit").charAt(0));
		interfacService.modifyInterfac(interfac);	
		
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceSave");
		modelAndView.addObject("appId", appId);	
		modelAndView.addObject("userId", userId);	
		return modelAndView;
	}
	/**
	 * 删除interface
	 * @return void
	 */
	@RequestMapping(value = "/delete/{userId}/{appId}/{interId}")
	public ModelAndView deleteInterfac(@PathVariable long userId,@PathVariable long appId,@PathVariable long interId) {
		interfacService.deleteInterfac(interId);
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceSave");
		modelAndView.addObject("appId",appId);
		modelAndView.addObject("userId",userId);
		return modelAndView;
	} 
	/**
	 * 列出已删除的interface
	 * @return void
	 */
	@RequestMapping(value = "/listDel/{userId}/{appId}")
	public ModelAndView showAlldeletedInterfacs(@PathVariable long userId,@PathVariable long appId) {
		List<Interfac> interfacs = interfacService.getDeletedInterfacListByAppId(appId);//获取例子
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceList");
		modelAndView.addObject("interfacs", interfacs);
		modelAndView.addObject("appId",appId);
		modelAndView.addObject("userId",userId);
		modelAndView.addObject("deleted",1);
		return modelAndView;
	}
	/**
	 * 恢复删除的interface
	 * @return void
	 */
	@RequestMapping(value = "/resume/{userId}/{appId}/{interId}")
	public ModelAndView resumeDeletedInterfac(@PathVariable long userId,@PathVariable long appId,@PathVariable long interId){
		interfacService.regainInterfac(interId);
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceSave");
		modelAndView.addObject("appId",appId);
		modelAndView.addObject("userId",userId);
		return modelAndView;
	}
	
	//---------------------------------------------------------------------	

		/**
		 * 客户端jar包拉取数据接口
		 * 
		 * @param app_id
		 * @return
		 */
		@RequestMapping("/pull/{userId}/{appId}")
		@ResponseBody
		public String pull(HttpServletRequest request,@PathVariable long userId, @PathVariable long appId) {
			List<Interfac> interfaces = interfacService.getInterfacListByAppId(appId);
			Interfac overAllControl = interfacService.getOverAllControl(interfaces);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("overallControl", overAllControl);
			JSONArray jSonArray = new JSONArray();
			jSonArray.addAll(interfaces);
			jsonObject.put("interfaces", jSonArray);
			return jsonObject.toJSONString();
		}
		
}
