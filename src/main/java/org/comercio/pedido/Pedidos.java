package org.comercio.pedido;

public interface Pedidos {

	void novoPedido(final NovoPedido novoPedido);

	void redisponibilizarPedido(final PedidoRedisponibilizado pedidoRedisponibilizado);

	void pedidoPago(final PedidoPago pedidoPago);

	void cancelarPedido(final PedidoCancelado pedidoCancelado);

}
