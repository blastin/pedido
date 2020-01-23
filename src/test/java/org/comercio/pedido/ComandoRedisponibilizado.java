package org.comercio.pedido;

import org.comercio.Comando;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ComandoRedisponibilizado implements Comando {

	private PedidoRedisponibilizado pedidoRedisponibilizado;

	private boolean acessado;

	@Override
	public <T> Comando inserirObjeto(final T t) {
		pedidoRedisponibilizado = (PedidoRedisponibilizado) t;
		return this;
	}

	@Override
	public void manipular() {
		log.info("Comando para redisponiblizado : {}", pedidoRedisponibilizado);
		acessado = true;
	}
	
	boolean comandoAcessado() {
		return acessado;
	}

}
