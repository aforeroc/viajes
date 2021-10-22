/**  
 * @Title:  TipoDestinoRestContoller.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   12/10/2021 10:33:35 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.mapper.TipoDestinoMapper;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;

/**
 * @ClassName: TipoDestinoRestContoller
 * @Description: TODO
 * @author: Alejandro Forero
 * @date: 12/10/2021 10:33:35 a. m.
 * @Copyright: USB
 */
@RestController
@RequestMapping("/api/tipoDestino")
public class TipoDestinoRestContoller {

	@Autowired
	private TipoDestinoService tipoDestinoService;
	@Autowired
	private TipoDestinoMapper tipoDestinoMapper;

	// 1
	@GetMapping("/getTipoDestinoPorCodigo")
	public ResponseEntity<TipoDestinoDTO> buscarTipoDestinoPorCodigo(@RequestParam("codigo") String codigo) {

		try {

			TipoDestino tipoDestino = tipoDestinoService.findByCodigo(codigo);
			return ResponseEntity.ok().body(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

	// 2
	@GetMapping("/getCodigoAndEstado")
	public ResponseEntity<TipoDestinoDTO> buscarCodigoAndEstado(@RequestParam("codigo") String codigo,
			@RequestParam("estado") String estado) {

		try {

			TipoDestino tipoDestino = tipoDestinoService.findByCodigoAndEstado(codigo, estado);
			return ResponseEntity.ok().body(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

	// 3
	@GetMapping("/getTiposDestino")
	public ResponseEntity<List<TipoDestinoDTO>> consultarTiposDestino(@RequestParam("estado") String estado) {

		try {

			List<TipoDestino> lstTipoDestino = tipoDestinoService.findByEstadoOrderByNombre(estado);

			return ResponseEntity.ok().body(tipoDestinoMapper.listTipoDestinoToTipoDestinoDTO(lstTipoDestino));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			// TODO: handle exception
		}

	}

	@PostMapping("/guardarTipoDestino")
	public ResponseEntity<?> guardarTipoDestino(@RequestBody TipoDestinoDTO tipoDestinoDTO) {
		try {
			TipoDestino tipoDestino = tipoDestinoService.guardarTipoDestino(tipoDestinoDTO);
			return ResponseEntity.ok(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/actualizarTipoDestino")
	public ResponseEntity<?> actualizarTipoDestino(@RequestBody TipoDestinoDTO tipoDestinoDTO) {
		try {
			TipoDestino tipoDestino = tipoDestinoService.actualizarTipoDestino(tipoDestinoDTO);
			return ResponseEntity.ok(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/eliminarTipoDestino")
	public ResponseEntity<?> eliminarTipoDestino(@RequestParam("id") Long id) {
		try {
			tipoDestinoService.eliminarTipoDestino(id);
			return ResponseEntity.ok("Se elimino satisfactoriamente");
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
