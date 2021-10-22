/**  
 * @Title:  DestinoService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   17/09/2021 12:33:30 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;

/**   
 * @ClassName:  DestinoService   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   17/09/2021 12:33:30 a. m.      
 * @Copyright:  USB
 */

public interface DestinoService {

	
	
	/**
	 * 
	 * @Title: findByTipoDestino_codigo   
	   * @Description: consultar tipo destino por codigo
	 * @param: @param codigoTipoDestino
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Destino>      
	 * @throws
	 */
	
	public List <Destino>  findByTipoDestinoCodigo(String codigoTipoDestino) throws SQLException; 
	
	/**
	 * 
	 * @Title: findByEstado   
	   * @Description: consultar page destino por estado
	 * @param: @param estado
	 * @param: @param pageable
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Page<Destino>      
	 * @throws
	 */
	
	public Page<Destino> findByEstado (String estado, Pageable pageable ) throws SQLException; 
	
	
	
	/**
	 * 
	 * @Title: guardarDEstino   
	   * @Description: guardadr destino dado un DTO de destino
	 * @param: @param destinoDTO
	 * @param: @throws SQLException      
	 * @return: void      
	 * @throws
	 */
	//
	public Destino guardarDestino(DestinoDTO destinoDTO)throws SQLException;
	
	
	/**
	 * 
	 * @Title: ActualziarDestino   
	   * @Description: metodo para actualizar destino
	 * @param: @param destinoDTO
	 * @param: @throws SQLException      
	 * @return: void      
	 * @throws
	 */
	//
	public Destino actualizarDestino(DestinoDTO destinoDTO)throws SQLException;
	
	/**
	 * 
	 * @Title: eliminarDestino   
	   * @Description: metodo que elimina un destino
	 * @param: @param destinoDTO
	 * @param: @throws SQLException      
	 * @return: void      
	 * @throws
	 */
	//
	public void eliminarDestino (Long idDest)throws SQLException;
	
	
}
