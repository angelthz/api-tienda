package com.athz.tienda.api.domain.productos;

import java.math.BigDecimal;

public record UpdateProductoDTO (
		Integer id,
		String nombre,
		Integer cantidad,
		BigDecimal precio,
		CategoriaDTO categoria) {

}
