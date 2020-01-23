package org.comercio.pedido;

import org.comercio.Comando;
import org.comercio.MapaComando;
import org.comercio.MapeamentoComandos;
import org.comercio.produto.ProdutosGateway;
import org.comercio.produto.ServicoProduto;
import org.junit.Assert;
import org.junit.Test;

public class PedidoRedisponibilizadoTest {

	private static final PedidoFabrica FABRICA = new PedidoFabricaImplementacao();

	private final int identificadorPedido = 1;

	@Test
	public void quandoPedidoEhRedisponibilizado() {

		final RepositorioCache pedidoIO = new RepositorioCache(identificadorPedido);

		final ComandoRedisponibilizado comando = new ComandoRedisponibilizado();

		construcao(comando, pedidoIO, Situacao.REDISPONIBILIZADO);

		Assert.assertTrue("repositorio foi acessado", pedidoIO.pedidoFoiRedisponibilizado());

		Assert.assertTrue("comando redisponibilizado deve ter sido acessado", comando.comandoAcessado());

	}

	@Test
	public void quandoPedidoCanceladoFazerRedisponibilizacao() {

		final RepositorioCache pedidoIO = new RepositorioCache(identificadorPedido) {

			@Override
			public Situacao situacao(IdentificadorPedido identificadorPedido) {
				return Situacao.CANCELADO;
			}

		};

		final ComandoRedisponibilizado comando = new ComandoRedisponibilizado();

		construcao(comando, pedidoIO, Situacao.REDISPONIBILIZADO);

		Assert.assertTrue("repositorio foi acessado", pedidoIO.pedidoFoiRedisponibilizado());

		Assert.assertTrue("comando redisponibilizado deve ter sido acessado", comando.comandoAcessado());

	}

	@Test
	public void quandoPedidoPagoNaoPodeRedisponibilizar() {

		final RepositorioCache pedidoIO = new RepositorioCache(identificadorPedido) {

			@Override
			public Situacao situacao(IdentificadorPedido identificadorPedido) {
				return Situacao.PAGO;
			}

		};

		final ComandoSemRedisponibilizar comando = new ComandoSemRedisponibilizar();

		construcao(comando, pedidoIO, Situacao.SEM_REDISPONIBILIZACAO);

		Assert.assertFalse("repositorio foi acessado", pedidoIO.pedidoFoiRedisponibilizado());

		Assert.assertTrue("comando sem redisponibilizade deve ter sido acessado", comando.comandoAcessado());

	}

	private void construcao(final Comando comando, final RepositorioCache pedidoIO, final Situacao situacao) {

		final ProdutosGateway produtosGateway = new ServicoProduto();

		final MapaComando<Situacao> mapaComando = new MapeamentoComandos<>();

		mapaComando.inserirComando(situacao, comando);

		final Pedidos pedidos = FABRICA.construir(pedidoIO, produtosGateway, mapaComando);

		final PedidoRedisponibilizado pedidoRedisponibilizado = new PedidoRedisponibilizado(identificadorPedido, 1);

		pedidos.redisponibilizarPedido(pedidoRedisponibilizado);

	}
}
