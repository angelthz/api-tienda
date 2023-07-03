package com.athz.tienda.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athz.tienda.api.clientes.ClienteEntity;
import com.athz.tienda.api.clientes.ClienteRespository;
import com.athz.tienda.api.ventas.PagoEntity;
import com.athz.tienda.api.ventas.PagoRepository;
import com.athz.tienda.api.ventas.VentaDatosDTO;
import com.athz.tienda.api.ventas.VentaEntity;
import com.athz.tienda.api.ventas.VentaRepository;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	@Autowired
	ClienteRespository clienteRepo;
	@Autowired
	VentaRepository ventaRepo;
	@Autowired
	PagoRepository pagoRepo;
	
	@GetMapping
	public List<VentaEntity> getVentas(){
		return ventaRepo.findAll();
	}
	
	@PostMapping("/agregar")
	public void addVenta(@RequestBody VentaDatosDTO datos) {
		ClienteEntity cliente = clienteRepo.getReferenceById(datos.id_cliente());
		PagoEntity pago = pagoRepo.getReferenceById(datos.id_pago());
		ventaRepo.save(new VentaEntity(cliente, pago));
	}
}
