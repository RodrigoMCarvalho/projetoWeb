package br.com.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//classe que implementa os métodos da interface ServletContextListener
public class HibernateContext implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.getSessionFactory().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		HibernateUtil.getSessionFactory().openSession();
		
	}

}
