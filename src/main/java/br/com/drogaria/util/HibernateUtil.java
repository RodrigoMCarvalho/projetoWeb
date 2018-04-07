package br.com.drogaria.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {   //A fábrica de sessões recebe o método de criação.
	private static SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}	
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuracao = new Configuration().configure();
            
            ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
            
            SessionFactory factory = configuracao.buildSessionFactory(registro);
            return factory;
        }
        catch (Throwable ex) {
            System.err.println("A fábrica de sessão não pode ser criada:" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
