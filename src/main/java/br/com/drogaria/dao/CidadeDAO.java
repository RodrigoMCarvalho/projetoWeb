package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.drogaria.domain.Cidade;
import br.com.drogaria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscaPorEstado(Long codigoEstado){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class); //criando um critério de cidade
			consulta.add(Restrictions.eq("estado.codigo", codigoEstado));
			//equivalente ao WHERE do SQL
			//procurar na Cidade o "estado.codigo" equivalente ao "codigoEstado"
			consulta.addOrder(Order.asc("nome")); //ordenar pelo nome da Cidade
			List<Cidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
