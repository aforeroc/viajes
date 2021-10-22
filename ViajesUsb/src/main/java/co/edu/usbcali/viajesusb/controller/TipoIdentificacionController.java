/**  
 * @Title:  TipoIdentificacionController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   18/10/2021 11:11:30 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.mapper.TipoIdentificacionMapper;
import co.edu.usbcali.viajesusb.service.TipoIdentificacionService;

/**
 * @ClassName: TipoIdentificacionController
 * @Description: TODO
 * @author: Alejandro Forero
 * @date: 18/10/2021 11:11:30 p. m.
 * @Copyright: USB
 */
@RestController
@RequestMapping("/api/tipoIdentificacion")
public class TipoIdentificacionController {

	@Autowired
	TipoIdentificacionService tipoIdentificacionService;
	@Autowired
	TipoIdentificacionMapper tipoIdentificacionMapper;

	// 1
	@GetMapping("/getTiposIdentificacion")
	public ResponseEntity<List<TipoIdentificacionDTO>> findByEstadoOrderByNombreAsc(
			@RequestParam("estado") String estado) {

		try {
			List<TipoIdentificacion> lstTipoIdentificacion = tipoIdentificacionService
					.findByEstadoOrderByNombre(estado);
			return ResponseEntity.ok().body(
					tipoIdentificacionMapper.listTipoIdentificacionToTipoIdentificacionDTO(lstTipoIdentificacion));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// 2
	@GetMapping("/getTipoIdentificacion")
	public ResponseEntity<TipoIdentificacionDTO> buscarTipoIdentificacion(@RequestParam("codigo") String codigo,
			@RequestParam("estado") String estado) {

		try {
			TipoIdentificacion tipoIdentificacion = tipoIdentificacionService.findByCodigoAndEstado(codigo, estado);
			return ResponseEntity.ok()
					.body(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	// 3
	@PostMapping("/guardarTipoIdentificacion")
	public ResponseEntity<TipoIdentificacionDTO> guardarTipoIdentificacion(
			@RequestBody TipoIdentificacionDTO tipoIdentificacionDTO) {
		try {
			TipoIdentificacion tipoIdentificacion = tipoIdentificacionService
					.guardarTipoIdentificacion(tipoIdentificacionDTO);
			return ResponseEntity
					.ok(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 4
	@PutMapping("/actualizarTipoIdentificacion")
	public ResponseEntity<?> actualizarCliente(@RequestBody TipoIdentificacionDTO tipoIdentificacionDTO) {
		try {

			TipoIdentificacion tipoIdentificacion = tipoIdentificacionService
					.actualizarTipoIdentificacion(tipoIdentificacionDTO);
			return ResponseEntity
					.ok(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 5
	@DeleteMapping("/eliminarTipoIdentificacion")
	public ResponseEntity<?> eliminarTipoIdentificacion(@RequestParam("id") Long id) {
		try {
			tipoIdentificacionService.eliminarTipoIdentificacion(id);
			return ResponseEntity.ok("Se eliminÃ³ satisfactoriamente");
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
