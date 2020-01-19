package org.comercio.pedido;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CalculoFrete {

	private final Integer codigoCliente;

	private final Integer codigoPedido;

	private final Integer codigoEndereco;

	private final BigDecimal custoPedido;

	CalculoFrete(final Pedido pedido, final NovoPedido novoPedido) {
		codigoPedido = pedido.getCodigo();
		custoPedido = pedido.custoTotal();
		codigoCliente = novoPedido.getCodigoCliente();
		codigoEndereco = novoPedido.getCodigoEndereco();
	}
}
