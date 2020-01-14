package org.comercio.pedido;

import org.comercio.Comando;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ComandoIndisponivel implements Comando {

	private NovoPedido novoPedido;

	@Override
	public void manipular() {
		log.info("Manipulando indisponibilidade");
		log.info("Recebi novoPedido = {}", novoPedido.getCodigoCliente());
	}

	@Override
	public <T> Comando inserirObjeto(final T t) {
		novoPedido = (NovoPedido) t;
		return this;
	}

}
