/**  
 * @Title:  TipoDestinoService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   7/09/2021 11:51:04 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;

import java.sql.SQLException;
import java.util.List;

/**   
 * @ClassName:  TipoDestinoService   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   7/09/2021 11:51:04 a. m.      
 * @Copyright:  USB
 */

public interface TipoDestinoService {

	
	/**
	 * 
	 * @Title: findByCodigo   
	   * @Description: consultar un tipo de destino dado el codigo
	 * @param: @param codigo
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoDestino      
	 * @throws
	 */
	//1
	public TipoDestino findByCodigo(String codigo)throws SQLException;
	
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
	//2
	public TipoDestino findByCodigoAndEstado(String codigo,String estado) throws SQLException;
	
	/**
	 * 
	 * @Title: findByEstadoOrderByNombre   
	   * @Description: consulta  una lista de tipos destino por estado ordenados alfabeticamente
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<TipoDestino>      
	 * @throws
	 */
	//3
	public List<TipoDestino> findByEstadoOrderByNombre(String estado) throws SQLException;
	
	
	
	//
	public TipoDestino guardarTipoDestino (TipoDestinoDTO tipoDestinoDTO)throws SQLException;
	//
	public TipoDestino actualizarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws SQLException ;
	//
	public void eliminarTipoDestino(Long id)throws SQLException;
	
}



