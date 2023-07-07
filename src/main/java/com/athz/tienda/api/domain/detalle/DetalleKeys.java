package com.athz.tienda.api.domain.detalle;

import java.util.List;

import com.athz.tienda.api.domain.productos.ProductoEntity;
import com.athz.tienda.api.domain.ventas.VentaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Embeddable
public class DetalleKeys {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_venta")
	private VentaEntity venta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_producto")
	private ProductoEntity producto;
	
	
	public DetalleKeys() {
	
	}
	
	public DetalleKeys(VentaEntity venta, ProductoEntity prod) {
		this.venta = venta;
		this.producto = prod;
	}

	/**
	 * @return the venta
	 */
	public VentaEntity getVenta() {
		return venta;
	}
	/**
	 * @param venta the venta to set
	 */
	public void setVenta(VentaEntity venta) {
		this.venta = venta;
	}
	/**
	 * @return the producto
	 */
	public ProductoEntity getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
	
	
}
