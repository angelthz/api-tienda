package com.athz.tienda.api.controller;

import java.net.URI;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.athz.tienda.api.domain.detalle.DetalleDTO;
import com.athz.tienda.api.domain.detalle.DetalleEntity;
import com.athz.tienda.api.domain.detalle.DetalleKeys;
import com.athz.tienda.api.domain.detalle.DetalleRepository;
import com.athz.tienda.api.domain.productos.ProductoEntity;
import com.athz.tienda.api.domain.productos.ProductoRepository;
import com.athz.tienda.api.domain.ventas.VentaDTO;
import com.athz.tienda.api.domain.ventas.VentaEntity;
import com.athz.tienda.api.domain.ventas.VentaRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/detalles")
public class DetalleController {
	@Autowired
	private DetalleRepository detalleRep;
	@Autowired
	private VentaRepository ventaRep;
	@Autowired
	private ProductoRepository productoRep;
	
	
	/**
	 * Devuelve todos los detalles de todas las ventas
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<DetalleDTO>> getAllVentas(){
		List<DetalleDTO> ventas = detalleRep.findAll().stream().map(detalle->new DetalleDTO(detalle)).toList();
		if(ventas.size()>0)
			return ResponseEntity.ok().body(ventas);
		else
			return ResponseEntity.noContent().build();
	}
	
	
	/**
	 * Devuelve todos los detalles de una venta relacionados a un ID de venta
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<List<DetalleDTO> > getDetalleVenta(@PathVariable Integer id) {
		List<DetalleDTO> detalles = detalleRep.findByFkVentaId(id).stream().map(detalle->new DetalleDTO(detalle)).toList();
		if(detalles.size()>0)
			return ResponseEntity.ok(detalles);
		else
			return ResponseEntity.notFound().build();
	}
	

	/**
	 * Agrega un producto al detalle-venta y actuliza la venta
	 * @param datosProducto
	 * @return
	 */
	@PostMapping
	@Transactional
	public ResponseEntity addProducto(@RequestBody DetalleDTO datosDetalle) {
		VentaEntity venta = ventaRep.getReferenceById(datosDetalle.idVenta());
		ProductoEntity prod = productoRep.getReferenceById(datosDetalle.idProducto());
		DetalleEntity detalle = new DetalleEntity(venta, prod, datosDetalle.cantidad());
		
		venta.updateVentaAddProduct(detalle);
		prod.updateRestCantidad(detalle.getCantidad());
		detalleRep.save(detalle);
		
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Elimina un producto del detalle-venta y actualiza la venta
	 * @param datosDetalle
	 */
	@Transactional
	@DeleteMapping
	public void deleteProductoDetalle(@RequestBody DetalleDTO datosDetalle) {
		VentaEntity venta = ventaRep.getReferenceById(datosDetalle.idVenta());
		ProductoEntity prod = productoRep.getReferenceById(datosDetalle.idProducto());
		DetalleEntity detalle = detalleRep.getReferenceById(new DetalleKeys(venta, prod));
		
		venta.updateVentaRemoveProduct(detalle);
		prod.updateAddCantidad(detalle.getCantidad());
		
		detalleRep.delete(detalle);
		
	}
	
}
