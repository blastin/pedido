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

		final MapaComando<Situacao> comandos = new MapeamentoComandos<Situacao>().inserirComando(Situacao.INDISPONIVEL,
				new ComandoIndisponivel());

		final RepositorioCache pedidoIO = new RepositorioCache();

		final Pedidos pedidos = new Servico(pedidoIO, comandos);

		final NovoPedido novoPedido = new NovoPedido(1, 1,
				Collections.singleton(new NovoPedido.ProdutoCarrinho(1, "100")));

		pedidos.novoPedido(novoPedido);

		Assert.assertTrue("Repositorio Cache Acessado", pedidoIO.repositorioAcessado());

	}

	@Test
	public void realizarNovoPedidoDisponivelTest() {

		final RepositorioCache pedidoIO = new RepositorioCache() {

			@Override
			public Situacao reservaPedido(Collection<Integer> collection) {
				return Situacao.DISPONIVEL;
			}

		};

		final MapaComando<Situacao> comandos = new MapeamentoComandos<Situacao>().inserirComando(Situacao.DISPONIVEL,
				new ComandoDisponivel());

		final Pedidos pedidos = new Servico(pedidoIO, comandos);

		final NovoPedido novoPedido = new NovoPedido(2, 5,
				Collections.singleton(new NovoPedido.ProdutoCarrinho(546, "200")));

		pedidos.novoPedido(novoPedido);

		Assert.assertTrue("Repositorio Cache Acessado", pedidoIO.repositorioAcessado());

	}
}
