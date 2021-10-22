/**  
 * @Title:  TipoIdentificacionDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   4/10/2021 7:15:42 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import co.edu.usbcali.viajesusb.domain.Cliente;
import lombok.Data;

/**   
 * @ClassName:  TipoIdentificacionDTO   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   4/10/2021 7:15:42 p. m.      
 * @Copyright:  USB
 */
@Data
public class TipoIdentificacionDTO implements Serializable {

	
	
	

	/**   
	   * @Fields serialVersionUID: TODO (what does this variable mean)   
	 */
	
	private static final long serialVersionUID = -279118676938618164L;
	
	private Long idTiid;
	private String codigo;
	private String nombre;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	

	
}
