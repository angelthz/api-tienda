package com.athz.tienda.api.ventas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.athz.tienda.api.clientes.ClienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity (name="Venta")
@Table(name = "ventas")
public class VentaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_venta")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_cliente")
	@JsonIgnore
	private ClienteEntity cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_pago")
	@JsonIgnore
	private PagoEntity pago;
	
	private LocalDate fecha;
	
	@Column(name="total_articulos")
	private Integer totalArticulos;
	private BigDecimal subtotal;
	private BigDecimal descuento;
	private BigDecimal iva;
	@Column(name="total_neto")
	private BigDecimal totalNeto;
	
	public VentaEntity() {
		
	}

	public VentaEntity(VentaDatosDTO datos) {
		
	}

	public VentaEntity(ClienteEntity cliente, PagoEntity pago) {
		this.cliente = cliente;
		this.pago = pago;
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
	 * @return the cliente
	 */
	public ClienteEntity getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the pago
	 */
	public PagoEntity getPago() {
		return pago;
	}

	/**
	 * @param pago the pago to set
	 */
	public void setPago(PagoEntity pago) {
		this.pago = pago;
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the totalArticulos
	 */
	public Integer getTotalArticulos() {
		return totalArticulos;
	}

	/**
	 * @param totalArticulos the totalArticulos to set
	 */
	public void setTotalArticulos(Integer totalArticulos) {
		this.totalArticulos = totalArticulos;
	}

	/**
	 * @return the subtotal
	 */
	public BigDecimal getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * @return the descuento
	 */
	public BigDecimal getDescuento() {
		return descuento;
	}

	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	/**
	 * @return the iva
	 */
	public BigDecimal getIva() {
		return iva;
	}

	/**
	 * @param iva the iva to set
	 */
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	/**
	 * @return the totalNeto
	 */
	public BigDecimal getTotalNeto() {
		return totalNeto;
	}

	/**
	 * @param totalNeto the totalNeto to set
	 */
	public void setTotalNeto(BigDecimal totalNeto) {
		this.totalNeto = totalNeto;
	}
	
	
}
