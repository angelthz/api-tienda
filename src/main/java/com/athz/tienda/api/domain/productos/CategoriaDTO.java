package com.athz.tienda.api.domain.productos;

public record CategoriaDTO (Integer id, String categoria) {
	public CategoriaDTO(CategoriaEntity categoria) {
		this(categoria.getId(), categoria.getCategoria());
	}
}
