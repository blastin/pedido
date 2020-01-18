package org.comercio.produto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.comercio.EntradaInformacoesException;
import org.junit.Assert;
import org.junit.Test;

public class NegocioProdutoTest {

	@Test
	public void construirProduto() {

		final Integer codigoProduto = 664;

		final NegocioProduto negocioProduto = new NegocioProduto(codigoProduto, BigDecimal.valueOf(100), 74, 0.4F, 77);

		Assert.assertEquals("custo deve ser igual a ", BigDecimal.valueOf(2003.60).setScale(2, RoundingMode.DOWN),
				negocioProduto.obterCusto().setScale(2, RoundingMode.DOWN));

		Assert.assertEquals("codigo deve ser igual a 664", codigoProduto, negocioProduto.obterCodigo());

	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCodigoProdutoNulo() {
		new NegocioProduto(null, BigDecimal.valueOf(100), 25, 0.4F, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCodigoProdutoIgualZero() {
		new NegocioProduto(0, BigDecimal.valueOf(100), 25, 0.4F, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCodigoProdutoMenorQueZero() {
		new NegocioProduto(-1, BigDecimal.valueOf(100), 25, 0.4F, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCustoProdutoNulo() {
		new NegocioProduto(11, null, 25, 0.4F, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCustoProdutoIgualZero() {
		new NegocioProduto(1, BigDecimal.valueOf(0), 25, 0.4F, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCustoProdutoMenorQueZero() {
		new NegocioProduto(1, BigDecimal.valueOf(-1), 25, 0.4F, 77);
	}

	@Test
	public void quandoDescontoIgualZero() {

		final NegocioProduto negocioProduto = new NegocioProduto(1, BigDecimal.valueOf(100), 0, 0.4F, 77);

		Assert.assertEquals("custo deve ser igual a ", BigDecimal.valueOf(7706.16).setScale(2, RoundingMode.DOWN),
				negocioProduto.obterCusto().setScale(2, RoundingMode.DOWN));

	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoDescontoNulo() {
		new NegocioProduto(11, BigDecimal.valueOf(100), null, 5F, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoDescontoMenorZero() {
		new NegocioProduto(1, BigDecimal.valueOf(100), -25, 1F, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoPesoNulo() {
		new NegocioProduto(11, BigDecimal.valueOf(100), 25, null, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoPesoIgualZero() {
		new NegocioProduto(1, BigDecimal.valueOf(100), 25, 0F, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoPesoMenorZero() {
		new NegocioProduto(1, BigDecimal.valueOf(100), 25, -1F, 77);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoQuantidadeNulo() {
		new NegocioProduto(11, BigDecimal.valueOf(100), 25, 4F, null);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoQuantidadeIgualZero() {
		new NegocioProduto(1, BigDecimal.valueOf(100), 25, 4F, 0);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoQuantidadeMenorZero() {
		new NegocioProduto(1, BigDecimal.valueOf(100), 25, 4F, -77);
	}

}
