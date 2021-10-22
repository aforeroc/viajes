/**  
 * @Title:  TipoDestinoMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   12/10/2021 11:32:43 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;

/**   
 * @ClassName:  TipoDestinoMapper   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   12/10/2021 11:32:43 a. m.      
 * @Copyright:  USB
 */

@Mapper(componentModel = "spring")
public interface TipoDestinoMapper {

	public TipoDestinoDTO tipoDestinoToTipoDestinoDTO(TipoDestino tipoDestino);
	
	public TipoDestino tipoDestinoDTOToTipoDestino(TipoDestinoDTO tipoDestinoDTO);
	
	public List<TipoDestinoDTO> listTipoDestinoToTipoDestinoDTO(List<TipoDestino> lsttipoDestino);
	public List<TipoDestinoDTO> listTipoDestinoDTOToTipoDestino(List<TipoDestinoDTO> lsttipoDestinoDTO);
	
	
}
