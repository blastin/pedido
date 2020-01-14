package org.comercio.pedido;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import lombok.Getter;

class Pedido {

	@Getter
	private final Integer codigo;

	private final Collection<Produto> produtos;

	private final Situacao situacao;

	Pedido(final Collection<Produto> produtos) {
		this.produtos = produtos;
		situacao = Situacao.AGUARDANDO_PAGAMENTO;
		codigo = 0;
	}

	Pedido(final Integer codigo, final Situacao situacao, final Collection<Produto> produtos) {
		this.codigo = codigo;
		this.produtos = produtos;
		this.situacao = situacao;
	}

	Collection<Integer> identificadoresProduto() {
		return produtos.stream().map(Produto::getCodigo).collect(Collectors.toSet());
	}

	BigDecimal custoTotal() {
		return produtos.stream().map(Produto::obterCusto).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	Boolean pedidoDespachado() {
		return situacao.equals(Situacao.DESPACHADO);
	}

	Boolean pedidoIndisponivel() {
		return situacao.equals(Situacao.INDISPONIVEL);
	}
}
