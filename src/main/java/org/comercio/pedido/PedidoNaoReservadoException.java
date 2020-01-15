package org.comercio.pedido;

public class PedidoNaoReservadoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3842443268452971995L;

	public PedidoNaoReservadoException(final String mensagem) {
		super(mensagem);
	}

}
