package org.comercio.produto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

import org.comercio.produto.Produto;
import org.comercio.produto.ProdutosGateway;

public class ServicoProduto implements ProdutosGateway {

	@Override
	public Collection<Produto> produtos(Collection<Integer> novoPedido) {
		return Collections.singleton(new NegocioProduto(1, BigDecimal.valueOf(100), 25, 0.4F, 77));
	}

}
