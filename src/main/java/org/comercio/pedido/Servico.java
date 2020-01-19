package org.comercio.pedido;

import java.util.Collection;

import org.comercio.MapaComando;
import org.comercio.produto.Produto;
import org.comercio.produto.ProdutosGateway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Servico implements Pedidos {

	private final PedidoIO pedidoIO;

	private final ProdutosGateway produtosGateway;

	private final MapaComando<Situacao> comandos;

	Servico(final PedidoIO pedidoIO, final MapaComando<Situacao> comandos, final ProdutosGateway produtosGateway) {
		this.pedidoIO = pedidoIO;
		this.comandos = comandos;
		this.produtosGateway = produtosGateway;
	}

	@Override
	public void novoPedido(final NovoPedido novoPedido) {

		log.info("Novo pedido");

		final Collection<Produto> produtos = produtosGateway.produtos(novoPedido.getProdutosCarrinho());

		final IdentificadorPedido identificadorPedido = pedidoIO.reservaPedido(novoPedido.identificadoresProdutos());

		final Pedido pedido = new Pedido(produtos, identificadorPedido, novoPedido);

		if (pedido.reservado()) {
			comandos.executar(Situacao.DISPONIVEL,
					comando -> comando.inserirObjeto(new CalculoFrete(pedido, novoPedido)));
		} else {
			comandos.executar(Situacao.INDISPONIVEL, comando -> comando.inserirObjeto(novoPedido));
		}

	}

	@Override
	public void redisponibilizarPedido(final PedidoRedisponibilizado pedidoRedisponibilizado) {

		log.info("Redisponibilizar pedido");

		// DISPARAR EVENTO PARA REDISPONIBILIZAR PEDIDO

	}

	@Override
	public void pedidoPago(final PedidoPago pedidoPago) {

		log.info("Pedido pago");

		pedidoIO.pago(pedidoPago.obterIdentificadorPedido());

		comandos.executar(Situacao.PAGO, comando -> comando.inserirObjeto(pedidoPago));

	}

	@Override
	public void cancelarPedido(final PedidoCancelado pedidoCancelado) {

		log.info("Cancelar pedido");

		// VERIFICAR SE PEDIDO JÁ FOI DESPACHADO

		// CASO PEDIDO JÁ TENHA SIDO DESPACHADO, DISPARAR EVENTO PARA NOTIFICIAR

		// INDISPONIBILIDADE DE CANCELAMENTO

		// CASO CONTRÁRIO, DISPARAR EVENTO PARA ESTORNAR PAGAMENTO

	}

}
