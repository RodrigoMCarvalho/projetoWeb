package br.com.drogaria.util;

import org.hibernate.Session;
import org.junit.Test;

import br.com.drogaria.util.HibernateUtil;

public class HibernateUtilTest {
	
	@Test
	public void conectar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		sessao.close();
		HibernateUtil.getSessionFactory().close();
	}

}
