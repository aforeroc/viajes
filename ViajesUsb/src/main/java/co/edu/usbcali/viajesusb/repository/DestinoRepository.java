/**  
 * @Title:  DestinoRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   1/09/2021 7:49:18 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.SQLException;
import java.util.List;

import co.edu.usbcali.viajesusb.domain.Destino;

/**   
 * @ClassName:  DestinoRepository   
  * @Description: repository para destino   
 * @author: Alejandro Forero     
 * @date:   1/09/2021 7:49:18 p. m.      
 * @Copyright:  USB
 */

public interface DestinoRepository extends JpaRepository<Destino, Long> {

	/**
	 * 
	 * @Title: findByTipoDestino_codigo   
	   * @Description: todos los destinos que pertenecen a un tipo de destino el list es porque son va a enviar varios
	 * @param: @param codigoTipoDestino
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Destino>      
	 * @throws
	 */
	
	public List <Destino>  findByTipoDestino_codigo(String codigoTipoDestino) throws SQLException; 
	
	/**
	 * 
	 * @Title: findByEstado   
	   * @Description: retorna una pagina de la lista de destinos por estado
	 * @param: @param estado
	 * @param: @param pageable
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Page<Destino>      
	 * @throws
	 */
	
	public Page<Destino> findByEstado (String estado, Pageable pageable ) throws SQLException; 
	
}
