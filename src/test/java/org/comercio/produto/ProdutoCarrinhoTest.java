package org.comercio.produto;

import java.math.BigDecimal;

import org.comercio.EntradaInformacoesException;
import org.junit.Assert;
import org.junit.Test;

public class ProdutoCarrinhoTest {

	private static final String CUSTO = "100";

	@Test
	public void construirProdutoCarrinho() {

		final ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho(1, CUSTO);

		Assert.assertSame("deve ter codigo igual a 1", 1, produtoCarrinho.getCodigo());

		Assert.assertEquals("deve custo igual a 100 ", new BigDecimal(CUSTO), produtoCarrinho.getCusto());

	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCodigoProdutoNulo() {
		new ProdutoCarrinho(null, CUSTO);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCodigoProdutoIgualZero() {
		new ProdutoCarrinho(0, CUSTO);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCodigoProdutoMenorQueZero() {
		new ProdutoCarrinho(-1, CUSTO);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCustoProdutoNulo() {
		new ProdutoCarrinho(1, null);
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCustoProdutoNaoNumerico() {
		new ProdutoCarrinho(1, "");
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCustoProdutoIgualZero() {
		new ProdutoCarrinho(1, "0");
	}

	@Test(expected = EntradaInformacoesException.class)
	public void quandoCustoProdutoMenorQueZero() {
		new ProdutoCarrinho(1, "-1");
	}

}
