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
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class TokenService {
	
	//Tomamos el valor de nuestro Secret del application.properties
	@Value("${api.security.secret}")
	private String apiSecret;
	
	/**
	 * Genera un JWT Token de autorizacion
	 * @param cliente : ClienteEntity
	 * @return String Un JWT Token
	 */
	public String generarToken(ClienteEntity cliente) {
		try {
			System.out.println(apiSecret);
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret); //algoritmo de encriptacion y secret para la validacion
		    String token = JWT.create()
		        .withIssuer("ventas api") //emisor del token
		        .withSubject(cliente.getCorreo()) //a quien va dirigido el token
		        .withClaim("id", cliente.getId())
		        .withExpiresAt(getTiempoDeExpiracion())
		        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
		    // Invalid Signing configuration / Couldn't convert Claims.
			throw new RuntimeException();
		}
	}
	
	/**
	 * Realiza la validacion de un JWT Token enviado en el header de las requests.
	 * @param token
	 * @return El nombre de usuario autenticado
	 */
	public String validateToken(String token) {
		DecodedJWT decodedJWT = null;
		
		
		if(token==null) {
			throw new RuntimeException("Token Nulo");
		}
		try {
		    	Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    	decodedJWT = JWT.require(algorithm)
		        // specify an specific claim validations
		        .withIssuer("ventas api")
		        // reusable verifier instance
		        .build()
		        .verify(token);
		    	
		    	decodedJWT.getSubject();
		        
		} catch (JWTVerificationException exception){
		    System.out.println(exception.getMessage());
		}
		
		if(decodedJWT.getSubject() == null)
			throw new RuntimeException("Verificacion del token invalida");
		
		return decodedJWT.getSubject();
	}
	
	
	/**
	 * Genera un tiempo de expiracion del token
	 * @return
	 */
	private Instant getTiempoDeExpiracion() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
	}
}
