package org.comercio.pedido;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class RepositorioCache implements PedidoIO {

	private int statusAcesso;

	private final Integer identificador;

	private Situacao situacao;

	private boolean pedidoRedisponibilizado;

	RepositorioCache(final Integer identificador) {
		this.identificador = identificador;
		situacao = Situacao.AGUARDANDO_PAGAMENTO;
	}

	@Override
	public IdentificadorPedido reservar(final Collection<Integer> identificadoresProduto) {
		statusAcesso += 1;
		return new IdentificadorPedido(identificador);
	}

	@Override
	public void pago(final Integer obterIdentificadorPedido) {
		log.info("Pedido pago");
		situacao = Situacao.PAGO;
	}

	Boolean repositorioAcessado() {
		return statusAcesso == 1;
	}

	Boolean pedidoFoiPago() {
		return situacao.equals(Situacao.PAGO);
	}

	@Override
	public Situacao situacao(final IdentificadorPedido identificadorPedido) {
		return Situacao.AGUARDANDO_PAGAMENTO;
	}

	@Override
	public void redisponibilizar(final IdentificadorPedido identificadorPedido) {
		log.info("Pedido redisponibilizado");
		pedidoRedisponibilizado = true;
	}

	public boolean pedidoFoiRedisponibilizado() {
		return pedidoRedisponibilizado;
	}

}
