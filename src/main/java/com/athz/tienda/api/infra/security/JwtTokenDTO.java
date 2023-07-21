package com.athz.tienda.api.infra.security;

/**
 * DTO para enviar un JWT Token despues de la autenticacion del usuario
 * @author Angel
 *
 */
public record JwtTokenDTO(String jwtToken) {

}
