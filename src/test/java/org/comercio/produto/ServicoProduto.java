package org.comercio.produto;

import java.util.Collection;
import java.util.HashSet;

public class ServicoProduto implements ProdutosGateway {

	@Override
	public Collection<Produto> produtos(final Collection<ProdutoCarrinho> produtoCarrinhos) {

		final Collection<Produto> produtos = new HashSet<>();

		produtoCarrinhos.forEach(produtoCarrinho -> produtos
				.add(new NegocioProduto(produtoCarrinho.getCodigo(), produtoCarrinho.getCusto(), 25, 0.4F, 77)));

		return produtos;

	}

}
