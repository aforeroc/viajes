/**  
 * @Title:  TipoIdentificacionTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 10:57:44 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.repository.TipoIdentificacionRepository;
import co.edu.usbcali.viajesusb.service.TipoIdentificacionService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  TipoIdentificacionTest   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 10:57:44 a. m.      
 * @Copyright:  USB
 */



@SpringBootTest
@Rollback(false)
class TipoIdentificacionTest {

	@Autowired
	private TipoIdentificacionService tipoIdentificacionService;
	
	
	@Test
	@Transactional
	void debeConsultarTiposDeIdentificacionPorEstadoOrdenadosAlfabeticamente() {
		
		List<TipoIdentificacion> lsttipoIdentificacion= null;
		
		
		try {
			lsttipoIdentificacion=tipoIdentificacionService.findByEstadoOrderByNombre("A");
			
			for (TipoIdentificacion tipoIdentificacion : lsttipoIdentificacion) {
				System.out.println(tipoIdentificacion.getNombre());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	@Transactional
	void DebeConsultarUnTipoDeDestinoPorCodigoEstado() {
		
		TipoIdentificacion tipoIdentificacion =null;
		try {
			tipoIdentificacion=tipoIdentificacionService.findByCodigoAndEstado("TI", "A");
			System.out.println(tipoIdentificacion.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	@Test
	@Transactional
	void guardarTipoIdentificacion() {
		
		
		
		try {
			
			TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
			
			//tipoDestinoDTO.setIdTide(25L);
			tipoIdentificacionDTO.setCodigo("PR");
			tipoIdentificacionDTO.setNombre("PRUEBA");
			tipoIdentificacionDTO.setFechaCreacion(new Date());
			tipoIdentificacionDTO.setUsuCreador("CLOPEZ");
			tipoIdentificacionDTO.setEstado(Constantes.ACTIVO);

			tipoIdentificacionService.guardarTipoIdentificacion(tipoIdentificacionDTO);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	@Transactional
	void actualizarTipoIdentificacion() {

		try {

			TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();

			tipoIdentificacionDTO.setIdTiid(6L);
			tipoIdentificacionDTO.setCodigo("Pi");
			tipoIdentificacionDTO.setNombre("PRUii");
			tipoIdentificacionDTO.setFechaCreacion(new Date());
			tipoIdentificacionDTO.setUsuCreador("CLOPEZ");
			tipoIdentificacionDTO.setEstado(Constantes.ACTIVO);

			tipoIdentificacionService.actualizarTipoIdentificacion(tipoIdentificacionDTO);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	@Test
	@Transactional
	public void eliminarTipoIdentificacion() throws SQLException {
		try {

			TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();

			tipoIdentificacionDTO.setIdTiid(6L);

			tipoIdentificacionService.eliminarTipoIdentificacion(tipoIdentificacionDTO.getIdTiid());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
