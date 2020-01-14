package org.comercio.pedido;

import java.math.BigDecimal;
import java.util.Collection;

import lombok.Getter;

@Getter
public class NovoPedido {

	private final Integer codigoPedido;

	private final Integer codigoCliente;

	private final Integer codigoEndereco;

	private final Collection<ProdutoCarrinho> produtos;

	public NovoPedido(final Integer codigoCliente, final Integer codigoEndereco,
			final Collection<ProdutoCarrinho> produtos) {
		this.produtos = produtos;
		this.codigoEndereco = codigoEndereco;
		this.codigoCliente = codigoCliente;
		codigoPedido = 0;
	}

	NovoPedido(final Pedido pedido, final NovoPedido novoPedido) {
		this.produtos = novoPedido.getProdutos();
		this.codigoEndereco = novoPedido.codigoEndereco;
		this.codigoCliente = novoPedido.codigoCliente;
		this.codigoPedido = pedido.getCodigo();
	}

	@Getter
	static class ProdutoCarrinho {

		private final Integer codigo;

		private final BigDecimal custo;

		ProdutoCarrinho(final Integer codigo, final String custo) {
			this.codigo = codigo;
			this.custo = new BigDecimal(custo);
		}
	}
}
