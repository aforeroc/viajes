/**  
 * @Title:  ClienteRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 10:46:52 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;

/**   
 * @ClassName:  ClienteRepository   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 10:46:52 a. m.      
 * @Copyright:  USB
 */

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	
	
	/**
	 * 
	 * @Title: findByEstadoOrderBynumeroIdentificacion   
	   * @Description:2. Consultar todos los clientes por estado. Esta consulta debe ser paginada y ordenada de forma ascendente por numero de identificación
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Cliente>      
	 * @throws
	 */
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable)throws SQLException;
	
	
	
	
	
	/**
	 * 
	 * @Title: findByCorreoIgnoreCase   
	   * @Description: 3. Consultar un cliente por correo electrónico. Esta consulta debe ignorar las mayusculas y minúsculas 
	 * @param: @param correo
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Cliente      
	 * @throws
	 */
	
	public Cliente findByCorreoIgnoreCase (String correo)throws SQLException;
	
	
	/**
	 * 
	 * @Title: findByNumeroIdentificacionLike   
	   * @Description: 4. Consultar cliente por numero de identificación, usando LIKE
	 * @param: @param numeroIdentificacion
	 * @param: @return      
	 * @return: Cliente      
	 * @throws
	 */
	
	public Cliente findByNumeroIdentificacionLike(String numeroIdentificacion)throws SQLException;
	
	
	
	/**
	 * 
	 * @Title: findByNombreIgnoreCaseLike   
	   * @Description: Consultar cliente por nombre, ignorando Mayusculas y minúsculas, usando LIKE.
	 * @param: @param nombre
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Cliente      
	 * @throws
	 */
	
	public Cliente findByNombreIgnoreCaseLike(String nombre)throws SQLException;
	
	
	/**
	 * 
	 * @Title: findByFechaNacimientoBetween   
	   * @Description: Consultar clientes por rango de fecha (Se debe pasar dos fechas y traer los clientes cuya fecha de nacimiento se encuentre entre el rango ingresado). 
	 * @param: @param fecha1
	 * @param: @param fecha2
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Cliente      
	 * @throws
	 */
	
	public List<Cliente> findByFechaNacimientoBetween(Date fecha1,Date fecha2)throws SQLException;

	
	/**
	 * 
	 * @Title: countByEstado   
	   * @Description:8. Consultar total de clientes por estado.
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Cliente>      
	 * @throws
	 */
	public Long countByEstado (String estado)throws SQLException;
	
	
	
	/**
	 * 
	 * @Title: findByTipoIdentificacion   
	   * @Description: 9.Consultar clientes por tipo de identificación. Debe de ser paginado
	 * @param: @param codigoTipoIdentificacion
	 * @param: @param pageable
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Page<Cliente>      
	 * @throws
	 */
	
	public Page<Cliente> findByTipoIdentificacion_Codigo(String codigoTipoIdentificacion,Pageable pageable)throws SQLException;
	
	
	/**
	 * 
	 * @Title: FindByPrimerApellidoOrSegundoApellido   
	   * @Description: 10. Consultar clientes por apellido (tener en cuenta los campos primer y segundo apellido)
	 * @param: @param <Cliente>
	 * @param: @throws SQLException      
	 * @return: void      
	 * @throws
	 */
	
	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerapellido,String segundoapellido)throws SQLException;
	
	
	@Query(nativeQuery = true)
    public List<ClienteDTO> consultarClientesPorEstadoNoIdentificacionTipoIdentificacion(@Param("pEstado") String estado, @Param("pNumeroIdentificacion") 
                                                                                        String noIdentificacion, 
                                                                                        @Param("pNombre")String nombre) throws SQLException;
	
}


