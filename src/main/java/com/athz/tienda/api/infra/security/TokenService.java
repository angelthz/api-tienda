package com.athz.tienda.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.athz.tienda.api.domain.clientes.ClienteEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {
	
	@Value("${api.security.secret}")
	private String secret;
	
	/**
	 * Genera un JWT Token de autorizacion
	 * @param cliente : ClienteEntity
	 * @return String Un JWT Token
	 */
	public String generarToken(ClienteEntity cliente) {
		try {
			System.out.println(secret);
		    Algorithm algorithm = Algorithm.HMAC256(secret); //algoritmo de encriptacion y secret para la validacion
		    String token = JWT.create()
		        .withIssuer("ventas api") //emisor del token
		        .withSubject(cliente.getCorreo()) //a quien va dirigido el token
		        .withClaim("id", cliente.getId())
		        .withIssuedAt(getTiempoDeExpiracion())
		        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
		    // Invalid Signing configuration / Couldn't convert Claims.
			throw new RuntimeException();
		}
	}
	
	
	private Instant getTiempoDeExpiracion() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
	}
}
