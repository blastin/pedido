package org.comercio;

public class ComandoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2145149798351496410L;

	public ComandoNaoEncontradoException(final String mensagem) {
		super(mensagem);
	}

}
