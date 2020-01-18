package org.comercio;

public interface MapaComando<T> {

	void executar(final T t, final Visitor visitor);

	MapaComando<T> inserirComando(final T t, final Comando comando);

}