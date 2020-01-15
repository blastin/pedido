package org.comercio.pedido;

import java.util.Collection;

interface PedidoIO {

	Situacao reservaPedido(final Collection<Integer> collection);

	Collection<Produto> produtos(NovoPedido novoPedido);
	
}
