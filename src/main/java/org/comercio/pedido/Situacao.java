package org.comercio.pedido;

import java.util.Arrays;

enum Situacao {

	AGUARDANDO_PAGAMENTO(0), PAGO(1), DESPACHADO(2), CANCELADO(3), INDISPONIVEL(4), DISPONIVEL(5);

	private final Integer codigo;

	private Situacao(Integer codigo) {
		this.codigo = codigo;
	}

	static Situacao obterPorCondigo(final Integer codigo) {
		return Arrays.stream(Situacao.values()).filter(situacao -> situacao.codigo.equals(codigo)).findFirst()
				.orElseThrow(() -> new RuntimeException("Situacao não encontrada"));
	}
}
