package com.athz.tienda.api.clientes;

import java.util.List;

import com.athz.tienda.api.direcciones.DireccionEntity;

public record ClienteResultDTO(
		Integer id,
		String nombre,
		String paterno,
		String materno,
		String correo,
		String telefono,
		boolean estado,
		List<DireccionEntity> direcciones) {
	
	public ClienteResultDTO(ClienteEntity cliente) {
		this(
				cliente.getId(),
				cliente.getNombre(),
				cliente.getPaterno(),
				cliente.getMaterno(),
				cliente.getCorreo(),
				cliente.getTelefono(),
				cliente.getEstado(),
				cliente.getDirecciones()
		);
	}

}
