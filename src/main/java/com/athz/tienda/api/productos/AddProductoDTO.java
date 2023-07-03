package com.athz.tienda.api.productos;

import java.math.BigDecimal;

public record AddProductoDTO(
		String nombre, 
		BigDecimal precio, 
		Integer cantidad,
		CategoriaDTO categoria) {

}
