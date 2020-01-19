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

	private final Collection<ProdutoCarrinho> produtosCarrinho;

	public NovoPedido(final Integer codigoCliente, final Integer codigoEndereco,
			final Collection<ProdutoCarrinho> produtosCarrinho) {
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
		if (produtosCarrinho == null || produtosCarrinho.isEmpty()) {
			throw new EntradaInformacoesException("Coleção de produtos não pode ser nulo ou vazio");
		}
		this.produtosCarrinho = produtosCarrinho;
	}

	Collection<Integer> identificadoresProdutos() {
		return produtosCarrinho.stream().map(ProdutoCarrinho::getCodigo).collect(Collectors.toSet());
	}

}
