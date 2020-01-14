package org.comercio;

import java.util.HashMap;

public class MapeamentoComandos<T> implements MapaComando<T> {

	private HashMap<T, Comando> mapaComandos;

	public MapeamentoComandos() {
		mapaComandos = new HashMap<>();
	}

	@Override
	public void executar(final T t, Visitor visitor) {
		if (!mapaComandos.containsKey(t)) {
			throw new ComandoNaoEncontradoException(
					String.format("Não foi possivel encontrar comando = %s", t.getClass()));
		}
		visitor.visitar(mapaComandos.get(t)).manipular();
	}

	@Override
	public MapaComando<T> inserirComando(final T t, final Comando comando) {
		mapaComandos.put(t, comando);
		return this;
	}

}
