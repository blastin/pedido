package org.comercio.pedido;

import java.util.Collection;

interface PedidoIO {

	Situacao reservaPedido(final Pedido pedido);

	Collection<Produto> produtos(NovoPedido novoPedido);
	
}
