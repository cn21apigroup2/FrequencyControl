package com.cn21.FrequencyControl.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {

	//不用拦截的url请求
	private List<String> unCheckUrls;
	
	/**
	 * @return the unCheckUrls
	 */
	public List<String> getUnCheckUrls() {
		return unCheckUrls;
	}

	/**
	 * @param unCheckUrls the unCheckUrls to set
	 */
	public void setUnCheckUrls(List<String> unCheckUrls) {
		this.unCheckUrls = unCheckUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String requestUrl = request.getRequestURI(); 
		if(unCheckUrls.contains(requestUrl))
		{
			return true;
		}
		else
		{	
			//从session里面获取用户名的信息，判断如果没有取到用户信息，就跳转到登录页面，提示用户登录
			if(request.getSession().getAttribute("username")!=null)
			{
				return true;
			}
			else
			{
				response.sendRedirect("/login");
				return false;
			}
		}
		
		
		
		
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
