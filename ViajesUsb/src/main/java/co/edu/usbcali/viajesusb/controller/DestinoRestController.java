/**  
 * @Title:  DestinoRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   12/10/2021 10:33:13 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.mapper.DestinoMapper;
import co.edu.usbcali.viajesusb.service.DestinoService;

/**
 * @ClassName: DestinoRestController
 * @Description: TODO
 * @author: Alejandro Forero
 * @date: 12/10/2021 10:33:13 a. m.
 * @Copyright: USB
 */
@RestController
@RequestMapping("/api/destino")
public class DestinoRestController {

	@Autowired
	private DestinoService destinoService;
	@Autowired
	private DestinoMapper destinoMapper;

	@PostMapping("/guardarDestino")
	public ResponseEntity<DestinoDTO> guardarDestino(@RequestBody DestinoDTO destinoDTO) {

		try {

			Destino destino = destinoService.guardarDestino(destinoDTO);
			return ResponseEntity.ok(destinoMapper.destinoToDestinoDTO(destino));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/actualizarDestino")
	public ResponseEntity<?> actualizarCliente(@RequestBody DestinoDTO destinoDTO) {

		try {
			Destino destino = destinoService.actualizarDestino(destinoDTO);
			return ResponseEntity.ok(destinoMapper.destinoToDestinoDTO(destino));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/eliminarTipoDestino/{id}")
	public ResponseEntity<?> eliminarDestino(@PathVariable("id") Long id) {

		try {
			destinoService.eliminarDestino(id);
			return ResponseEntity.ok("Se elimino satisfactoriamente ");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
