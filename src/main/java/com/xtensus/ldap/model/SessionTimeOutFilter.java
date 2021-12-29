package com.xtensus.ldap.model;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;


//Si le délai d’expiration de la session se produit, cette classe sera redirigée 
//vers la page  de connexion « login.jsf
public class SessionTimeOutFilter implements Filter {
	
	private String timeoutPage = "login.jsf";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
				
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException{
		
		if ((request instanceof HttpServletRequest)
			&& (response instanceof HttpServletResponse)) {
			
				HttpServletRequest httpServletRequest = (HttpServletRequest) request;
				HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			if (isSessionControlRequiredForThisResource(httpServletRequest)) {
				
				if (isSessionInvalid(httpServletRequest)) {
					
						String timeoutUrl = httpServletRequest.getContextPath() + "/" + getTimeoutPage();
						
				System.out.println("Session is invalid, redirecting to timeout page::" + timeoutUrl);
				
				httpServletResponse.sendRedirect(timeoutUrl);
					return;
					}
				}
			}
		
		filterChain.doFilter(request, response);	
	}

	private boolean isSessionControlRequiredForThisResource(
		HttpServletRequest httpServletRequest) {
		
		String requestPath = httpServletRequest.getRequestURI();
		boolean controlRequired = !StringUtils.contains(requestPath,
		getTimeoutPage());
		
		return controlRequired;
	}

	private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
		
		boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null)
		&& !httpServletRequest.isRequestedSessionIdValid();
		
			return sessionInValid;
	}
	
	public String getTimeoutPage() {
		return timeoutPage;
	}

	public void setTimeoutPage(String timeoutPage) {
			this.timeoutPage = timeoutPage;
	}
		
	@Override
	public void destroy() {
	}
	
}
