package org.comercio.pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;

import org.comercio.produto.Produto;
import org.comercio.produto.ProdutosGateway;
import org.comercio.produto.ServicoProduto;
import org.junit.Assert;
import org.junit.Test;

public class PedidoTest {

	private static final IdentificadorPedido IDENTIFICADOR_PEDIDO = new IdentificadorPedido(1);

	@Test
	public void quandoPedidoCriadoComReserva() {

		final Collection<Produto> produtos = Collections.emptyList();

		final Pedido pedido = new Pedido(produtos, IDENTIFICADOR_PEDIDO);

		Assert.assertSame("pedido deve ter codigo igual a 1", IDENTIFICADOR_PEDIDO.getIdentificador(),
				pedido.getCodigo());

		Assert.assertTrue("pedido deve ter sido reservado", pedido.reservado());

	}

	@Test
	public void custoDePedidoDeveSerIgual() {

		final ProdutosGateway produtosGateway = new ServicoProduto();

		final Collection<Produto> produtos = produtosGateway.produtos(Collections.singleton(1));

		final Pedido pedido = new Pedido(produtos, IDENTIFICADOR_PEDIDO);

		final BigDecimal custoTotal = pedido.custoTotal();

		Assert.assertEquals("custo deve ser igual", new BigDecimal(5779.626).setScale(2, RoundingMode.DOWN),
				custoTotal);

	}

	@Test
	public void quandoPedidoCriadoSemReserva() {

		final Collection<Produto> produtos = Collections.emptyList();

		final IdentificadorPedido identificadorPedido = new IdentificadorPedido(0);

		final Pedido pedido = new Pedido(produtos, identificadorPedido);

		Assert.assertFalse("pedido não deve ter sido reservado", pedido.reservado());

	}

	@Test
	public void quandoPedidoCriadoComReservaDeIdentificadorMenorQueZero() {

		final Collection<Produto> produtos = Collections.emptyList();

		final IdentificadorPedido identificadorPedido = new IdentificadorPedido(-1);

		final Pedido pedido = new Pedido(produtos, identificadorPedido);

		Assert.assertFalse("pedido não deve ter sido reservado", pedido.reservado());

	}
}
