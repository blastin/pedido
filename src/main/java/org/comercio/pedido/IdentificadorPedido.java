package org.comercio.pedido;

import java.util.Optional;

public class IdentificadorPedido {

	private final Integer identificador;

	public IdentificadorPedido(final Integer identificador) {
		this.identificador = identificador;
	}

	public boolean pedidoReservado() {
		return identificador != null;
	}

	public Optional<Integer> obterIdentificador() {
		return Optional.ofNullable(identificador);
	}

}
