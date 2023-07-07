package com.athz.tienda.api.domain.clientes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.athz.tienda.api.domain.direcciones.DireccionAddDTO;
import com.athz.tienda.api.domain.direcciones.DireccionDTO;
import com.athz.tienda.api.domain.direcciones.DireccionEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Cliente")
@Table(name="clientes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClienteEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id_cliente")
	private Integer id;
	private String nombre;
	private String paterno;
	private String materno;
	private String correo;
	private String pass;
	private LocalDate fecha_nacim;
	@Enumerated(EnumType.STRING)
	private Genero genero;
	private String telefono;
	private boolean estado;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<DireccionEntity> direcciones;
	
	public ClienteEntity() {
		
	}
	
	
	
	/**
	 * @param id
	 * @param nombre
	 * @param paterno
	 * @param materno
	 * @param correo
	 * @param pass
	 * @param fecha_nacim
	 * @param genero
	 * @param telefono
	 * @param direcciones
	 */
	public ClienteEntity(Integer id, String nombre, String paterno, String materno, String correo, String pass,
			LocalDate fecha_nacim, Genero genero, String telefono, List<DireccionEntity> direcciones) {
		this.id = id;
		this.nombre = nombre;
		this.paterno = paterno;
		this.materno = materno;
		this.correo = correo;
		this.pass = pass;
		this.fecha_nacim = fecha_nacim;
		this.genero = genero;
		this.telefono = telefono;
		this.direcciones = direcciones;
	}



	public ClienteEntity(ClienteDTO cliente) {
		this.nombre = cliente.nombre();
		this.paterno = cliente.paterno();
		this.materno = cliente.materno();
		this.correo = cliente.correo();
		this.pass = cliente.password();
		this.fecha_nacim = cliente.fecha_nacim();
		this.genero = cliente.genero();
		this.telefono = cliente.telefono();
		this.estado = true;
		this.direcciones = new ArrayList<>();
	
		cliente.direcciones().forEach( dir -> {
			this.direcciones.add(new DireccionEntity(dir, this));
		});
		
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
	 * @return the paterno
	 */
	public String getPaterno() {
		return paterno;
	}



	/**
	 * @param paterno the paterno to set
	 */
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}



	/**
	 * @return the materno
	 */
	public String getMaterno() {
		return materno;
	}



	/**
	 * @param materno the materno to set
	 */
	public void setMaterno(String materno) {
		this.materno = materno;
	}



	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}



	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}



	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}



	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}



	/**
	 * @return the fecha_nacim
	 */
	public LocalDate getFecha_nacim() {
		return fecha_nacim;
	}



	/**
	 * @param fecha_nacim the fecha_nacim to set
	 */
	public void setFecha_nacim(LocalDate fecha_nacim) {
		this.fecha_nacim = fecha_nacim;
	}



	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}



	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}



	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}



	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	/**
	 * @return the direcciones
	 */
	public List<DireccionEntity> getDirecciones() {
		return direcciones;
	}



	/**
	 * @param direcciones the direcciones to set
	 */
	public void setDirecciones(List<DireccionEntity> direcciones) {
		this.direcciones = direcciones;
		//this.direcciones.forEach(direccion -> direccion.setCliente(this));
	}

	

	/**
	 * @return the estado
	 */
	public boolean getEstado() {
		return estado;
	}



	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}



	public void update(ClienteUpdateDTO cliente) {
		if(cliente.nombre() != null)
			this.nombre = cliente.nombre();
		if(cliente.paterno() != null)
			this.paterno = cliente.paterno();
		if(cliente.materno() != null)
			this.materno = cliente.materno();
		if(cliente.telefono() != null)
			this.telefono = cliente.telefono();
		if(cliente.direcciones() != null) {
			cliente.direcciones().forEach(dirUpdate ->{
				DireccionEntity dirEm = this.direcciones
						.stream()
						.filter(dir -> dir.getId() == dirUpdate.id())
						.findAny()
						.orElse(null);
				
				dirEm.update(dirUpdate);

			});
		}
		
	}



	public void addDirecciones(DireccionAddDTO direccionesDTO) {
		direccionesDTO.direcciones().forEach( dir -> {
			this.direcciones.add(new DireccionEntity(dir, this));
		});
	}



	public void disable() {
		this.estado = false;
	}
	
	
}
