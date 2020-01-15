package org.comercio.pedido;

import java.util.Collection;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PedidoTest {

	@Rule
	public ExpectedException excessao = ExpectedException.none();

	@Test
	public void quandoPedidoCriadoSemReserva() {

		excessao.expect(PedidoNaoReservadoException.class);

		final Collection<Produto> produtos = Collections.emptyList();

		final Pedido pedido = new Pedido(produtos);

		new Pedido(pedido, new IdentificadorPedido(null));

	}

	@Test
	public void quandoPedidoCriadoComReserva() {

		final Collection<Produto> produtos = Collections.emptyList();

		final Pedido pedido = new Pedido(produtos);

		final int codigo = 1;

		final Pedido pedidoReserva = new Pedido(pedido, new IdentificadorPedido(codigo));

		Assert.assertSame("pedido deve ter codigo igual a 1", codigo, pedidoReserva.getCodigo());

	}

	@Test
	public void quandoPedidoCriadoComReservaDeIdentificadorZero() {

		excessao.expect(PedidoNaoReservadoException.class);

		final Collection<Produto> produtos = Collections.emptyList();

		final Pedido pedido = new Pedido(produtos);

		final int codigo = 0;

		new Pedido(pedido, new IdentificadorPedido(codigo));

	}
}
