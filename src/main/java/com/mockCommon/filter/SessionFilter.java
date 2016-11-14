package com.mockCommon.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mockCommon.util.OpenId;

public class SessionFilter extends OncePerRequestFilter {
	
	@Autowired
	private OpenId openId;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		
		/**
		 * 需要使用OpenId时将下面的注释去掉，并且修改OpenId.java中相应的配置
		 */
//		String url = request.getRequestURL().toString();
//		String queryString = request.getQueryString();
//		
//		if(request.getSession().getAttribute("username") == null){
//			if(queryString != null && queryString.contains("openid.assoc_handle")){
//				if(openId.check_authentication(queryString)){
//					request.getSession().setAttribute("username", request.getParameter("openid.sreg.email"));
//				}else{
//					response.sendRedirect(openId.generateAuthUrl());
//				}
//			}else{
//				response.sendRedirect(openId.generateAuthUrl());
//			}
//		}
		
		chain.doFilter(request, response);
		
	}
	
}
