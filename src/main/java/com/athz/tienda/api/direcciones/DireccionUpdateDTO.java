package com.athz.tienda.api.direcciones;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DireccionUpdateDTO(
		@NotNull
		Integer id,
		String estado,
		String municipio,
		String colonia,
		String calle,
		Integer numero,
		String cp
		) {

}
