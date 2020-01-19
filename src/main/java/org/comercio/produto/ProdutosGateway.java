package org.comercio.produto;

import java.util.Collection;

public interface ProdutosGateway {

	Collection<Produto> produtos(final Collection<ProdutoCarrinho> produtoCarrinhos);

}
