package co.edu.usbcali.viajesusb.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usua")
	public Long idUsua;
	
	@Column(name="login",nullable = true)
	public String login;
	
	@Column(name="password")
	public String password;
	
	@Column(name="nombre")
	public String nombre;
	
	@Column(name="identificacion",unique = true)
	public String identificacion;
	
	@Column(name="fecha_creacion")
	public Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	public Date fechaModificacion;
	
	@Column(name="usu_creador")
	public String usuCreador;
	
	@Column(name="usu_modificador")
	public String usuModificador;
	
	@Column(name="estado")
	public String estado;
	
	
	
	
	
	

}
