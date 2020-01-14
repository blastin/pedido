package org.comercio;

public interface Visitor {

	Comando visitar(final VisitorObjeto comando);
	
}
