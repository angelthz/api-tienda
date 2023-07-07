package com.athz.tienda.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.athz.tienda.api.domain.productos.AddProductoDTO;
import com.athz.tienda.api.domain.productos.CategoriaEntity;
import com.athz.tienda.api.domain.productos.CategoriaRepository;
import com.athz.tienda.api.domain.productos.ProductoDTO;
import com.athz.tienda.api.domain.productos.ProductoEntity;
import com.athz.tienda.api.domain.productos.ProductoRepository;
import com.athz.tienda.api.domain.productos.UpdateProductoDTO;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	/**
	 * Devuelve todos los productos de la tabla productos
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<ProductoDTO>> getProductos() {
		return ResponseEntity.ok(productoRepo.findByCantidadGreaterThan(0).stream().map(prod -> new ProductoDTO(prod)).toList());
//		return new ResponseEntity<List<ProductoDTO>>(
//				productoRepo.findAll().stream().map(prod->new ProductoDTO(prod)).toList(), HttpStatus.OK);
	}
	
	/**
	 * Agrega un nuevo producto a la tabla productos
	 * @param datosProducto
	 */
	@PostMapping
	public ResponseEntity<ProductoDTO> addProducto(@RequestBody AddProductoDTO datosProducto) {
		ProductoEntity prod = new ProductoEntity(datosProducto);
		productoRepo.save(prod);
		ProductoDTO prodResponse = new ProductoDTO(prod);
		URI url = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(prod.getId())
				.toUri();
		
		//URI url2 = UriComponentsBuilder.fromc.buildAndExpand(prod.getId()).toUri();
		
		return ResponseEntity.created(url).body(prodResponse);
	}
	
	/**
	 * Borra un producto por medio de su id
	 * @param id
	 */
	@DeleteMapping("/borrar/{id}")
	@Transactional
	public ResponseEntity deleteProducto(@PathVariable Integer id) {
		ProductoEntity prod = productoRepo.getReferenceById(id);
		productoRepo.delete(prod);
		return ResponseEntity.noContent().build();
	}
	
	
	/**
	 * Actualiza un producto
	 * @param datosProducto
	 */
	@PutMapping()
	@Transactional
	public ResponseEntity<ProductoDTO> updateProducto(@RequestBody UpdateProductoDTO datosProducto) {
		ProductoEntity prod = productoRepo.getReferenceById(datosProducto.id());
		prod.update(datosProducto);
		return ResponseEntity.ok(new ProductoDTO(prod));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> getProducto(@PathVariable Integer id){
		ProductoEntity prod = productoRepo.getReferenceById(id);
		return ResponseEntity.ok(new ProductoDTO(prod));
	}
}
