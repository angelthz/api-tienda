package com.athz.tienda.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.athz.tienda.api.domain.clientes.ClienteRespository;

@Service
public class AutenticacionService implements UserDetailsService{

	@Autowired
	private ClienteRespository clienteRep;
	
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		//retornar un usuario por nombre de usurio
		//en este caso por su correo
		System.out.println("Aut entication service");
		return clienteRep.findByCorreo(correo);
	}
	
}
