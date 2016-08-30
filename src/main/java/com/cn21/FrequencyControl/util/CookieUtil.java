/**
 *  @Title: CookieUtil.java 
 *  @Package com.cn21.FrequencyControl.util 
 *  @Description: TODO(用一句话描述该文件做什么) 
 *  @author chenxiaofeng
 *  @date 2016年8月30日 上午9:04:00 
 *  @version V1.0 
 */
package com.cn21.FrequencyControl.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenxiaofeng
 * @date 2016年8月30日
 */
public class CookieUtil {
	public static final int MAX_AGE = -1;
	public static final String COOKIE_PATH = "/";

	public static void setCookie(HttpServletResponse response,String key,String value) {
		Cookie cookie = new Cookie(key.trim(),value.trim());
		cookie.setMaxAge(MAX_AGE);
		cookie.setPath(COOKIE_PATH);
		response.addCookie(cookie);
	}

	public static Cookie getCookie(HttpServletRequest request, String key) {
		if (key == null || key.trim().length() == 0)
			return null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key))
					return cookie;
			}
		}
		return null;
	}
	public static void deleteCookie(HttpServletResponse response,String key){
		Cookie cookie = new Cookie(key.trim(),null);
		cookie.setMaxAge(0);
		cookie.setPath(COOKIE_PATH);
		response.addCookie(cookie);
	}
}
