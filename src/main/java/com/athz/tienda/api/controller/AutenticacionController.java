package com.athz.tienda.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athz.tienda.api.domain.clientes.ClienteEntity;
import com.athz.tienda.api.domain.clientes.DatosAutenticacionDTO;
import com.athz.tienda.api.infra.security.JwtTokenDTO;
import com.athz.tienda.api.infra.security.TokenService;

import ch.qos.logback.core.subst.Token;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
	
	/*
	 * Authenticacation manager
	 * Clase que dispara el servicio de autenticacion 
	 */
	@Autowired
	private AuthenticationManager authManager;
	
	/**
	 * Clase (Servicio) que genera los JWT Token con la libreria Auth0 
	 * y que nosotros hemos declarado
	 */
	@Autowired
	private TokenService tokenService; 
	
	
	/**
	 * Endpoint Login. Autentica el usuario y devulve in JWT Token
	 * @param datosUsuario
	 * @return
	 */
	@PostMapping
	public ResponseEntity autenticarUsuario(@RequestBody DatosAutenticacionDTO datosUsuario) {
		Authentication authToken = new UsernamePasswordAuthenticationToken(datosUsuario.correo(), datosUsuario.password());
		Authentication authUser = authManager.authenticate(authToken);
		String jwtToken = tokenService.generarToken((ClienteEntity) authUser.getPrincipal());
		return ResponseEntity.ok(new JwtTokenDTO(jwtToken));
	}
}
