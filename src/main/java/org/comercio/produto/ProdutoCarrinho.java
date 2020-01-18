package org.comercio.produto;

import java.math.BigDecimal;

import org.comercio.EntradaInformacoesException;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ProdutoCarrinho {

	private static final String PATTERN_NUMBER = "[0-9]+(.[0-9]+)?";

	private final Integer codigo;

	private final BigDecimal custo;

	public ProdutoCarrinho(final Integer codigo, final String custo) {
		if (codigo == null || codigo <= 0) {
			throw new EntradaInformacoesException(
					"Identificador de Produto não pode ser nulo ou com valor menor que 0");
		}
		this.codigo = codigo;
		if (custo == null || !custo.matches(PATTERN_NUMBER)) {
			throw new EntradaInformacoesException("Custo do produto não pode ser nulo ou não numerico");
		}
		this.custo = new BigDecimal(custo);
		if (this.custo.compareTo(BigDecimal.ZERO) <= 0) {
			throw new EntradaInformacoesException("Custo não pode ser menor ou igual a 0");
		}
	}

}
