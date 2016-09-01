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
import com.alibaba.fastjson.JSONObject;
import com.cn21.FrequencyControl.module.Application;
import com.cn21.FrequencyControl.module.InterfaceControl;
import com.cn21.FrequencyControl.service.ApplicationService;
import com.cn21.FrequencyControl.service.InterfacService;
import com.cn21.FrequencyControl.socket.ServerThread;

/**
 * @author zhangqingxiang
 * @date 2016年8月22日
 */
@Controller
@RequestMapping("/interface")
public class InterfacController {
	@Autowired
	private InterfacService interfacService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private ServerThread serverThread;
    
	/**
	 * 获取app interface列表
	 * @return void
	 */
	@RequestMapping(value = "/list/{appId}")
	@ResponseBody
	public String showAllInterfac(@PathVariable long appId) {
		List<InterfaceControl> interfacs = interfacService.getInterfacListByAppId(appId);//获取例子
		InterfaceControl overallControl = interfacService.getOverAllControl(interfacs);
		JSONArray resultJson = new JSONArray();
		resultJson.add(overallControl);
		resultJson.add(interfacs);
		return resultJson.toString();
	}
	/**
	 * 保存interface
	 * @return void
	 */
	@RequestMapping(value = "/save/{appId}")
	@ResponseBody
	public String saveUserApps(@PathVariable long appId,
			HttpServletRequest request, HttpServletResponse respons) {
		InterfaceControl interfac=interfacService.generateInterfac(request, appId);//根据用户提交表单生成app
		interfacService.createInterfac(interfac);//持久化到数据库
		//通知客户端更新
		Application application = applicationService.getApplicationByAppId(appId);
		serverThread.notifyPullApiLimited(application.getApp_key());
		return null;
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
		InterfaceControl interfac = interfacService.getInterfacByInterId(interId);//获取例子
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
	@RequestMapping(value="/modifySave/{appId}/{interfaceId}")
	@ResponseBody
	public String saveModifyInterfac(@PathVariable long appId,@PathVariable long interfaceId,
			HttpServletRequest request,HttpServletResponse response ) {
		InterfaceControl interfac = interfacService.getInterfacByInterId(interfaceId);//获取例子
		String apiFrequency = request.getParameter("apiFrequency");
		String timeout = request.getParameter("timeout");
		interfac.setFrequency(Integer.parseInt(apiFrequency));
		interfac.setTimeout(Integer.parseInt(timeout));
		interfac.setApi_name(request.getParameter("apiName"));
		interfac.setUnit(request.getParameter("unit").charAt(0));
		interfacService.modifyInterfac(interfac);
		//通知客户端更新
		Application application = applicationService.getApplicationByAppId(appId);
		serverThread.notifyPullApiLimited(application.getApp_key());
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(interfac);
		return jsonArray.toString();
	}
	/**
	 * 删除interface
	 * @return void
	 */
	@RequestMapping(value = "/delete/{appId}/{interfaceId}")
	@ResponseBody
	public String deleteInterfac(@PathVariable long appId,@PathVariable long interfaceId) {
		interfacService.deleteInterfac(interfaceId);
		//通知客户端更新
		Application application = applicationService.getApplicationByAppId(appId);
		serverThread.notifyPullApiLimited(application.getApp_key());
		return null;
	} 
	/**
	 * 列出已删除的interface
	 * @return void
	 */
	@RequestMapping(value = "/listDel/{userId}/{appId}")
	public ModelAndView showAlldeletedInterfacs(@PathVariable long userId,@PathVariable long appId) {
		List<InterfaceControl> interfacs = interfacService.getDeletedInterfacListByAppId(appId);//获取例子
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
		//通知客户端更新
		Application application = applicationService.getApplicationByAppId(appId);
		serverThread.notifyPullApiLimited(application.getApp_key());
		ModelAndView modelAndView = new ModelAndView("/interface/interfaceSave");
		modelAndView.addObject("appId",appId);
		modelAndView.addObject("userId",userId);
		return modelAndView;
	}
	
	//---------------------------------------------------------------------	

		/**
		 * 客户端jar包拉取数据接口
		 * 
		 * @param appKey
		 * @return
		 */
		@RequestMapping("/pull/{appKey}")
		@ResponseBody
		public String pull(HttpServletRequest request, @PathVariable String appKey) {
			Application application = applicationService.getApplicationByAppKey(appKey);
			List<InterfaceControl> interfaces = interfacService.getInterfacListByAppId(application.getApp_id());
			InterfaceControl overAllControl = interfacService.getOverAllControl(interfaces);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("overallControl", overAllControl);
			JSONArray jSonArray = new JSONArray();
			jSonArray.addAll(interfaces);
			jsonObject.put("interfaces", jSonArray);
			return jsonObject.toJSONString();
		}
		
}
