package com.athz.tienda.api.domain.productos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository  extends JpaRepository<ProductoEntity, Integer>{
	List<ProductoEntity> findByCantidadGreaterThan(Integer limite);
}	
