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

@RequestMapping("/login")
public class LoginController implements Controller {  
    private UserServiceImpl userServiceImpl;  
    @RequestMapping("")
    public ModelAndView handleRequest(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
        // TODO Auto-generated method stub  
        String name=request.getParameter("userName");  
        String password=request.getParameter("password");  
        System.out.println("name-----"+name+"----password-----"+password);  
        User user=new User();  
        user.setName(name);  
        user.setPassword(password);  
        Map<String, Object> model=new HashMap<String, Object>();  
        if(userServiceImpl.selectUser(user)!=null){  
            System.out.println("能查到信息");  
            model.put("user", user);  
            return new ModelAndView("index.ftl",model);  
        }else{  
            System.out.println("查不到信息");  
            model.put("error", "用户名或密码错误");
            return new ModelAndView("welcome.ftl",model);  
        }  
          
    }  
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {  
        this.userServiceImpl = userServiceImpl;  
    }  
    public UserServiceImpl getUserServiceImpl() {  
        return userServiceImpl;  
    }  
  
  
}  
