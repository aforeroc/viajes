/**  
 * @Title:  TipoIdentificacionRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 10:46:39 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.SQLException;
import java.util.List;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;

/**   
 * @ClassName:  TipoIdentificacionRepository   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 10:46:39 a. m.      
 * @Copyright:  USB
 */

public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Long> {

	
	
	
	/**
	 * 
	 * @Title: findByEstado   
	   * @Description: 1.Consultar todos los tipo de identificación por estado ordenados alfabeticamente 
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<TipoIdentificacion>      
	 * @throws
	 */
	public List<TipoIdentificacion> findByEstadoOrderByNombre(String estado)throws SQLException ;
	
	
	/**
	 * 
	 * @Title: findByCodigoAndEstado   
	   * @Description: Consultar tipo de identificación por código y estado. 
	 * @param: @param codigo
	 * @param: @param estado
	 * @param: @return      
	 * @return: TipoIdentificacion      
	 * @throws
	 */
	
	public TipoIdentificacion findByCodigoAndEstado(String codigo,String estado)throws SQLException ;
	
	
	
	
	
	
}
