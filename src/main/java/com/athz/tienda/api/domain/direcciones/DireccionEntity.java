package com.athz.tienda.api.domain.direcciones;

import com.athz.tienda.api.domain.clientes.ClienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Direccion")
@Table(name="direcciones")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DireccionEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id_direccion")
	private Integer id;
	private String estado;
	private String municipio;
	private String colonia;
	private String calle;
	private Integer numero;
	private String cp;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private ClienteEntity cliente;
	
	
	public DireccionEntity() {
		
	}
	
	
	
	
	/**
	 * @param id
	 * @param estado
	 * @param municipio
	 * @param colonia
	 * @param calle
	 * @param numero
	 * @param cp
	 * @param cliente
	 */
	public DireccionEntity(Integer id, String estado, String municipio, String colonia, String calle, Integer numero,
			String cp, ClienteEntity cliente) {
		this.id = id;
		this.estado = estado;
		this.municipio = municipio;
		this.colonia = colonia;
		this.calle = calle;
		this.numero = numero;
		this.cp = cp;
		this.cliente = cliente;
	}




	public DireccionEntity(DireccionDTO direccion) {
		this.estado = direccion.estado();
		this.municipio = direccion.municipio();
		this.colonia = direccion.colonia();
		this.calle = direccion.calle();
		this.numero = direccion.numero();
		this.cp = direccion.cp();
	}
	
	public DireccionEntity(DireccionDTO direccion, ClienteEntity cliente) {
		this.cliente = cliente;
		this.estado = direccion.estado();
		this.municipio = direccion.municipio();
		this.colonia = direccion.colonia();
		this.calle = direccion.calle();
		this.numero = direccion.numero();
		this.cp = direccion.cp();
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}




	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}




	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}




	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}




	/**
	 * @return the colonia
	 */
	public String getColonia() {
		return colonia;
	}




	/**
	 * @param colonia the colonia to set
	 */
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}




	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}




	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}




	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}




	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}




	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}




	/**
	 * @param cp the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
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




	public void update(DireccionUpdateDTO direccion) {
		this.estado = direccion.estado();
		this.municipio = direccion.municipio();
		this.colonia = direccion.colonia();
		this.calle = direccion.calle();
		this.numero = direccion.numero();
		this.cp = direccion.cp();
	}
	
	
	
}
