package org.comercio.pedido;

import org.comercio.EntradaInformacoesException;
import org.comercio.MapaComando;
import org.comercio.MapeamentoComandos;
import org.comercio.produto.ProdutosGateway;
import org.comercio.produto.ServicoProduto;
import org.junit.Assert;
import org.junit.Test;

public class PedidoPagoTest {

	@Test
	public void quandoPedidoMudaParaPago() {

		final RepositorioCache pedidoIO = new RepositorioCache(1);

		final MapaComando<Situacao> mapaComando = new MapeamentoComandos<Situacao>();

		final ComandoPedidoPago comandoPedidoPago = new ComandoPedidoPago();

		mapaComando.inserirComando(Situacao.PAGO, comandoPedidoPago);

		final ProdutosGateway produtosGateway = new ServicoProduto();

		final Pedidos pedidos = new Servico(pedidoIO, mapaComando, produtosGateway);

		final PedidoPago pedidoPago = new PedidoPago(1, 1);

		pedidos.pedidoPago(pedidoPago);

		Assert.assertTrue("Pedido deve ter sido pago", pedidoIO.pedidoFoiPago());

		Assert.assertTrue("comando deve ter sido acessado", comandoPedidoPago.acessado());

	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorPedidoNulo() {
		new PedidoPago(null, 1);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorPedidoIgualZero() {
		new PedidoPago(0, 1);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorPedidoMenorZero() {
		new PedidoPago(-1, 1);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorClienteNulo() {
		new PedidoPago(1, null);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorClienteIgualZero() {
		new PedidoPago(1, 0);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoIdentificadorClientedorMenorZero() {
		new PedidoPago(1, -1);
	}
}
