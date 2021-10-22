/**  
 * @Title:  TipoIdentificacionService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   17/09/2021 5:49:00 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;

/**   
 * @ClassName:  TipoIdentificacionService   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   17/09/2021 5:49:00 p. m.      
 * @Copyright:  USB
 */

public interface TipoIdentificacionService {

	
	//1
	public List<TipoIdentificacion> findByEstadoOrderByNombre(String estado)throws SQLException ;
	
	//2
	public TipoIdentificacion findByCodigoAndEstado(String codigo,String estado)throws SQLException ;
	
	//3
	public TipoIdentificacion guardarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws SQLException ;

	
	//4
	public TipoIdentificacion actualizarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws SQLException ;

	//5
	public void eliminarTipoIdentificacion(Long id) throws SQLException ;

	
}
