package co.edu.usbcali.viajesusb.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
//@Entity
//@Table(name="detalle_plan")
public class DetallePlan {

	
	public Long idDepl;
	
	
	public String alimentacion;
	
	
	public String hospedaje;
	
	
	public String transporte;
	
	
	public String traslados;
	
	
	//no se como numeric public ????? valor;
	
	
	public Long cantidadNoches;
	
	
	public Long cantidadDias;
	
	
	public Date fechaCreacion;
	
	
	public Date fechaModificacon;
	
	
	public String usuCreador;
	
	
	public String usuModificador;
	
	
	public String estado;
	
	//faltan id_plan/id_dest
	
}
