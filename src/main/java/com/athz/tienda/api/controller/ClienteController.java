package com.athz.tienda.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athz.tienda.api.domain.clientes.ClienteDTO;
import com.athz.tienda.api.domain.clientes.ClienteEntity;
import com.athz.tienda.api.domain.clientes.ClienteRespository;
import com.athz.tienda.api.domain.clientes.ClienteResultDTO;
import com.athz.tienda.api.domain.clientes.ClienteUpdateDTO;
import com.athz.tienda.api.domain.direcciones.DireccionAddDTO;
import com.athz.tienda.api.domain.ventas.PagoRepository;
import com.athz.tienda.api.domain.ventas.VentaEntity;
import com.athz.tienda.api.domain.ventas.VentaRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	//repositorio
	@Autowired
	private ClienteRespository clienteRepo;
	

	
	/**
	 * Agrega un nuevo Cliente
	 * @param datosRegistroCliente
	 */
	@PostMapping
	public void saveCliente(@RequestBody @Valid ClienteDTO datosRegistroCliente) {
		clienteRepo.save(new ClienteEntity(datosRegistroCliente));
	}
	
	/**
	 * Lista todos los clientes
	 * @return
	 */
	@GetMapping
	public Page<ClienteResultDTO> getAllClientes(@PageableDefault(size = 5, sort = "id", direction =  Direction.ASC) Pageable paginationParams){
		//return clienteRepo.findAll().stream().map( cliente -> new ClienteResultDTO(cliente) ).toList();
		//return clienteRepo.findByEstadoTrue().stream().map( cliente -> new ClienteResultDTO(cliente) ).toList();
		return clienteRepo.findAll(paginationParams).map( cliente -> new ClienteResultDTO(cliente) );

	}
	
	/**
	 * Actualiza un cliente
	 * @param cliente
	 */
	@Transactional
	@PutMapping
	public void updateCliente(@RequestBody ClienteUpdateDTO cliente) {
		ClienteEntity clienteEn = clienteRepo.getReferenceById(cliente.id());
		clienteEn.update(cliente);
		
	}
	
	/**
	 * Delete Fisico de un cliente
	 * @param id
	 */
	@Transactional
	@DeleteMapping("/borrar/{id}")
	public void deleteCliente(@PathVariable Integer id) {
		ClienteEntity clienteEm = clienteRepo.getReferenceById(id);
		clienteRepo.delete(clienteEm);
	}
	
	/**
	 * Delete logico de un cliente
	 */
	@Transactional
	@DeleteMapping("/desactivar/{id}")
	public void disableCliente(@PathVariable Integer id) {
		ClienteEntity clienteEm = clienteRepo.getReferenceById(id);
		clienteEm.disable();
	}
	
	/**
	 * Agregar una Direccion a un Cliente
	 * @param datos
	 */
	@Transactional
	@PostMapping("/agregar-direccion")
	public void addDireccion(@RequestBody DireccionAddDTO datos) {
		ClienteEntity clienteEm = clienteRepo.getReferenceById(datos.idCliente());
		clienteEm.addDirecciones(datos);
	}


}
