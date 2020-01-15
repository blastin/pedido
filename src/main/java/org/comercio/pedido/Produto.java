package org.comercio.pedido;

import java.math.BigDecimal;

import lombok.Getter;

class Produto {

	@Getter
	private final Integer codigo;

	private final BigDecimal custo;

	private final Integer desconto;

	private final Float peso;

	Produto(final Integer codigo, final BigDecimal custo, final Integer desconto, final Float peso) {
		this.codigo = codigo;
		this.custo = custo;
		this.desconto = desconto;
		this.peso = peso;
	}

	BigDecimal obterCusto() {

		final BigDecimal custoComFatorPeso = custo.add(BigDecimal.valueOf(0.2 * peso));

		final BigDecimal porcentagem = new BigDecimal(desconto).divide(BigDecimal.valueOf(100));

		return custoComFatorPeso.multiply(BigDecimal.ONE.subtract(porcentagem));

	}

}
