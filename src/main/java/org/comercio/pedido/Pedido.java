package org.comercio.pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

import org.comercio.produto.Produto;

import lombok.Getter;

class Pedido {

	@Getter
	private final Integer codigo;

	private final Collection<Produto> produtos;

	Pedido(final Collection<Produto> produtos, final IdentificadorPedido identificadorPedido) {
		this.produtos = produtos;
		codigo = identificadorPedido.getIdentificador();
	}

	BigDecimal custoTotal() {
		return produtos.stream().map(Produto::obterCusto).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2,
				RoundingMode.DOWN);
	}

	boolean reservado() {
		return codigo != null && codigo > 0;
	}

}
