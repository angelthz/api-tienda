package com.athz.tienda.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Configuration: le dice a spring que esta clase es una clase de configuracion
//por lo que spring le da prioridad a su instanciacion dado que es una configuracion
//que sirve para modifcar el comportamiento de la aplicacion
//Ademas cada metodo declarado dentro de la clase tiene como proposito 
//ser un Bean de tal manera que dicho metodo pueda ser observado por el
//contexto de Spring e inyectado a aquellas clases que lo necesiten
@Configuration
//EnableWebSecurity: Debemos implementar el metodo securityFilterChain y mediante esta anotacion
//le indicamos a spring que dicho metodo esta siendo implementado para sobreescribir el metodo
//de autenticacion por defecto. Este metodo debe devolver un objeto de tipo HttpSecurityChain.
@EnableWebSecurity
public class SecurityConfigurations {
	//Definicioon de nuestro Bean
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//		return httpSecurity
//				.csrf().disable()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and().build();
		
		return httpSecurity
				.csrf( csrf->csrf.disable())
				.sessionManagement( session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}
	
	/**
	 * httpSecurity
	 * .csrf().disable() //deshabilita la seguridad en contra de la suplantacion de identidad en
	 * 					//en la configuracion Stateful, como nosotros vamos a implementar Stateles mediante
	 * 					//JWT, no es necesario ya que la protecion Via JsonWebToken ya nos protege de estos 
	 * 					//ataques
	 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //cambia el metodo de autenticacion de StateFUL a StateLESS
	.and().build(); //crea e instancia el objeto de la clase HttpSecurity
	 */
	
	/**
	 * Devolver un authtentication manager
	 * @throws Exception 
	 * 
	 */
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}
	
	
	
	/**
	 * Password encoder, se encarda de desencriptar/encriptar la contraseña del usuario 
	 * de texto plano a una contraseña en formato hash mediante el algoritmo BCrypt
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
