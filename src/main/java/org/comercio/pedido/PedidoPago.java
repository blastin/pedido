package org.comercio.pedido;

import org.comercio.EntradaInformacoesException;

public class PedidoPago {

	private final Integer codigoCliente;

	private final Integer codigoPedido;

	public PedidoPago(final Integer codigoPedido, final Integer codigoCliente) {
		if (codigoPedido == null || codigoPedido <= 0) {
			throw new EntradaInformacoesException(
					"Identificador de pedido não podem ser nulo ou com valor menor que 0");
		}
		this.codigoPedido = codigoPedido;
		if (codigoCliente == null || codigoCliente <= 0) {
			throw new EntradaInformacoesException(
					"Identificador de cliente não podem ser nulo ou com valor menor que 0");
		}
		this.codigoCliente = codigoCliente;
	}

	public Integer obterIdentificadorPedido() {
		return codigoPedido;
	}

	public Integer obterIdentificadorCliente() {
		return codigoCliente;
	}

}
