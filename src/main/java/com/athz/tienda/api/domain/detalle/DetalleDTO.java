package com.athz.tienda.api.domain.detalle;

import java.math.BigDecimal;

public record DetalleDTO (
		Integer idVenta, 
		Integer idProducto, 
		Integer cantidad, 
		BigDecimal importe) {
	public DetalleDTO(DetalleEntity detalle) {
		this(	detalle.getFk().getVenta().getId(),
				detalle.getFk().getProducto().getId(),
				detalle.getCantidad(),
				detalle.getImporte());
	}
	
	public DetalleDTO(Integer idVenta, Integer idProducto, Integer cantidad) {
		this(idVenta, idProducto, cantidad, null);
	}
}
