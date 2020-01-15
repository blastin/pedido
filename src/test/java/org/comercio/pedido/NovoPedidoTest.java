package org.comercio.pedido;

import java.util.Collection;
import java.util.Collections;

import org.comercio.MapaComando;
import org.comercio.MapeamentoComandos;
import org.junit.Assert;
import org.junit.Test;

public class NovoPedidoTest {

	@Test
	public void realizarNovoPedidoIndisponivelTest() {

		final ComandoIndisponivel comando = new ComandoIndisponivel();

		final MapaComando<Situacao> comandos = new MapeamentoComandos<Situacao>().inserirComando(Situacao.INDISPONIVEL,
				comando);

		final RepositorioCache pedidoIO = new RepositorioCache(null);

		final Pedidos pedidos = new Servico(pedidoIO, comandos);

		final NovoPedido novoPedido = new NovoPedido(1, 1,
				Collections.singleton(new NovoPedido.ProdutoCarrinho(1, "100")));

		pedidos.novoPedido(novoPedido);

		Assert.assertTrue("Repositorio Cache Acessado", pedidoIO.repositorioAcessado());

		Assert.assertTrue("comando deve ser visitado", comando.visitado());

	}

	@Test
	public void realizarNovoPedidoDisponivelTest() {

		final RepositorioCache pedidoIO = new RepositorioCache(1) {

			@Override
			public IdentificadorPedido reservaPedido(Collection<Integer> collection) {
				return new IdentificadorPedido(0);
			}

		};

		final ComandoDisponivel comando = new ComandoDisponivel();

		final MapaComando<Situacao> comandos = new MapeamentoComandos<Situacao>().inserirComando(Situacao.DISPONIVEL,
				comando);

		final Pedidos pedidos = new Servico(pedidoIO, comandos);

		final NovoPedido novoPedido = new NovoPedido(2, 5,
				Collections.singleton(new NovoPedido.ProdutoCarrinho(546, "200")));

		pedidos.novoPedido(novoPedido);

		Assert.assertTrue("Repositorio Cache Acessado", pedidoIO.repositorioAcessado());

		Assert.assertTrue("comando deve ser visitado", comando.visitado());

	}
}
