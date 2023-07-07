package com.athz.tienda.api.infra;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ManejadorErrores {
	
	/**
	 * Responde al cliente en caso de que solicite un recurso no disponible o que 
	 * no exista.
	 * @return
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity error404() {
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Responde al cliente cuando en el body envia datos faltantes.
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity error400EmptyFields(MethodArgumentNotValidException e) {
		List<EmptyField> errores = e.getFieldErrors().stream().map(err -> new EmptyField(err)).toList();
		return ResponseEntity.badRequest().body(errores);
	}
	
	/**
	 * Responde al cliente cuando introduce un mismo dato en campos unicos
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity uniqueFieldError(SQLIntegrityConstraintViolationException e) {
		return ResponseEntity.badRequest().body(new DuplicateKey(e));
	}
	
	
	//Records
	
	public record DuplicateKey(String exceptionName, String message) {
		public DuplicateKey(SQLIntegrityConstraintViolationException e) {
			this(e.getClass().getSimpleName(), e.getMessage());
		}
	}
	
	public record EmptyField(String field, String message) {
		public EmptyField(FieldError e) {
			this(e.getField(), e.getDefaultMessage());
		}
	}
}
