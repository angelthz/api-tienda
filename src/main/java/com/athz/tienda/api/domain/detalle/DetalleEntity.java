package com.athz.tienda.api.domain.detalle;

import java.math.BigDecimal;

import com.athz.tienda.api.domain.productos.ProductoEntity;
import com.athz.tienda.api.domain.ventas.VentaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name="DetalleVenta")
@Table(name="detalle_ventas")
public class DetalleEntity {
	

	
	@EmbeddedId
	private DetalleKeys fk;
	
//	@EmbeddedId
//	private DetalleId id;
	private Integer cantidad;
	private BigDecimal importe;
	
	public DetalleEntity() {
		
	}


	public DetalleEntity(VentaEntity venta, ProductoEntity prod, Integer cantidad) {
		this.fk = new DetalleKeys(venta, prod);
		this.cantidad = cantidad;
		this.importe = prod.getPrecio().multiply(new BigDecimal(cantidad));
	}


	
	
	

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @return the fk
	 */
	public DetalleKeys getFk() {
		return fk;
	}

	/**
	 * @param fk the fk to set
	 */
	public void setFk(DetalleKeys fk) {
		this.fk = fk;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	
}
