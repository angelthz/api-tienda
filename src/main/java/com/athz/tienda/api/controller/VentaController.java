package com.athz.tienda.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athz.tienda.api.domain.clientes.ClienteEntity;
import com.athz.tienda.api.domain.clientes.ClienteRespository;
import com.athz.tienda.api.domain.detalle.DetalleEntity;
import com.athz.tienda.api.domain.ventas.PagoEntity;
import com.athz.tienda.api.domain.ventas.PagoRepository;
import com.athz.tienda.api.domain.ventas.VentaDTO;
import com.athz.tienda.api.domain.ventas.VentaDatosDTO;
import com.athz.tienda.api.domain.ventas.VentaEntity;
import com.athz.tienda.api.domain.ventas.VentaRepository;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	@Autowired
	ClienteRespository clienteRepo;
	@Autowired
	VentaRepository ventaRepo;
	@Autowired
	PagoRepository pagoRepo;
	
	/**
	 * Lista todas las ventas
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<VentaDTO>> getVentas(){
		List<VentaDTO> ventas = ventaRepo.findAll().stream().map(venta->new VentaDTO(venta)).toList();
		return ResponseEntity.ok().body(ventas);
	}
	
	/**
	 * Busca una venta por ID
	 * @param id
	 * @return
	 */
	@GetMapping("/buscar/{id}")
	public ResponseEntity<VentaDTO> buscarVenta (@PathVariable Integer id){
		VentaDTO venta = new VentaDTO(ventaRepo.getReferenceById(id));
		return ResponseEntity.ok().body(venta);
	}
	
	
	/**
	 * Lista las ventas por un ID de cliente
	 * @param id
	 * @return
	 */
	@GetMapping("/cliente/{id}")
	public ResponseEntity<List<VentaDTO>> getVentaPorCliente(@PathVariable Integer id){
		List<VentaDTO> ventas = ventaRepo.findByClienteId(id).stream().map(venta->new VentaDTO(venta)).toList();
		return ResponseEntity.ok().body(ventas);
	}
	
	/**
	 * Agrega una nueva venta
	 * @param datos
	 */
	@PostMapping("/agregar")
	public void addVenta(@RequestBody VentaDatosDTO datos) {
		ClienteEntity cliente = clienteRepo.getReferenceById(datos.id_cliente());
		PagoEntity pago = pagoRepo.getReferenceById(datos.id_pago());
		
		ventaRepo.save(new VentaEntity(cliente, pago));
	}
	
	
	/**
	 * Elimina una venta
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity deleteVenta(@PathVariable Integer id) {
		VentaEntity venta = ventaRepo.getReferenceById(id);
		ventaRepo.delete(venta);
		return ResponseEntity.noContent().build();
	}
	
	
}
