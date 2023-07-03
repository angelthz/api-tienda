package com.athz.tienda.api.clientes;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRespository extends JpaRepository<ClienteEntity, Integer>{

	List<ClienteEntity> findByEstadoTrue();

}
