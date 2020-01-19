package org.comercio.produto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

public class ServicoProdutoRetornoDiferente implements ProdutosGateway {

	@Override
	public Collection<Produto> produtos(Collection<ProdutoCarrinho> produtoCarrinhos) {
		return Collections.singleton(new NegocioProduto(1, BigDecimal.valueOf(123), 25, 0.4F, 77));
	}

}
