package org.comercio.pedido;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import lombok.Getter;

class Pedido {

	@Getter
	private final Integer codigo;

	private final Collection<Produto> produtos;

	Pedido(final Collection<Produto> produtos) {
		this.produtos = produtos;
		codigo = 0;
	}

	Pedido(Pedido pedido, IdentificadorPedido identificadorPedido) {
		this.produtos = pedido.produtos;
		codigo = identificadorPedido.obterIdentificador().filter(valor -> !valor.equals(0))
				.orElseThrow(() -> new PedidoNaoReservadoException("Pedido não reservado"));
	}

	Collection<Integer> identificadoresProduto() {
		return produtos.stream().map(Produto::getCodigo).collect(Collectors.toSet());
	}

	BigDecimal custoTotal() {
		return produtos.stream().map(Produto::obterCusto).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
