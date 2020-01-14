package org.comercio;

public interface MapaComando<T> {

	void executar(T t, Visitor visitor);

	MapaComando<T> inserirComando(T t, Comando comando);

}