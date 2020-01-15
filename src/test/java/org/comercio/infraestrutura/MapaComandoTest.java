package org.comercio.infraestrutura;

import org.comercio.Comando;
import org.comercio.ComandoNaoEncontradoException;
import org.comercio.MapaComando;
import org.comercio.MapeamentoComandos;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MapaComandoTest {

	static final String OBJETO = "Teste";

	@Rule
	public ExpectedException excessao = ExpectedException.none();

	@Test
	public void quandoMapComandoExistente() {

		final MapaComando<Integer> mapa = new MapeamentoComandos<>();

		mapa.inserirComando(0, new ComandoTest());

		mapa.executar(0, comando -> {
			Assert.assertNotNull("comando não deve ser nulo", comando);
			return comando.inserirObjeto(OBJETO);
		});

	}

	@Test
	public void quandoMapComandoInexistente() {

		excessao.expect(ComandoNaoEncontradoException.class);

		final MapaComando<Integer> mapa = new MapeamentoComandos<>();

		mapa.inserirComando(0, new ComandoTest());

		mapa.executar(1, comando -> comando.inserirObjeto(null));

	}

	static class ComandoTest implements Comando {

		private String objeto;

		@Override
		public <T> Comando inserirObjeto(T t) {
			objeto = (String) t;
			return this;
		}

		@Override
		public void manipular() {
			Assert.assertEquals("Deve ser Teste", OBJETO, objeto);
		}

	}
}
