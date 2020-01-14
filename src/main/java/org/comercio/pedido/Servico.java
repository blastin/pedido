package org.comercio.pedido;

import java.util.Collection;

import org.comercio.MapaComando;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Servico implements Pedidos {

	private final PedidoIO pedidoIO;

	private final MapaComando<Situacao> comandos;

	Servico(final PedidoIO pedidoIO, MapaComando<Situacao> comandos) {
		this.pedidoIO = pedidoIO;
		this.comandos = comandos;
	}

	@Override
	public void novoPedido(final NovoPedido novoPedido) {

		log.info("Novo pedido");

		final Collection<Produto> produtos = pedidoIO.produtos(novoPedido);

		final Pedido pedido = new Pedido(produtos);

		final Situacao pedidoDisponivelParaReserva = pedidoIO.reservaPedido(pedido);

		comandos.executar(pedidoDisponivelParaReserva,
				comando -> comando.inserirObjeto(new NovoPedido(pedido, novoPedido)));

	}

	@Override
	public void redisponibilizarPedido(final PedidoRedisponibilizado pedidoRedisponibilizado) {

		log.info("Redisponibilizar pedido");

		// DISPARAR EVENTO PARA REDISPONIBILIZAR PEDIDO

	}

	@Override
	public void pedidoPago(final PedidoPago pedidoPago) {

		log.info("Pedido pago");

		// DISPARAR EVENTO PARA MUDAR SITUAÇÃO DE PEDIDO PARA PAGO

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
