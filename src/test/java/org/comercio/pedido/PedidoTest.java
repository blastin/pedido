package org.comercio.pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;

import org.comercio.EntradaInformacoesException;
import org.comercio.produto.Produto;
import org.comercio.produto.ProdutoCarrinho;
import org.comercio.produto.ProdutosGateway;
import org.comercio.produto.ServicoProduto;
import org.junit.Assert;
import org.junit.Test;

public class PedidoTest {

	private static final IdentificadorPedido IDENTIFICADOR_PEDIDO = new IdentificadorPedido(1);

	private static final Integer CODIGO_PRODUTO = 546;

	private static final NovoPedido NOVO_PEDIDO = new NovoPedido(2, 5,
			Collections.singleton(new ProdutoCarrinho(CODIGO_PRODUTO, "200")));

	private static final ProdutosGateway PRODUTOS_GATEWAY = new ServicoProduto();

	private static final Collection<Produto> PRODUTOS = PRODUTOS_GATEWAY.produtos(NOVO_PEDIDO.getProdutosCarrinho());

	@Test
	public void quandoPedidoCriadoComReserva() {

		final Pedido pedido = new Pedido(PRODUTOS, IDENTIFICADOR_PEDIDO, NOVO_PEDIDO);

		Assert.assertSame("pedido deve ter codigo igual a 1", IDENTIFICADOR_PEDIDO.getIdentificador(),
				pedido.getCodigo());

		Assert.assertTrue("pedido deve ter sido reservado", pedido.reservado());

	}

	@Test
	public void custoDePedidoDeveSerIgual() {

		final Pedido pedido = new Pedido(PRODUTOS, IDENTIFICADOR_PEDIDO, NOVO_PEDIDO);

		final BigDecimal custoTotal = pedido.custoTotal();

		Assert.assertEquals("custo deve ser igual", new BigDecimal(11554.62).setScale(2, RoundingMode.DOWN),
				custoTotal);

	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoPedidoCriadoComIdentificadorPedidoNulo() {

		new Pedido(PRODUTOS, null, NOVO_PEDIDO);

	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoPedidoCriadoComColeçãoProdutosNulo() {

		new Pedido(null, IDENTIFICADOR_PEDIDO, NOVO_PEDIDO);

	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoPedidoCriadoComColeçãoProdutosVazia() {

		new Pedido(Collections.emptyList(), IDENTIFICADOR_PEDIDO, NOVO_PEDIDO);

	}

	@Test
	public void quandoPedidoCriadoSemReserva() {

		final IdentificadorPedido identificadorPedido = new IdentificadorPedido(0);

		final Pedido pedido = new Pedido(PRODUTOS, identificadorPedido, NOVO_PEDIDO);

		Assert.assertFalse("pedido não deve ter sido reservado", pedido.reservado());

	}

	@Test
	public void quandoPedidoCriadoComReservaDeIdentificadorMenorQueZero() {

		final IdentificadorPedido identificadorPedido = new IdentificadorPedido(-1);

		final Pedido pedido = new Pedido(PRODUTOS, identificadorPedido, NOVO_PEDIDO);

		Assert.assertFalse("pedido não deve ter sido reservado", pedido.reservado());

	}

	@Test
	public void quandoPedidoCriadoComIdentificadoresProdutoCarrinhoEmNovoPedidoDiferenteDeIdentificadoresProduto() {

		final IdentificadorPedido identificadorPedido = new IdentificadorPedido(-1);

		final Pedido pedido = new Pedido(PRODUTOS, identificadorPedido, NOVO_PEDIDO);

		Assert.assertFalse("pedido não deve ter sido reservado", pedido.reservado());

	}
}
