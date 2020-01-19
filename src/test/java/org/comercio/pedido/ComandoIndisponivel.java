package org.comercio.pedido;

import org.comercio.Comando;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ComandoIndisponivel implements Comando {

	private NovoPedido novoPedido;

	private Boolean visita;

	@Override
	public void manipular() {
		log.info("Pedido Indisponível = {}", novoPedido);
		visita = Boolean.TRUE;
	}

	@Override
	public <T> Comando inserirObjeto(final T t) {
		novoPedido = (NovoPedido) t;
		return this;
	}

	Boolean visitado() {
		return visita;
	}

}
