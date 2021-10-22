/**  
 * @Title:  ClienteRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   18/10/2021 6:45:28 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.mapper.ClienteMapper;
import co.edu.usbcali.viajesusb.mapper.TipoDestinoMapper;
import co.edu.usbcali.viajesusb.service.ClienteService;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;

/**
 * @ClassName: ClienteRestController
 * @Description: TODO
 * @author: Alejandro Forero
 * @date: 18/10/2021 6:45:28 p. m.
 * @Copyright: USB
 */

@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteMapper clienteMapper;

	// 10
	@PostMapping("/guardarCliente")
	public ResponseEntity<ClienteDTO> guardarCliente(@RequestBody ClienteDTO clienteDTO) {
		try {
			Cliente cliente = clienteService.guardarCliente(clienteDTO);
			return ResponseEntity.ok(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 11
	@PutMapping("/actualizarCliente")
	public ResponseEntity<?> actualizarCliente(@RequestBody ClienteDTO clienteDTO) {
		try {

			Cliente cliente = clienteService.actualizarCliente(clienteDTO);
			return ResponseEntity.ok(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 12
	@DeleteMapping("/eliminarCliente/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable("id") Long id) {
		try {
			clienteService.eliminarCliente(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 2
	@GetMapping("/findByCorreo")
	public ResponseEntity<ClienteDTO> buscarClientePorCorreo(@RequestParam("correo") String correo) {
		try {
			Cliente cliente = clienteService.findByCorreoIgnoreCase(correo);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			// retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	// 3
	@GetMapping("/findByNumeroIdentificacionLike")
	public ResponseEntity<ClienteDTO> findClienteByNumeroIdentificacionLike(
			@RequestParam("numeroIdentificacion") String numeroIdentificacion) {
		try {
			Cliente cliente = clienteService.findByNumeroIdentificacionLike(numeroIdentificacion);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			// retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	// 4 list
	@GetMapping("/findByNombreLike")
	public ResponseEntity<ClienteDTO> buscarClientePorNombreLike(@RequestParam("nombre") String nombre) {
		try {
			Cliente cliente = clienteService.findByNombreIgnoreCaseLike(nombre);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			// retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	// 5
	@GetMapping("/findByFechasNacimiento")
	public ResponseEntity<List<ClienteDTO>> buscarClientePorFechaN(
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicial,
			@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFinal) {

		try {

			List<Cliente> listacliente = clienteService.findByFechaNacimientoBetween(fechaInicial, fechaFinal);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listacliente));
		} catch (Exception e) {
			// retorna un error 500
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	// 6
	@GetMapping("/coutEstado")
	public ResponseEntity<?> clientesPorEstado(@RequestParam("estado") String estado) {
		try {
			Long cliente = clienteService.countByEstado(estado);
			return ResponseEntity.ok().body(cliente);
		} catch (Exception e) {
			// retorna un error 500
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	// 8
	@GetMapping("/findByApellidos")
	public ResponseEntity<List<ClienteDTO>> buscarClientePorApellidos(
			@RequestParam("primerApellido") String primerApellido,
			@RequestParam("segundoApellido") String segundoApellido) {
		try {
			List<Cliente> listacliente = clienteService.findByPrimerApellidoOrSegundoApellido(primerApellido,
					segundoApellido);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listacliente));
		} catch (Exception e) {
			// retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

//	//9
//	@GetMapping("/findByNumeroIdentificacionAndEstados")
//	public ResponseEntity<ClienteDTO> buscarClientePoridentificacionYEstado(@RequestParam("numeroIdentificacion") String numeroIdentificacion, @RequestParam("estado") String estado ){
//		try {
//			Cliente listacliente=  clienteService.findByNumeroIdentificacionAndEstado(numeroIdentificacion, estado);
//			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(listacliente));
//		} catch (Exception e) {
//			//retorna un error 500
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//			
//		}
//	}
//	@GetMapping("/findByNombre")
//	public ResponseEntity<List<ClienteDTO>> buscarClientePorNombre(@RequestParam("nombre") String nombre){
//		try {
//			List<ClienteDTO> listacliente=  clienteService.consultarClientesPorNombre(nombre);
//			return ResponseEntity.ok().body(clienteMapper.listaClienteToListClienteDTO(listacliente));
//		} catch (Exception e) {
//			//retorna un error 500
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//			
//		}
//	}

}
