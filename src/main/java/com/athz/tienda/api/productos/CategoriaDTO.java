package com.athz.tienda.api.productos;

public record CategoriaDTO (Integer id, String categoria) {
	public CategoriaDTO(CategoriaEntity categoria) {
		this(categoria.getId(), categoria.getCategoria());
	}
}
