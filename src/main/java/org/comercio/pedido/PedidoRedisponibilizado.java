package org.comercio.pedido;

import lombok.ToString;

@ToString
public class PedidoRedisponibilizado {

	private final Integer identificadorPedido;

	private final Integer identificadorCliente;

	public PedidoRedisponibilizado(Integer identificadorPedido, Integer identificadorCliente) {
		this.identificadorPedido = identificadorPedido;
		this.identificadorCliente = identificadorCliente;
	}

	public Integer getIdentificadorPedido() {
		return identificadorPedido;
	}

	public Integer getIdentificadorCliente() {
		return identificadorCliente;
	}

}
