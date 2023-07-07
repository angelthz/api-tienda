package com.athz.tienda.api.domain.detalle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleRepository extends JpaRepository<DetalleEntity, DetalleKeys> {
	
	public List<DetalleEntity> findByFkVentaId(Integer id);
	//public List<DetalleEntity> findByIdVenta(Integer id);
	
}
