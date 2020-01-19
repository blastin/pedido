package org.comercio.pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.stream.Collectors;

import org.comercio.EntradaInformacoesException;
import org.comercio.produto.Produto;

import lombok.Getter;

class Pedido {

	@Getter
	private final Integer codigo;

	private final Collection<Produto> produtos;

	private final Collection<Integer> identificadoresProdutoCarrinho;

	Pedido(final Collection<Produto> produtos, final IdentificadorPedido identificadorPedido,
			final NovoPedido novoPedido) {
		if (produtos == null || produtos.isEmpty()) {
			throw new EntradaInformacoesException("Coleção de Produtos não pode ser nulo ou vazia");
		}
		this.produtos = produtos;
		if (identificadorPedido == null) {
			throw new EntradaInformacoesException("Identificador de pedido não pode ser nulo");
		}
		codigo = identificadorPedido.getIdentificador();
		this.identificadoresProdutoCarrinho = novoPedido.identificadoresProdutos();
	}

	BigDecimal custoTotal() {
		return produtos.stream().map(Produto::obterCusto).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2,
				RoundingMode.DOWN);
	}

	boolean reservado() {

		final Collection<Integer> identificadoresProduto = produtos.stream().map(Produto::obterCodigo)
				.collect(Collectors.toSet());

		return codigo != null && codigo > 0 && identificadoresProduto.containsAll(identificadoresProdutoCarrinho);

	}

}
