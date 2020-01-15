package org.comercio.pedido;

import java.util.Collection;
import java.util.HashSet;

class RepositorioCache implements PedidoIO {

	private Boolean statusAcesso;

	@Override
	public Situacao reservaPedido(Collection<Integer> collection) {
		return Situacao.INDISPONIVEL;
	}

	@Override
	public Collection<Produto> produtos(NovoPedido novoPedido) {

		statusAcesso = Boolean.TRUE;

		Collection<Produto> produtos = new HashSet<>();

		novoPedido.getProdutos()
				.forEach(produto -> produtos.add(new Produto(produto.getCodigo(), produto.getCusto(), 30, 2.2f)));

		return produtos;

	}

	Boolean repositorioAcessado() {
		return statusAcesso.equals(Boolean.TRUE);
	}

}
