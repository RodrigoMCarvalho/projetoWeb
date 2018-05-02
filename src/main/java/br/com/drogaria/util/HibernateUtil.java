package br.com.drogaria.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {   //A fábrica de sessões recebe o método de criação.
	private static SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	//método para converter uma Session para Connection, para ser usado no método imprimir
	//o Hibernate usa Session, enquanto o JDBC usa Connection
	public static Connection getConection() {
		Session sessao = sessionFactory.openSession(); //cria uma sessão
		
		//conexao = conn;
		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() { //sessão retorna para conexao
			@Override
			public Connection execute(Connection conn) throws SQLException {
				return conn;
			}
		});
		return conexao;
	}
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuracao = new Configuration().configure();
            
            ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
            
            SessionFactory factory = configuracao.buildSessionFactory(registro);
            return factory;
        }
        catch (Throwable ex) {
            System.err.println("A fábrica de sessão não pode ser criada: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
