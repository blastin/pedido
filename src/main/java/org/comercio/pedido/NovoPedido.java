package org.comercio.pedido;

import java.util.Collection;
import java.util.stream.Collectors;

import org.comercio.EntradaInformacoesException;
import org.comercio.produto.ProdutoCarrinho;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class NovoPedido {

	private final Integer codigoCliente;

	private final Integer codigoEndereco;

	private final Collection<ProdutoCarrinho> produtos;

	public NovoPedido(final Integer codigoCliente, final Integer codigoEndereco,
			final Collection<ProdutoCarrinho> produtos) {
		if (codigoCliente == null || codigoCliente <= 0) {
			throw new EntradaInformacoesException(
					"Identificador de cliente não pode ser nulo ou com valor menor que 0");
		}
		this.codigoCliente = codigoCliente;
		if (codigoEndereco == null || codigoEndereco <= 0) {
			throw new EntradaInformacoesException(
					"Identificador de endereço não pode ser nulo ou com valor menor que 0");
		}
		this.codigoEndereco = codigoEndereco;
		if (produtos == null || produtos.isEmpty()) {
			throw new EntradaInformacoesException("Coleção de produtos não pode ser nulo ou vazio");
		}
		this.produtos = produtos;
	}

	public Collection<Integer> identificadoresProdutos() {
		return produtos.stream().map(ProdutoCarrinho::getCodigo).collect(Collectors.toSet());
	}

}
