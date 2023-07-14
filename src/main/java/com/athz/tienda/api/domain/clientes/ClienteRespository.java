package com.athz.tienda.api.domain.clientes;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClienteRespository extends JpaRepository<ClienteEntity, Integer>{

	public List<ClienteEntity> findByEstadoTrue();
	
	/**
	 * Al implementar la interface UserDetails, obtenemos un objeto de tipo UserDetails
	 * al realizar la busqueda de un usuario en la base de datos mediante su correo.
	 * UserDetails contiene el usuario y contraseña
	 * @param correo
	 * @return
	 */
	public UserDetails findByCorreo(String correo);

}
