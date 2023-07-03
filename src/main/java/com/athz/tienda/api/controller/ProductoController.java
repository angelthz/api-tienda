package com.athz.tienda.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athz.tienda.api.productos.ProductoDTO;
import com.athz.tienda.api.productos.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoRepository productoRepo;
	
	@GetMapping
	public List<ProductoDTO> getProductos() {
		return productoRepo.findAll().stream().map(prod -> new ProductoDTO(prod)).toList();
	}
}
