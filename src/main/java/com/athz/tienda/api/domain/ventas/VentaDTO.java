package com.athz.tienda.api.domain.ventas;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VentaDTO (
		Integer id_venta,
		Integer id_cliente,
		Integer id_pago,
		LocalDate fecha,
		Integer total_productos,
		BigDecimal subtotal,
		BigDecimal descuento,
		BigDecimal iva,
		BigDecimal total
		
		){
	
	public VentaDTO(VentaEntity venta) {
		this(
			venta.getId(),
			venta.getCliente().getId(),
			venta.getPago().getId(),
			venta.getFecha(),
			venta.getTotalArticulos(),
			venta.getSubtotal(),
			venta.getDescuento(),
			venta.getIva(),
			venta.getTotalNeto()
				);
	}
}
