package org.comercio.pedido;

import java.util.Collection;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PedidoTest {

	@Rule
	public ExpectedException excessao = ExpectedException.none();

	@Test
	public void quandoPedidoCriadoSemReserva() {

		excessao.expect(PedidoNaoReservadoException.class);

		Collection<Produto> produtos = Collections.emptyList();

		final Pedido pedido = new Pedido(produtos);

		new Pedido(pedido, new IdentificadorPedido(null));

	}
}
