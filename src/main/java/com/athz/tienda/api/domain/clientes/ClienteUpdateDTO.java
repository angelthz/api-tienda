package com.athz.tienda.api.domain.clientes;

import java.util.List;

import com.athz.tienda.api.domain.direcciones.DireccionDTO;
import com.athz.tienda.api.domain.direcciones.DireccionUpdateDTO;

import jakarta.validation.constraints.NotNull;

public record ClienteUpdateDTO(
		@NotNull
		Integer id,
		String nombre,
		String paterno,
		String materno,
		String telefono,
		List<DireccionUpdateDTO> direcciones) {

}
