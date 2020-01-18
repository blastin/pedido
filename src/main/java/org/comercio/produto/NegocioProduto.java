package org.comercio.produto;

import java.math.BigDecimal;

import org.comercio.EntradaInformacoesException;

class NegocioProduto implements Produto {

	private final Integer codigo;

	private final BigDecimal custo;

	private final Integer desconto;

	private final Float peso;

	private final Integer quantidade;

	NegocioProduto(final Integer codigo, final BigDecimal custo, final Integer desconto, final Float peso,
			final Integer quantidade) {
		if (codigo == null || codigo <= 0) {
			throw new EntradaInformacoesException(
					"Identificador de produto não pode ser nulo ou com valor menor ou igual a 0");
		}
		this.codigo = codigo;
		if (custo == null || custo.compareTo(BigDecimal.ZERO) <= 0) {
			throw new EntradaInformacoesException("Custo de produto não pode ser nulo ou com valor menor ou igual a 0");
		}
		this.custo = custo;
		if (desconto == null || desconto < 0) {
			throw new EntradaInformacoesException("Desconto para produto não pode ser nulo ou com valor menor que 0");
		}
		this.desconto = desconto;
		if (peso == null || peso <= 0) {
			throw new EntradaInformacoesException("Peso de produto não pode ser nulo ou com valor menor ou igual a 0");
		}
		this.peso = peso;
		if (quantidade == null || quantidade <= 0) {
			throw new EntradaInformacoesException(
					"Quantidade de produtos não pode ser nulo ou com valor menor ou igual a 0");
		}
		this.quantidade = quantidade;
	}

	@Override
	public Integer obterCodigo() {
		return codigo;
	}

	@Override
	public BigDecimal obterCusto() {

		final BigDecimal custoComFatorPeso = custo.add(BigDecimal.valueOf(0.2 * peso));

		final BigDecimal porcentagem = new BigDecimal(desconto).divide(BigDecimal.valueOf(100));

		return custoComFatorPeso.multiply(BigDecimal.ONE.subtract(porcentagem))
				.multiply(BigDecimal.valueOf(quantidade));

	}

}
