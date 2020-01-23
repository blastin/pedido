package org.comercio.pedido;

import org.comercio.Comando;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComandoPedidoPago implements Comando {

	private PedidoPago pedidoPago;

	private boolean comandoAcessado;

	@Override
	public <T> Comando inserirObjeto(T t) {
		pedidoPago = (PedidoPago) t;
		return this;
	}

	@Override
	public void manipular() {
		log.info("Comando para Pedido com identificador [cliente = {}, pedido = {} ], recebido",
				pedidoPago.obterIdentificadorCliente(), pedidoPago.obterIdentificadorPedido());
		comandoAcessado = true;
	}

	public boolean acessado() {
		return comandoAcessado;
	}

}
