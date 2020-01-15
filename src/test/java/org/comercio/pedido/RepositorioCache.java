package org.comercio.pedido;

import java.util.Collection;
import java.util.HashSet;

class RepositorioCache implements PedidoIO {

	private Boolean statusAcesso;

	private final Integer identificador;

	public RepositorioCache(Integer identificador) {
		this.identificador = identificador;
	}

	@Override
	public IdentificadorPedido reservaPedido(final Collection<Integer> collection) {
		return new IdentificadorPedido(identificador);
	}

	@Override
	public Collection<Produto> produtos(final NovoPedido novoPedido) {

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
