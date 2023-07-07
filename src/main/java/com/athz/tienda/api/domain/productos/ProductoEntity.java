package com.athz.tienda.api.domain.productos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name="Producto")
@Table(name="productos")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_producto")
	private Integer id;
	private String nombre;
	private BigDecimal precio;
	private Integer cantidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_categoria")
	@JsonIgnore
	private CategoriaEntity categoria;
	
	public ProductoEntity() {
		
	}

	public ProductoEntity(AddProductoDTO datosProducto) {
		this.nombre = datosProducto.nombre();
		this.precio = datosProducto.precio();
		this.cantidad = datosProducto.cantidad();
		this.categoria = new CategoriaEntity(datosProducto.categoria());
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the precio
	 */
	public BigDecimal getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the categoria
	 */
	public CategoriaEntity getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public void update(UpdateProductoDTO datosProducto) {
		this.nombre = datosProducto.nombre();
		this.precio = datosProducto.precio();
		this.cantidad = datosProducto.cantidad();
		this.categoria = new CategoriaEntity(datosProducto.categoria());
	}
	

	
	public Integer productoCantidad(Integer cantidad) {
		this.cantidad = this.cantidad-cantidad;
		return cantidad;
	}

	public void updateAddCantidad(Integer cantidad) {
		this.cantidad = this.cantidad + cantidad;
	}

	public void updateRestCantidad(Integer cantidad) {
		this.cantidad = this.cantidad - cantidad;
	}

	
	
}	
