/**  
 * @Title:  TipoIdentificacionMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   18/10/2021 11:12:41 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.service.DestinoService;
import co.edu.usbcali.viajesusb.service.TipoIdentificacionService;

/**   
 * @ClassName:  TipoIdentificacionMapper   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   18/10/2021 11:12:41 p. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel = "spring")
public interface TipoIdentificacionMapper {

	public TipoIdentificacionDTO tipoIdentificacionToTipoIdentificacionDTO(TipoIdentificacion tipoIdentificacion);

    public TipoIdentificacion tipoIdentificacionDTOToTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO);

    public List<TipoIdentificacionDTO> listTipoIdentificacionToTipoIdentificacionDTO(List<TipoIdentificacion> lstTipoIdentificacion);

    public List<TipoIdentificacion> listTipoIdentificacionDTOToTipoIdentificacion(List<TipoIdentificacionDTO> lstTipoIdentificacionDTO);
	
}
