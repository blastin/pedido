package org.comercio.pedido;

import lombok.Getter;

public enum Situacao {

	AGUARDANDO_PAGAMENTO(0), PAGO(1), DESPACHADO(2), CANCELADO(3), INDISPONIVEL(4), DISPONIVEL(5), REDISPONIBILIZADO(6),
	SEM_REDISPONIBILIZACAO(7);

	@Getter
	private final Integer codigo;

	Situacao(final Integer codigo) {
		this.codigo = codigo;
	}

	boolean favoravelParaRedisponibilizacao() {
		return this.equals(AGUARDANDO_PAGAMENTO) || this.equals(CANCELADO);
	}
}
