package com.athz.tienda.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athz.tienda.api.productos.AddProductoDTO;
import com.athz.tienda.api.productos.CategoriaEntity;
import com.athz.tienda.api.productos.CategoriaRepository;
import com.athz.tienda.api.productos.ProductoDTO;
import com.athz.tienda.api.productos.ProductoEntity;
import com.athz.tienda.api.productos.ProductoRepository;
import com.athz.tienda.api.productos.UpdateProductoDTO;

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
	public List<ProductoDTO> getProductos() {
		return productoRepo.findAll().stream().map(prod -> new ProductoDTO(prod)).toList();
	}
	
	/**
	 * Agrega un nuevo producto a la tabla productos
	 * @param datosProducto
	 */
	@PostMapping
	public void addProducto(@RequestBody AddProductoDTO datosProducto) {
		ProductoEntity prod = new ProductoEntity(datosProducto);
		productoRepo.save(prod);
	}
	
	/**
	 * Borra un producto por medio de su id
	 * @param id
	 */
	@DeleteMapping("/borrar/{id}")
	@Transactional
	public void deleteProducto(@PathVariable Integer id) {
		ProductoEntity prod = productoRepo.getReferenceById(id);
		productoRepo.delete(prod);
	}
	
	@PutMapping()
	@Transactional
	public void updateProducto(@RequestBody UpdateProductoDTO datosProducto) {
		ProductoEntity prod = productoRepo.getReferenceById(datosProducto.id());
		prod.update(datosProducto);
	}
}
