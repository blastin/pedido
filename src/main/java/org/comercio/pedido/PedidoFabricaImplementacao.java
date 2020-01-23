package org.comercio.pedido;

import org.comercio.MapaComando;
import org.comercio.produto.ProdutosGateway;

public class PedidoFabricaImplementacao implements PedidoFabrica {

	@Override
	public Pedidos construir(PedidoIO pedidoIO, ProdutosGateway produtosGateway, MapaComando<Situacao> mapaComando) {
		return new Servico(pedidoIO, mapaComando, produtosGateway);
	}

}
