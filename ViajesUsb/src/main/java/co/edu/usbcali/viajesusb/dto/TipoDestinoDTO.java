/**  
 * @Title:  tipoDestinoDTO.java   
 * @Package co.edu.usbcali.viajes.dto   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   28/09/2021 11:19:58 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;



import co.edu.usbcali.viajesusb.domain.Destino;
import lombok.Data;

/**   
 * @ClassName:  tipoDestinoDTO   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   28/09/2021 11:19:58 a. m.      
 * @Copyright:  USB
 */
@Data
public class TipoDestinoDTO implements Serializable{

	
	


	/**   
	   * @Fields serialVersionUID: TODO (what does this variable mean)   
	 */
	
	private static final long serialVersionUID = 661252494774102284L;
	
	
	
	
	
	
	
	
	private Long idTide;
	private String codigo;
	private String nombre;
	private String descripcion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	

	
}
