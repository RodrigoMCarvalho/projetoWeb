package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.ItemVenda;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda> {

	public void salvar(Venda venda, List<ItemVenda> listItensVenda) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(venda);

			// para uma venda será salvo N itens
			for (int posicao = 0; posicao < listItensVenda.size(); posicao++) {
				ItemVenda itemVenda = listItensVenda.get(posicao); // obtém o item da linha corrente do for
				itemVenda.setVenda(venda); // necessário realizar esse procedimento por causa que o código(PK) vem como
											// null após o salvamento o hibernate seta o código correto
				sessao.save(itemVenda);

				Produto produto = itemVenda.getProduto();
				int quantidade = produto.getQuantidade() - itemVenda.getQuantidade();
				produto.setQuantidade((short) quantidade);
				sessao.update(produto); // atualiza o número de itens no estoque

			}
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
