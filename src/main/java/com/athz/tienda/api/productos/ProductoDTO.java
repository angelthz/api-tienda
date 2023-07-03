package com.athz.tienda.api.productos;

import java.math.BigDecimal;

public record ProductoDTO (
		Integer id,
		String nombre, 
		BigDecimal precio,
		String categoria){

	public ProductoDTO(ProductoEntity prod) {
		this(	prod.getId(),
				prod.getNombre(),
				prod.getPrecio(),
				prod.getCategoria().getCategoria()
		);
	}

}
