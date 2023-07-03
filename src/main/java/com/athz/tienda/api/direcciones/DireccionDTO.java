package com.athz.tienda.api.direcciones;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DireccionDTO (
		@NotBlank
		String estado,
		@NotBlank
		String municipio,
		@NotBlank
		String colonia,
		@NotBlank
		String calle,
		@NotNull
		Integer numero,
		@NotBlank
		String cp
		){
}
