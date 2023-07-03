package com.athz.tienda.api.direcciones;

import java.util.List;

public record DireccionAddDTO (
		Integer idCliente,
		List<DireccionDTO> direcciones){
}
