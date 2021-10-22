/**  
 * @Title:  ClienteTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 1:41:03 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.ClienteRepository;
import co.edu.usbcali.viajesusb.service.ClienteService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  ClienteTest   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   6/09/2021 1:41:03 p. m.      
 * @Copyright:  USB
 */

@SpringBootTest
@Rollback(false)
class ClienteTest {

	@Autowired
	private ClienteService clienteService;
	
	@Test
	@Transactional
	void ConsultarLosClientesPorEstadoPaginadaOrdenadaDeFormaAscendentePorNumeroDeIdentificación() {
		
		Page <Cliente> pageCliente=null;
	
		try {
			Pageable pageable =PageRequest.of(0, 3);
			pageCliente = clienteService.findByEstadoOrderByNumeroIdentificacionAsc("A", pageable);

			
			for (Cliente cliente : pageCliente.getContent()) {
				System.out.println(cliente.getNombre() +"-"+ cliente.getNumeroIdentificacion());
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void ConsultarClientePorCorreo() {
		
		Cliente cliente=null;
	
		try {
			//aforerocardona@gmail.com
			cliente = clienteService.findByCorreoIgnoreCase("aforerocardona@gmail.com");

			System.out.println(cliente.toString());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Transactional
	void consultarClientePorNumeroIdentificacionLike() {
		
		Cliente cliente=null;
	


			try {
				//%1005891079%
				cliente = clienteService.findByNumeroIdentificacionLike("%1005891079%");

				System.out.println(cliente.toString());
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
	
	}
	
	
	
	
	
	@Test
	@Transactional
	void ConsultarClienteIgnorandoayusculasYMinusculasConLike() {
		
		Cliente cliente=null;
	

			
			

			try {
				
				cliente = clienteService.findByNombreIgnoreCaseLike("ALEJANDRO");

				System.out.println(cliente.toString());
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
	
	}
	
	
	@Test
	@Transactional
	void ConsultarClientePorFechaNacimientoEntreDosFechas() {
		
		List<Cliente> lstcliente=null;
	
		Calendar fecha1 =new GregorianCalendar(1990,8,10);
		Calendar fecha2 =new GregorianCalendar(2020,10,10);
		//2020,10,10
		//1990,8,10
			
			

			try {
				
				lstcliente = clienteService.findByFechaNacimientoBetween(fecha1.getTime(),fecha2.getTime());

				for (Cliente cliente : lstcliente) {
					System.out.println(cliente.toString());
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
	
	}
	
	@Test
	@Transactional
	void ConsultarTotalClientesPorEstado() {
		
		Long clientes;
	
	
			

			try {
				
				clientes = clienteService.countByEstado("A");
				System.out.println("Hay un total de:"+" "+clientes);
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
	
	}
	
	@Test
	@Transactional
	void ConsultarClientePorTipoIDentificacion() {
		
		Page <Cliente> pageCliente=null;
	
	
			

			try {
				Pageable pageable =PageRequest.of(0, 4);
				pageCliente = clienteService.findByTipoIdentificacion_Codigo("CC", pageable);
				
				
				for (Cliente cliente : pageCliente.getContent()) {
						System.out.println(cliente.toString());
				}
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
	
	}
	

	@Test
	@Transactional
	void DebeConsultarClientesPorApellido() {
		
		List<Cliente> lstCliente =null;
		
		try {
			lstCliente=clienteService.findByPrimerApellidoOrSegundoApellido("Rivera", "Giraldo") ;   
			
			for (Cliente cliente : lstCliente) {
				System.out.println(cliente.toString());
			}
				
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	@Test
    @Transactional
    void debeConsultarMaldita() {

        List<ClienteDTO> lstCliente = null;

        try {
            lstCliente = clienteService.consultarClientesPorEstadoNoIdentificacionTipoIdentificacion("A", "1005891079", "Alejandro");
            for (ClienteDTO cliente : lstCliente) {
                System.out.println(cliente.getNombre());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
	

	@Test
	@Transactional
	void debeGuardarElCliente() {
		
		try {
			ClienteDTO clienteDTO = new ClienteDTO();
			
			clienteDTO.setNumeroIdentificacion("1234");
			clienteDTO.setPrimerApellido("prueba");
			clienteDTO.setSegundoApellido("prueba2");
			clienteDTO.setNombre("pedr00o");
			clienteDTO.setTelefono1("3002401666");
			clienteDTO.setSexo(Constantes.MASCULINO);
			clienteDTO.setCorreo("pedrito@gmail.com");
			clienteDTO.setFechaNacimiento(new Date(2000/10/21));
			clienteDTO.setFechaCreacion(new Date());
			clienteDTO.setUsuCreador("AFOC");
			clienteDTO.setEstado(Constantes.ACTIVO);
			clienteDTO.setCodigoTipoIdentificacion("CC");
			
			clienteService.guardarCliente(clienteDTO);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
		
	@Test
	@Transactional
	void debeActualizarCliente() {
		try {
			ClienteDTO clienteDTO = new ClienteDTO();
			
			clienteDTO.setIdClie(6L);
			
			clienteDTO.setNumeroIdentificacion("2111111111");
			clienteDTO.setPrimerApellido("pueba");
			clienteDTO.setSegundoApellido("prueba2");
			clienteDTO.setNombre("pacoo");
			clienteDTO.setTelefono1("3002401666");
			clienteDTO.setSexo(Constantes.MASCULINO);
			clienteDTO.setCorreo("paco@gmail.com");
			clienteDTO.setFechaNacimiento(new Date(2000/10/21));
			clienteDTO.setFechaCreacion(new Date());
			clienteDTO.setUsuCreador("AFOC");
			clienteDTO.setEstado(Constantes.ACTIVO);
			clienteDTO.setCodigoTipoIdentificacion("CC");
			
			clienteService.actualizarCliente(clienteDTO);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
		
		
		
	

	
	
	@Test
	@Transactional
	void eliminarCliente() {
		
		try {

			ClienteDTO clienteDTO = new ClienteDTO();

			clienteDTO.setIdClie(7L);

			clienteService.eliminarCliente(clienteDTO.getIdClie());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
		
	}


