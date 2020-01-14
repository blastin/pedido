package org.comercio.pedido;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Servico implements Pedidos {

	private final PedidoIO pedidoIO;

	private final Comandos<Situacao> comandos;

	Servico(final PedidoIO pedidoIO, Comandos<Situacao> comandos) {
		this.pedidoIO = pedidoIO;
		this.comandos = comandos;
	}

	@Override
	public void novoPedido(final NovoPedido novoPedido) {

		log.info("Novo pedido");

		// OBTER PRODUTOS
		final Collection<Produto> produtos = pedidoIO.produtos(novoPedido);

		final Pedido pedido = new Pedido(produtos);

		final Situacao pedidoDisponivelParaReserva = pedidoIO.reservaPedido(pedido);

		// VERIFICAR SE PEDIDO É DISPONIVEL PARA RESERVA

		// CASO NÃO SEJA, DISPARAR EVENTO PARA NOTIFICAR INDISPONIBILIDADE

		if (pedidoDisponivelParaReserva.equals(Situacao.INDISPONIVEL)) {
			log.info("Pedido indisponivel");
			comandos.executar(pedidoDisponivelParaReserva, comando -> comando.inserirObjeto(novoPedido));
		}

		// CASO EXISTA DISPONIBILIDADE DE RESERVA , CALCULAR CUSTO TOTAL DO PEDIDO

		else {

			log.info("Pedido disponivel");

			// DISPARAR EVENTO PARA AGREGAR CUSTO DE FRETE

			comandos.executar(Situacao.DISPONIVEL,
					comando -> comando.inserirObjeto(new NovoPedido(pedido, novoPedido)));
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
