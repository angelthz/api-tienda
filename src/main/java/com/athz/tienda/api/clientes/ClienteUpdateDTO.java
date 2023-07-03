package com.athz.tienda.api.clientes;

import java.util.List;

import com.athz.tienda.api.direcciones.DireccionDTO;
import com.athz.tienda.api.direcciones.DireccionUpdateDTO;

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
