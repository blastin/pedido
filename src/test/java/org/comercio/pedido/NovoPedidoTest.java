package org.comercio.pedido;

import java.util.Collection;
import java.util.Collections;

import org.comercio.EntradaInformacoesException;
import org.comercio.MapaComando;
import org.comercio.MapeamentoComandos;
import org.comercio.produto.ProdutoCarrinho;
import org.comercio.produto.ProdutosGateway;
import org.comercio.produto.ServicoProduto;
import org.junit.Assert;
import org.junit.Test;

public class NovoPedidoTest {

	private static final String CUSTO = "100";

	private static final Collection<ProdutoCarrinho> COLECAO = Collections.singleton(new ProdutoCarrinho(1, CUSTO));

	@Test
	public void realizarNovoPedidoIndisponivelTest() {

		final ComandoIndisponivel comando = new ComandoIndisponivel();

		final MapaComando<Situacao> comandos = new MapeamentoComandos<Situacao>().inserirComando(Situacao.INDISPONIVEL,
				comando);

		final RepositorioCache pedidoIO = new RepositorioCache(null);

		final ProdutosGateway produtosGateway = new ServicoProduto();

		final Pedidos pedidos = new Servico(pedidoIO, comandos, produtosGateway);

		final NovoPedido novoPedido = new NovoPedido(1, 1, Collections.singleton(new ProdutoCarrinho(1, CUSTO)));

		pedidos.novoPedido(novoPedido);

		Assert.assertTrue("Repositorio Cache Acessado", pedidoIO.repositorioAcessado());

		Assert.assertTrue("comando deve ser visitado", comando.visitado());

	}

	@Test
	public void realizarNovoPedidoDisponivelTest() {

		final RepositorioCache pedidoIO = new RepositorioCache(1) {

			@Override
			public IdentificadorPedido reservaPedido(final Collection<Integer> collection) {
				super.reservaPedido(collection);
				return new IdentificadorPedido(1);
			}

		};

		final ComandoDisponivel comando = new ComandoDisponivel();

		final MapaComando<Situacao> comandos = new MapeamentoComandos<Situacao>().inserirComando(Situacao.DISPONIVEL,
				comando);

		final ProdutosGateway produtosGateway = new ServicoProduto();

		final Pedidos pedidos = new Servico(pedidoIO, comandos, produtosGateway);

		final NovoPedido novoPedido = new NovoPedido(2, 5, Collections.singleton(new ProdutoCarrinho(546, "200")));

		pedidos.novoPedido(novoPedido);

		Assert.assertTrue("Repositorio Cache Acessado", pedidoIO.repositorioAcessado());

		Assert.assertTrue("comando deve ser visitado", comando.visitado());

	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorPedidoNulo() {
		new NovoPedido(null, 1, COLECAO);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorPedidoIgualZero() {
		new NovoPedido(0, 1, COLECAO);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorPedidoMenorZero() {
		new NovoPedido(-1, 1, COLECAO);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorEnderecoNulo() {
		new NovoPedido(1, null, COLECAO);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorEnderecoIgualZero() {
		new NovoPedido(1, 0, COLECAO);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorEnderecodorMenorZero() {
		new NovoPedido(1, -1, COLECAO);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoProdutoNulo() {
		new NovoPedido(1, 1, null);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoProdutoVazio() {
		new NovoPedido(1, 1, Collections.emptyList());
	}

}
