package org.comercio.infraestrutura;

import org.comercio.Comando;
import org.comercio.ComandoNaoEncontradoException;
import org.comercio.MapaComando;
import org.comercio.MapeamentoComandos;
import org.junit.Assert;
import org.junit.Test;

public class MapaComandoTest {

	static final String OBJETO = "Teste";

	@Test
	public void quandoMapComandoExistente() {

		final MapaComando<Integer> mapa = new MapeamentoComandos<>();

		mapa.inserirComando(0, new ComandoTest());

		mapa.executar(0, comando -> {
			Assert.assertNotNull("comando não deve ser nulo", comando);
			return comando.inserirObjeto(OBJETO);
		});

	}

	@Test(expected = ComandoNaoEncontradoException.class)
	public void quandoMapComandoInexistente() {

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
