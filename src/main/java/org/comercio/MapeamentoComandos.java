package org.comercio;

import java.util.HashMap;

import org.comercio.pedido.Comandos;

public class MapeamentoComandos<T> implements Comandos<T> {

	private HashMap<T, Comando> mapaComandos;

	public MapeamentoComandos() {
		mapaComandos = new HashMap<>();
	}

	@Override
	public void executar(final T t, Visitor visitor) {
		if (!mapaComandos.containsKey(t)) {
			throw new ComandoNaoEncontratoException(
					String.format("Não foi possivel encontrar comando = %s", t.getClass()));
		}
		visitor.visitar(mapaComandos.get(t)).manipular();
	}

	@Override
	public Comandos<T> inserirComando(final T t, final Comando comando) {
		mapaComandos.put(t, comando);
		return this;
	}

	static class ComandoNaoEncontratoException extends RuntimeException {

		public ComandoNaoEncontratoException(final String mensagem) {
			super(mensagem);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 2145149798351496410L;

	}

}
