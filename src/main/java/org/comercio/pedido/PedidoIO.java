package org.comercio.pedido;

import java.util.Collection;

interface PedidoIO {

	IdentificadorPedido reservaPedido(final Collection<Integer> collection);

	void pago(Integer obterIdentificadorPedido);

}
