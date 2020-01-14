package org.comercio.pedido;

import org.comercio.Comando;
import org.comercio.Visitor;

public interface Comandos<T> {

	void executar(T t, Visitor visitor);

	Comandos<T> inserirComando(T t, Comando comando);

}