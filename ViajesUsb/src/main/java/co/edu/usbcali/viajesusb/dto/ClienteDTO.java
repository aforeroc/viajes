/**  
 * @Title:  TipoDestinoDTO.java   
 * @Package co.edu.usbcali.viajes.dto   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 8:10:56 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import lombok.Data;

/**   
 * @ClassName:  TipoDestinoDTO   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 8:10:56 p. m.      
 * @Copyright:  USB
 */
@Data
public class ClienteDTO implements Serializable {

	/**   
	   * @Fields serialVersionUID: TODO (what does this variable mean)   
	 */
	
	private static final long serialVersionUID = -3839483195774577896L;


	
	public Long idClie;
	public String numeroIdentificacion;
	public String primerApellido;
	public String segundoApellido;
	public String nombre;
	public String telefono1;
	public String telefono2;
	public String correo;
	public String sexo;
	public Date fechaNacimiento;
	public Date fechaCreacion;
	public Date fechaModificacion;
	public String usuCreador;
	public String usuModificador;
	public String estado;
	
	private Long idTiid;
    private String codigoTipoIdentificacion;
    private String nombreTipoIdentificacion;
    //private String estadoTipoIdentificacion;
	
	
	
	
	
	
	
	
	/**   
	 * @Title:  ClienteDTO  
	 * @Author: Alejandro Forero 
	   * @Description:TODO 
	 * @param:  @param numeroIdentificacion
	 * @param:  @param nombre
	 * @param:  @param estado  
	 * @throws   
	 */
	
	public ClienteDTO(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	public ClienteDTO() {
		
	}
	
	
	/**   
	 * @Title:  ClienteDTO  
	 * @Author: Alejandro Forero 
	   * @Description:TODO 
	 * @param:  @param tipoIdentificacion  
	 * @throws   
	 */
	



	
}
