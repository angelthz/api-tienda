package com.athz.tienda.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
	
	@Autowired
	SecurityFilter securityFilter;
	
	/**
	 * Sobrescribe la configuracion por defecto de SpringSecurity, estableciendo un tipo de sesion Stateless
	 * @param httpSecurity
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity
				.csrf( csrf->csrf.disable() )
				.sessionManagement( session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
				.authorizeHttpRequests(
						authz -> authz
						.requestMatchers("/login")
						.permitAll()
						.anyRequest()
						.authenticated())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //definicion del filtro para que se ejecute nuestro security filter
								//agrega un filtro antes e implemente un usernamePaswordAhtenticationFilter
								//valida que el usuario haya iniciado sesion en el sistema
							
				.build();
	}
	
	/**
	 * Devuelve una instancia de AuthenticationManager
	 * @param authConfiguration
	 * @return
	 * @throws Exception
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
