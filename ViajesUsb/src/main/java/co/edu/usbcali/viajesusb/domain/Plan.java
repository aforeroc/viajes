package co.edu.usbcali.viajesusb.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;



@Data
//@Entity
//@Table(name="plan")
public class Plan {
	
	public Long idPlan;
	
	
	public String codigo;
	
	
	public String nombre;
	
	
	public Long descripcionSolicitud;
	
	
	public Long cantidadPersonas;
	
	
	public Date fechaSolicitud;
	
	
	public Date fechaInicioViaje;
	
	
	public Date fechaFinViaje;
	
	
	///////public ???? valorTotal;
	
	
	public Date fechaCreacion;
	
	
	public Date fechaModificacion;
	
	
	public String usuCreador;
	
	
	public String usuModificador;
	
	
	public String estado;
	
	//faltan id_clie/id_usua
	
	

}
