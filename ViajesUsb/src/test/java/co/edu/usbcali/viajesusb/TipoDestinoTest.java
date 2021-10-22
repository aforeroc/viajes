/**  
 * @Title:  TipoDestinoTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   1/09/2021 9:52:12 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;



import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  TipoDestinoTest   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   1/09/2021 9:52:12 p. m.      
 * @Copyright:  USB
 */

@SpringBootTest
@Rollback(false)
class TipoDestinoTest {

	@Autowired
	private TipoDestinoService tipoDestinoService;
	
	@Test
	@Transactional
	void DebeConsultarUnTipoDeDestinoPorCodigo() {
		
		TipoDestino tipoDestino =null;
		try {
			tipoDestino=tipoDestinoService.findByCodigo("PLAYA");//playa no funciona
			System.out.println(tipoDestino.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		
		
	}	
	@Test
	@Transactional
	void DebeConsultarUnTipoDeDestinoPorCodigoEstado() {
		
		TipoDestino tipoDestino =null;
		try {
			tipoDestino=tipoDestinoService.findByCodigoAndEstado("PLAYA","A");//playa no funciona
			System.out.println(tipoDestino.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
	@Test
	@Transactional
	void DebeConsultarUnTiposDeDestinoPorEstadoOrdenadosAlfabeticamente() {
		
		List<TipoDestino> lstTipoDestino =null;
		
		try {
			lstTipoDestino=tipoDestinoService.findByEstadoOrderByNombre("A");//playa no funciona
			
			for (TipoDestino tipoDestino : lstTipoDestino) {
				System.out.println(tipoDestino.getNombre());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	@Transactional
	void guardarTipoDestinoGlam() {
		
		
		
		try {
			
			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();
			
			//tipoDestinoDTO.setIdTide(25L);
			tipoDestinoDTO.setCodigo("CAS");
			tipoDestinoDTO.setNombre("CASCADA");
			tipoDestinoDTO.setDescripcion("MUCHA AGUA");
			tipoDestinoDTO.setFechaCreacion(new Date());
			tipoDestinoDTO.setUsuCreador("AFORE");
			tipoDestinoDTO.setEstado(Constantes.ACTIVO);

			
			
			tipoDestinoService.guardarTipoDestino(tipoDestinoDTO);	
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	
	}
	@Test
	@Transactional
	void actualizarTipoDestinoGlam() {

		try {

			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();

			tipoDestinoDTO.setIdTide(15L);
			tipoDestinoDTO.setCodigo("CAS");
			tipoDestinoDTO.setNombre("CASCADA");
			tipoDestinoDTO.setDescripcion("MUCHA AGUA");
			tipoDestinoDTO.setFechaCreacion(new Date());
			tipoDestinoDTO.setUsuCreador("CLOPEZ");
			tipoDestinoDTO.setEstado(Constantes.ACTIVO);

			tipoDestinoService.actualizarTipoDestino(tipoDestinoDTO);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	
	
	@Test
	@Transactional
	void eliminarTipoDestinoGlam() {

		try {

			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();

			tipoDestinoDTO.setIdTide(13L);

			tipoDestinoService.eliminarTipoDestino(tipoDestinoDTO.getIdTide());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
	