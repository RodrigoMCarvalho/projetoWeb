package br.com.drogaria.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.drogaria.domain.Usuario;
import br.com.drogaria.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p"); //criando um alias para pessoa (p)
			
			consulta.add(Restrictions.eq("p.cpf", cpf)); //cpf está dentro da entidade pessoa
			
			SimpleHash hash = new SimpleHash("md5", senha);  //configura a criptografia
			consulta.add(Restrictions.eq("senha", hash.toHex())); 
			
			Usuario resultado = (Usuario)consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
