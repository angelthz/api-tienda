package com.athz.tienda.api.domain.direcciones;

import java.util.List;

public record DireccionAddDTO (
		Integer idCliente,
		List<DireccionDTO> direcciones){
}
