package com.athz.tienda.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.athz.tienda.api.domain.clientes.ClienteEntity;
import com.athz.tienda.api.domain.clientes.ClienteRespository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	//Instancia de TokenService para validar los tokens
	@Autowired
	private TokenService tokenService;
	
	//Instancia de ClienteRepository para hacer consultas a la BD
	@Autowired
	private ClienteRespository clienteRepo;
	
	/**
	 * Filtro
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		//Obtenemos el token de la request
		String authHeader = request.getHeader("Authorization");
			
		if(authHeader != null) {
			//obtenemos el token puro, eliminando la palabra "Bearer " de la cadena
			String token = authHeader.replace("Bearer ", "");
			
			//validamos el token, si el token es valido subject deberia almacenar el correo del usuario
			//que hace la request, si el token es invalido subject deberia ser null
			String subject = tokenService.validateToken(token);
			
			//una vez validado el token, deberemos forzar un inicio de sesion en el sistema 	
			if(subject != null) {
				//Forzaremos el inicio de sesion
				
				//obtenemos un UserDetails de nuestra entidad de ClienteEntity
				UserDetails cliente = clienteRepo.findByCorreo(subject);
				
				//obtenemos un UsernamePasswordAuthenticationToken
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
				
				//Establecemos la autenticacion
				SecurityContextHolder
				.getContext()
				.setAuthentication(authentication);
				
			}
		}
		
		//continua con el flujo de la request
		filterChain.doFilter(request, response);
	}

}
