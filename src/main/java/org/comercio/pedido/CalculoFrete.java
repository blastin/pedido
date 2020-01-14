package org.comercio.pedido;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class CalculoFrete {

	private Integer codigoCliente;

	private Integer codigoPedido;

	private Integer codigoEndeco;

	private BigDecimal custoPedido;

}
