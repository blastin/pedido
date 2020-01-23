package org.comercio.pedido;

import org.comercio.MapaComando;
import org.comercio.produto.ProdutosGateway;

public interface PedidoFabrica {

	Pedidos construir(final PedidoIO pedidoIO, final ProdutosGateway produtosGateway,
			MapaComando<Situacao> mapaComando);

}
