package co.edu.usbcali.viajesusb.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import lombok.Data;
import lombok.ToString;




@NamedNativeQueries({
    @NamedNativeQuery(name="Cliente.consultarClientesPorEstadoNoIdentificacionTipoIdentificacion", query="",resultSetMapping = 
    "consultarClientesPorEstadoNoIdentificacionTipoIdentificacion")
})


@SqlResultSetMappings({
    @SqlResultSetMapping(name="consultarClientesPorEstadoNoIdentificacionTipoIdentificacion",
            classes= { @ConstructorResult(targetClass = ClienteDTO.class,
                columns= {
                        @ColumnResult(name="nombre", type = String.class)
                })}),
})




@Data
@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_clie")
	public Long idClie;
	
	@Column(name="numero_identificacion",unique = true,nullable = false,length = 15)
	public String numeroIdentificacion;
	
	@Column(name="primer_apellido",nullable = false,length = 100)
	public String primerApellido;
	
	@Column(name="segundo_apellido",length = 100)
	public String segundoApellido;
	
	@Column(name="nombre",nullable = false,length = 100)
	public String nombre;
	
	@Column(name="telefono1",length = 15)
	public String telefono1;
	
	@Column(name="telefono2",length = 15)
	public String telefono2;
	
	@Column(name="correo",length = 100)
	public String correo;
	
	@Column(name="sexo",nullable = false,length = 1)
	public String sexo;
	
	@Column(name="fecha_nacimiento",nullable=false)
	public Date fechaNacimiento;
	
	@Column(name="fecha_creacion",nullable = false)
	public Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	public Date fechaModificacion;
	
	@Column(name="usu_creador",nullable = false,length = 10)
	public String usuCreador;
	
	@Column(name="usu_modificador",length = 10)
	public String usuModificador;
	
	@Column(name="estado",nullable = false,length = 1)
	public String estado;
	
	
	
	//relacion con tipo_identificacion
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tiid",nullable = false)
	public TipoIdentificacion tipoIdentificacion;


}
