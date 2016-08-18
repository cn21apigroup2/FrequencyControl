package com.cn21.FrequencyControl.controller;

import java.util.HashMap;  
import java.util.Map;  

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.mvc.Controller;    

import com.cn21.FrequencyControl.service.impl.UserServiceImpl;  
import com.cn21.FrequencyControl.module.User;  
@RequestMapping("/register")
public class RigisterController implements Controller{
	private UserServiceImpl userServiceImpl;
	public ModelAndView handleRequest(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {
		String name=request.getParameter("userName");  
        String password=request.getParameter("password");  
        System.out.println("name--"+name+"--password--"+password);  
        User user=new User();  
        user.setName(name);  
        user.setPassword(password);  
        Map<String, Object> model=new HashMap<String, Object>();
        int m=userServiceImpl.insertUser(user);
        if(m==1){
        	user=userServiceImpl.selectUser(user);  
            System.out.println("注册成功");  
            model.put("user", user);
        	return new ModelAndView("Main.jsp",model); 
        }else{
        	System.out.println("注册失败");  
            model.put("error", "异常错误，请重新注册");  
            return new ModelAndView("Rigister.jsp",model);
        }
		
	}
	public void setUserServiceImpl(UserServiceImpl userService) {  
        this.userServiceImpl = userService;  
    }  
    public UserServiceImpl getUserServiceImpl() {  
        return userServiceImpl;  
    } 
}
