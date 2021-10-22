/**  
 * @Title:  ClienteRepository.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   17/09/2021 4:29:42 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;

/**   
 * @ClassName:  ClienteRepository   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   17/09/2021 4:29:42 p. m.      
 * @Copyright:  USB
 */

public interface ClienteService {

	
	
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable)throws SQLException;
	
	//2
	public Cliente findByCorreoIgnoreCase (String correo)throws SQLException;
	
	
	//3
	public Cliente findByNumeroIdentificacionLike(String numeroIdentificacion)throws SQLException;
	
	
	//4
	public Cliente findByNombreIgnoreCaseLike(String nombre)throws SQLException;
	
	
	//5
	public List<Cliente> findByFechaNacimientoBetween(Date fecha1,Date fecha2)throws SQLException;


	
	//6
	public Long countByEstado (String estado)throws SQLException;
	
	
	
	//7
	public Page<Cliente> findByTipoIdentificacion_Codigo(String codigoTipoIdentificacion,Pageable pageable)throws SQLException;

	
	
	//8
	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerapellido,String segundoapellido)throws SQLException;
	
	
	//9
	@Query(nativeQuery = true)
    public List<ClienteDTO> consultarClientesPorEstadoNoIdentificacionTipoIdentificacion(@Param("pEstado") String estado, @Param("pNumeroIdentificacion") 
                                                                                        String noIdentificacion, 
                                                                                        @Param("pNombre")String nombre) throws SQLException;
	
	//10
	public Cliente guardarCliente (ClienteDTO clienteDTO)throws SQLException;
	
	//11
	public Cliente actualizarCliente(ClienteDTO clienteDTO) throws SQLException;
	
	
	//12
	public void eliminarCliente(Long idClie) throws SQLException;
}
