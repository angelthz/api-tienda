package com.athz.tienda.api.domain.ventas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<VentaEntity, Integer>{
	public List<VentaEntity> findByClienteId(Integer id);
}
