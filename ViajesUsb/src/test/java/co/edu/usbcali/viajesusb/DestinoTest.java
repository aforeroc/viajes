/**  
 * @Title:  DestinoTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   3/09/2021 10:35:38 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.DestinoRepository;
import co.edu.usbcali.viajesusb.service.DestinoService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  DestinoTest   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   3/09/2021 10:35:38 p. m.      
 * @Copyright:  USB
 */
@SpringBootTest
@Rollback(false)
class DestinoTest {

	@Autowired
	private DestinoService destinoService;
	
	@Test
	@Transactional
	void debeConsultarDestinoPorTipoDestino() {
		
		List<Destino> lstDestino= null;
		
		try {
			
				
			lstDestino = destinoService.findByTipoDestinoCodigo("PLAYA");//55:30
			
			
			for (Destino destino : lstDestino) {
				System.out.println(destino.getCodigo()+"-"+destino.getNombre());
				//H1:M18
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	@Test
	@Transactional
	void debeConsultarDestinosPorEstadosPaginado() {
		
	
	Page<Destino> pageDestino= null;
	
	try {
		//primer numero es el numero de la pagina actual empezando desde 0
		//seundo la cantidad de items por pagina
		Pageable pageable= PageRequest.of(0, 2);
		pageDestino = destinoService.findByEstado("A",pageable);//para probar se pone en null
		
		for (Destino destino : pageDestino.getContent()) {
			System.out.println(destino.getCodigo()+"-"+destino.getNombre());
		
			
		}
		
		System.out.println(pageDestino.toString());
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
	@Test
	@Transactional
	void debeGuardarElDestinoSanAndres() {
		
		try {
			
	
		DestinoDTO destinoDTO = new DestinoDTO();
		
		destinoDTO.setAire(Constantes.SI);
		destinoDTO.setMar(Constantes.SI);
		destinoDTO.setTierra(Constantes.NO);
		
		destinoDTO.setNombre("San andrés");
		destinoDTO.setCodigo("SAND");
		destinoDTO.setDescripcion("San andrés islas");
		destinoDTO.setEstado(Constantes.ACTIVO);
		destinoDTO.setFechaCreacion(new Date());
		destinoDTO.setUsoCreador("CLOPEZ");
		
		destinoDTO.setCodigoTipoDestino("PLAYA");
		destinoDTO.setNombreTipoDestino("PLAYA Y MAR");
		
		destinoService.guardarDestino(destinoDTO);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	@Transactional
	void debeActualizarElDestinoSanAndres() {
		
		try {
			
	
		DestinoDTO destinoDTO = new DestinoDTO();
		
		destinoDTO.setIdDest(4L);
		destinoDTO.setAire(Constantes.SI);
		destinoDTO.setMar(Constantes.SI);
		destinoDTO.setTierra(Constantes.NO);
		
		destinoDTO.setNombre("San andrés");
		destinoDTO.setCodigo("SAND");
		destinoDTO.setDescripcion("San andrés islas");
		destinoDTO.setEstado(Constantes.ACTIVO);
		destinoDTO.setFechaCreacion(new Date());
		destinoDTO.setUsoCreador("CLOPEZ");
		
		destinoDTO.setCodigoTipoDestino("PLAYA");
		destinoDTO.setNombreTipoDestino("PLAYA Y MAR");
		
		destinoService.actualizarDestino(destinoDTO);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		 
	@Test
	@Transactional
	void debeEliminarElDestinoSanAndres() {

		try {

			DestinoDTO destinoDTO = new DestinoDTO();

			destinoDTO.setIdDest(7L);

			destinoService.eliminarDestino(destinoDTO.getIdDest());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
