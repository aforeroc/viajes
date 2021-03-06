/**  
 * @Title:  ClienteMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   18/10/2021 7:09:16 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;

/**   
 * @ClassName:  ClienteMapper   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   18/10/2021 7:09:16 p. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel = "spring")
public interface ClienteMapper {

	
	

	@Mapping(source ="tipoIdentificacion.nombre", target="nombreTipoIdentificacion")
	@Mapping(source ="tipoIdentificacion.codigo", target="codigoTipoIdentificacion")
	//@Mapping(source ="tipoIdentificacion.estado", target="estadoTipoIdentificacion")
	@Mapping(source ="tipoIdentificacion.idTiid", target="idTiid")
	
    public ClienteDTO clienteToClienteDTO(Cliente cliente);
	
    public List<ClienteDTO> listClienteToListClienteDTO(List<Cliente> clientes);
    
    public List<ClienteDTO> listClienteToListClienteDTO(Page<Cliente> clientes);
    
    public List<ClienteDTO> listaClienteToListClienteDTO(List<ClienteDTO> clientes);

    
    
}
    
    
