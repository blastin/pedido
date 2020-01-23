package org.comercio.pedido;

import java.util.Collection;

interface PedidoIO {

	IdentificadorPedido reservar(final Collection<Integer> identificadoresProduto);

	void pago(final Integer obterIdentificadorPedido);

	Situacao situacao(final IdentificadorPedido identificadorPedido);

	void redisponibilizar(final IdentificadorPedido identificadorPedido);

}
