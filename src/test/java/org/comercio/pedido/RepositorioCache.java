package org.comercio.pedido;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class RepositorioCache implements PedidoIO {

	private int statusAcesso;

	private final Integer identificador;

	private Situacao situacao;

	public RepositorioCache(Integer identificador) {
		this.identificador = identificador;
		situacao = Situacao.AGUARDANDO_PAGAMENTO;
	}

	@Override
	public IdentificadorPedido reservaPedido(final Collection<Integer> collection) {
		statusAcesso += 1;
		return new IdentificadorPedido(identificador);
	}

	@Override
	public void pago(Integer obterIdentificadorPedido) {
		log.info("Pedido pago");
		situacao = Situacao.PAGO;
	}

	Boolean repositorioAcessado() {
		return statusAcesso == 1;
	}

	Boolean pedidoFoiPago() {
		return situacao.equals(Situacao.PAGO);
	}

}
