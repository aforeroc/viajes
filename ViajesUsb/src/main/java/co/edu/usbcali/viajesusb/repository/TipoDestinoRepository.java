/**  
 * @Title:  TipoDestinoRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   1/09/2021 7:53:33 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.SQLException;
import java.util.List;

import co.edu.usbcali.viajesusb.domain.TipoDestino;

/**   
 * @ClassName:  TipoDestinoRepository   
  * @Description: repository para tipo destino
 * @author: Alejandro Forero     
 * @date:   1/09/2021 7:53:33 p. m.      
 * @Copyright:  USB
 */

public interface TipoDestinoRepository extends JpaRepository<TipoDestino, Long>{
	
	
	/**
	 * 
	 * @Title: findByCodigo   
	   * @Description: consulta un tipo destino por codigo
	 * @param: @param codigo
	 * @param: @return      
	 * @return: TipoDestino      
	 * @throws
	 */
	public TipoDestino findByCodigo(String codigo) throws SQLException; 
	
	/**
	 * 
	 * @Title: findByCodigoAndEstado   
	   * @Description: consulta tipo destino por codigo y estado 
	 * @param: @param codigo
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoDestino      
	 * @throws
	 */
	public TipoDestino findByCodigoAndEstado(String codigo,String estado) throws SQLException;

	
	/**
	 * 
	 * @Title: findByEstado   
	   * @Description: consultar tipodestino por estado
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<TipoDestino>      
	 * @throws
	 */
	public List<TipoDestino> findByEstadoOrderByNombre(String estado) throws SQLException;
}
