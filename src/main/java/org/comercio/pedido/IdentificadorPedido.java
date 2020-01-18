package org.comercio.pedido;

import lombok.Getter;

@Getter
public class IdentificadorPedido {

	private final Integer identificador;

	public IdentificadorPedido(final Integer identificador) {
		this.identificador = identificador;
	}

}
