package com.xtensus.beans.mbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;


import com.xtensus.jsf.common.LanguageManagerBean;
import com.xtensus.jsf.common.VariableGlobale;

import com.xtensus.ldap.model.Person;
import com.xtensus.ldap.utils.CustomUserDetails;
import com.xtensus.spring.service.impl.ApplicationManager;

@Component
@Scope("session")
public class LoginBean {	
	@Autowired
	public LoginBean(@Qualifier("applicationManager") ApplicationManager appMgr) {}
	
	public String check() throws IOException {
		
		boolean findPerson = false;
		System.out.println("Dans check");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
		try {
			dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			int id;
			
			if (principal instanceof CustomUserDetails) {
				
						if (((CustomUserDetails) principal).getAuthorities().isEmpty()) {
							ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
							externalContext.redirect(externalContext.getRequestContextPath()+ "/Ilogin/notAutorizedToConnect.xhtml");
						} else { id = ((CustomUserDetails) principal).getId();
							System.out.println(">>>>>>>"+id);
						}
					}
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "accepted";
			}
}