package org.comercio.pedido;

import org.comercio.Comando;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ComandoDisponivel implements Comando {

	private CalculoFrete calculoFrete;

	@Override
	public <T> Comando inserirObjeto(final T t) {
		calculoFrete = (CalculoFrete) t;
		return this;
	}

	@Override
	public void manipular() {
		log.info("Pedido Disponivel. Calcular frete {}", calculoFrete);
	}

}
