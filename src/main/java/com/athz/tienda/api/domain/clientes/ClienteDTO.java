package com.athz.tienda.api.domain.clientes;

import java.time.LocalDate;
import java.util.List;

import com.athz.tienda.api.domain.direcciones.DireccionDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

/**
 * 
 * @author Angel
 *
 */

/*
 * 
 * Las validaciones las hacemos a nivel de DTO
 */
public record ClienteDTO (
		@NotBlank
		String nombre,
		@NotBlank
		String paterno,
		@NotBlank
		String materno,
		@Email
		@NotBlank
		String correo,
		@NotBlank
		String password,
		@Past
		LocalDate fecha_nacim,
		@NotNull
		Genero genero,
		@NotBlank
		@Pattern(regexp="^\\d{10}$")
		String telefono,
		@NotNull
		@Valid
		List<DireccionDTO> direcciones){ 

}
