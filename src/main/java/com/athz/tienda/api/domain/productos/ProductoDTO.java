package com.athz.tienda.api.domain.productos;

import java.math.BigDecimal;

public record ProductoDTO (
		Integer id,
		String nombre, 
		BigDecimal precio,
		Integer cantidad,
		CategoriaDTO categoria){
		public ProductoDTO(ProductoEntity prod) {
			this(
					prod.getId(),
					prod.getNombre(),
					prod.getPrecio(),
					prod.getCantidad(),
					new CategoriaDTO(prod.getCategoria()));
		}
}
