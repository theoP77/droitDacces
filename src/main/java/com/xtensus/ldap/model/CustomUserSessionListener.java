package com.xtensus.ldap.model;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


//Classe CustomUserSessionListener.java pour surveiller les sessions. 
public class CustomUserSessionListener implements HttpSessionListener {

	public CustomUserSessionListener() {}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
		System.out.println("Current Session created :" + event.getSession().getId() + " at " + new Date());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		HttpSession session = event.getSession();

		System.out.println("Current Session destroyed :" + session.getId() + " Logging out use");
	}	
}
