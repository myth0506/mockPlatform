package com.mockCommon.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mockCommon.constant.ThreadLocalConstant;
import com.mockCommon.util.ErrorMessageUtil;
import com.mockCommon.util.RequestUtil;

/**
 * 在本地threadLocal中保存request
 */
public class ThreadLocalInterceptor implements HandlerInterceptor {
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

		ErrorMessageUtil.clear();
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {

		List<String> emList = ErrorMessageUtil.get();
		if (emList != null) {
			RequestUtil.setAttribute(ThreadLocalConstant.ERROR_MESSAGE_REQUEST_KEY, emList);
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//根据实际需求修改cdnBaseUrl的地址
		request.setAttribute("cdnBaseUrl", "http://baoxian.qa.ms.netease.com");
		request.setAttribute("cdnFileVersion", "20150417");
		
		ThreadLocalConstant.requestTL.set(request);
		ThreadLocalConstant.responseTL.set(response);
		ThreadLocalConstant.sessionTL.set(request.getSession());
		
		return true;
	}
}
