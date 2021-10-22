/**  
 * @Title:  ClienteRespositoryImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   17/09/2021 4:30:05 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.repository.ClienteRepository;
import co.edu.usbcali.viajesusb.repository.DestinoRepository;
import co.edu.usbcali.viajesusb.repository.TipoIdentificacionRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**
 * @ClassName: ClienteRespositoryImpl
 * @Description: TODO
 * @author: Alejandro Forero
 * @date: 17/09/2021 4:30:05 p. m.
 * @Copyright: USB
 */

@Scope("singleton")
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TipoIdentificacionService tipoIdentificacionService;

	/**
	 * <p>
	 * Title: findByEstadoOrderByNumeroIdentificacionAsc
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param estado
	 * @param pageable
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByEstadoOrderByNumeroIdentificacionAsc(java.lang.String,
	 *      org.springframework.data.domain.Pageable)
	 */

	@Override
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable)
			throws SQLException {

		Page<Cliente> pageCliente = null;

		if (estado == null || estado.trim().equals("")) {
			throw new SQLException("El codigo de estado es obligatorio");
		}
		if (estado.matches("[^A|I]")) {
			throw new SQLException("Solo recibe las letras A e I");
		}
		if (pageable == null || pageable.equals("")) {
			throw new SQLException("El paginado no puede ser nulo");
		}

		pageCliente = clienteRepository.findByEstadoOrderByNumeroIdentificacionAsc(estado, pageable);

		return pageCliente;
	}

	/**
	 * <p>
	 * Title: findByCorreoIgnoreCase
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param correo
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByCorreoIgnoreCase(java.lang.String)
	 */

	@Override
	public Cliente findByCorreoIgnoreCase(String correo) throws SQLException {
		Cliente cliente = null;

		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		if (correo == null || correo.trim().equals("")) {
			throw new SQLException("El correo es obligatorio");
		}

		if (correo != null) {
			Matcher mather = pattern.matcher(correo);
			if (mather.find() == true) {

			} else {
				throw new SQLException("Escribir correo de forma adecuada");
			}
		}

		cliente = clienteRepository.findByCorreoIgnoreCase(correo);

		return cliente;
	}

	/**
	 * <p>
	 * Title: findByNumeroIdentificacionLike
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param numeroIdentificacion
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByNumeroIdentificacionLike(java.lang.String)
	 */

	@Override
	public Cliente findByNumeroIdentificacionLike(String numeroIdentificacion) throws SQLException {
		Cliente cliente = null;

		if (numeroIdentificacion == null || numeroIdentificacion.trim().equals("")) {
			throw new SQLException("El codigo de tipo destino es obligatorio");
		}
		if (numeroIdentificacion.matches("[A-Z]+")) {
			throw new SQLException("Solo son validos numeros");
		}

		cliente = clienteRepository.findByNumeroIdentificacionLike(numeroIdentificacion.trim());

		return cliente;
	}

	/**
	 * <p>
	 * Title: findByNombreIgnoreCaseLike
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param nombre
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByNombreIgnoreCaseLike(java.lang.String)
	 */

	@Override
	public Cliente findByNombreIgnoreCaseLike(String nombre) throws SQLException {
		Cliente cliente = null;
		
		
		if (nombre == null || nombre.trim().equals("")) {
			throw new SQLException("El nombre es obligatorio");
		}
		if (nombre.matches("[0-9]+")) {
			throw new SQLException("Solo son validas letras");
		}

		cliente = clienteRepository.findByNombreIgnoreCaseLike(nombre.trim());

		return cliente;
	}

	/**
	 * <p>
	 * Title: findByFechaNacimientoBetween
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByFechaNacimientoBetween(java.util.Date,
	 *      java.util.Date)
	 */

	@Override
	public List<Cliente> findByFechaNacimientoBetween(Date fecha1, Date fecha2) throws SQLException {
		List<Cliente> lstcliente = null;

		if (fecha1 == null && fecha2 == null) {
			throw new SQLException("Las fechas no pueden ser nulas");
		}

		if (fecha1 == null) {
			throw new SQLException("Las fecha uno no puede ser nula");
		}

		if (fecha2 == null) {
			throw new SQLException("Las fecha dos no puede ser nula");
		}

		if (fecha1.compareTo(fecha2) >= 0) {
			throw new SQLException("Las fecha 2 no puede ser menor que la 1");
		}

		lstcliente = clienteRepository.findByFechaNacimientoBetween(fecha1, fecha2);

		if (lstcliente.isEmpty()) {
			throw new SQLException("no hay clientes en la fechas que ingreso");
		}
		return lstcliente;
	}

	/**
	 * <p>
	 * Title: countByEstado
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param estado
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#countByEstado(java.lang.String)
	 */

	@Override
	public Long countByEstado(String estado) throws SQLException {
		Long clientes;

		if (estado == null || estado.trim().equals("")) {
			throw new SQLException("El codigo de estado es obligatorio");
		}
		if (estado.matches("[^A|I]")) {
			throw new SQLException("Solo recibe las letras A e I");
		}
		clientes = clienteRepository.countByEstado(estado.trim());

		return clientes;
	}

	/**
	 * <p>
	 * Title: findByTipoIdentificacion_Codigo
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param codigoTipoIdentificacion
	 * @param pageable
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByTipoIdentificacion_Codigo(java.lang.String,
	 *      org.springframework.data.domain.Pageable)
	 */

	@Override
	public Page<Cliente> findByTipoIdentificacion_Codigo(String codigoTipoIdentificacion, Pageable pageable)
			throws SQLException {
		Page<Cliente> pageCliente = null;

		if (codigoTipoIdentificacion == null || codigoTipoIdentificacion.trim().equals("")) {
			throw new SQLException("El codigo tipo identificacion es obligatorio");
		}
		if (codigoTipoIdentificacion.matches("[0-9]+")) {
			throw new SQLException("Solo recibe letras");
		}
		if (codigoTipoIdentificacion.length() > 2) {
			throw new SQLException("La longitud maxima es de dos  ");
		}
		pageCliente = clienteRepository.findByTipoIdentificacion_Codigo(codigoTipoIdentificacion.trim(), pageable);

		return pageCliente;
	}

	/**
	 * <p>
	 * Title: findByPrimerApellidoOrSegundoApellido
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param primerapellido
	 * @param segundoapellido
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByPrimerApellidoOrSegundoApellido(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerapellido, String segundoapellido)
			throws SQLException {
		List<Cliente> lstCliente = null;

		if (primerapellido == null || primerapellido.trim().equals("")) {
			throw new SQLException("El primer apellido es obligatorio");
		}
		if (primerapellido.matches("[^A-Z]")) {
			throw new SQLException("Solo recibe las letras ");
		}

		lstCliente = clienteRepository.findByPrimerApellidoOrSegundoApellido(primerapellido.trim(), segundoapellido);

		return lstCliente;
	}

	/**
	 * <p>
	 * Title: consultarClientesPorEstadoNoIdentificacionTipoIdentificacion
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param estado
	 * @param noIdentificacion
	 * @param nombre
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#consultarClientesPorEstadoNoIdentificacionTipoIdentificacion(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */

	@Override
	public List<ClienteDTO> consultarClientesPorEstadoNoIdentificacionTipoIdentificacion(String estado,
			String noIdentificacion, String nombre) throws SQLException {
		List<ClienteDTO> lstCliente = null;

		if (estado == null || estado.trim().equals("")) {
			throw new SQLException("El codigo de estado es obligatorio");
		}
		if (estado.matches("[^A|I]")) {
			throw new SQLException("Solo recibe las letras A e I");
		}

		if (noIdentificacion == null || noIdentificacion.trim().equals("")) {
			throw new SQLException("El numero de identificacion es obligatorio");
		}
		if (noIdentificacion.matches("[^0-9]+")) {
			throw new SQLException("Solo se aceptan numeros");
		}

		if (nombre == null || nombre.trim().equals("")) {
			throw new SQLException("El nombre es obligatorio");
		}
		if (nombre.matches("[0-9]+")) {
			throw new SQLException("Solo son validas letras");
		}

		lstCliente = clienteRepository.consultarClientesPorEstadoNoIdentificacionTipoIdentificacion(estado.trim(),
				noIdentificacion.trim(), nombre.trim());

		return lstCliente;
	}

	@Override
	public Cliente guardarCliente(ClienteDTO clienteDTO) throws SQLException {

		Cliente cliente = null;
		TipoIdentificacion tipoIdentificacion = null;

		cliente = new Cliente();

		// Validaciones de numero identificacion
		// ...VALIDADO....
		if (clienteDTO.getNumeroIdentificacion() == null || clienteDTO.getNumeroIdentificacion().trim().equals("")) {
			throw new SQLException("El numero de identificacion no puede ser nulo");
		} else if (!Utilities.isNumeric(clienteDTO.getNumeroIdentificacion())) {
			throw new SQLException(
					"El numero de identificacion no puede contener letras " + clienteDTO.getNumeroIdentificacion());
		} else if (clienteDTO.getNumeroIdentificacion().length() > 15) {
			throw new SQLException("La cantidad de digitos del numero identificacion no pueden exceder el total de 15");
		} else {
			cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
		}

		// Validaciones para primer apellido
		// ...VALIDADO....
		if (clienteDTO.getPrimerApellido() == null || clienteDTO.getPrimerApellido().trim().equals("")) {
			throw new SQLException("El primer apellido del cliente no puede ser nulo");
		} else if (clienteDTO.getPrimerApellido().length() > 100) {
			throw new SQLException(
					"La cantidad de caracteres del primer apellido del cliente no pueden exceder el total de 100");
		} else {
			cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
		}

		// Validaciones para segundo apellido
		// ...VALIDADO....\\
		if (clienteDTO.getSegundoApellido() != null) {
			if (clienteDTO.getSegundoApellido().length() > 100) {
				throw new SQLException(
						"La cantidad de caracteres del segundo apellido del cliente no pueden exceder el total de 100");
			}
		} else {
			cliente.setSegundoApellido(clienteDTO.getSegundoApellido());
		}

		// Validaciones para el nombre
		// ...VALIDADO....
		if (clienteDTO.getNombre() == null || clienteDTO.getNombre().trim().equals("")) {
			throw new SQLException("El nombre del cliente no puede ser nulo");
		} else if (clienteDTO.getNombre().length() > 100) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del cliente no pueden exceder el total de 100");
		} else {
			cliente.setNombre(clienteDTO.getNombre());
		}

		// Validaciones para el numero de telefono 1
		// ...VALIDADO....
		if (clienteDTO.getTelefono1() == null || clienteDTO.getTelefono1().trim().equals("")) {
			throw new SQLException("El telefono 1 del cliente no puede ser nulo");
		} else if (!Utilities.isNumeric(clienteDTO.getTelefono1())) {
			throw new SQLException("El numero telefonico no debe contener letras");
		} else if (clienteDTO.getTelefono1().length() > 15) {
			throw new SQLException("La cantidad de digitos del numero telefonico 1 no pueden exceder el total de 15");
		} else {
			cliente.setTelefono1(clienteDTO.getTelefono1());
		}

		// Validaciones para el numero de telefono 2
		// ...VALIDADO....\\
		if (clienteDTO.getTelefono2() != null) {
			if (clienteDTO.getTelefono2().length() > 15) {
				throw new SQLException(
						"La cantidad de digitos del numero telefonico 2 no pueden exceder el total de 15");
			}
		} else {
			cliente.setTelefono2(clienteDTO.getTelefono2());
		}

		// Validaciones para el correo electronico del cliente
		// ...Validado....\\
		if (!Utilities.isValidEmail(clienteDTO.getCorreo())) {
			throw new SQLException("El correo no es valido");

		}
		if (clienteDTO.getCorreo().length() > 100) {
			throw new SQLException("La cantidad de caracteres del correo no puede exceder el total de 100");
		} else {
			cliente.setCorreo(clienteDTO.getCorreo());
		}

		// Validaciones para el genero del cliente
		// ...Validado....\\
		if (clienteDTO.getSexo() == null || clienteDTO.getSexo().trim().equals("")) {
			throw new SQLException("El genero del cliente no puede ser nulo");
		}
		if (Utilities.isNumeric(clienteDTO.getSexo())) {
			throw new SQLException("El genero no debe contener numeros");
		}
		if (clienteDTO.getSexo().length() > 1) {
			throw new SQLException("La cantidad maxima de caracteres para el genero es de 1");
		} else {
			cliente.setSexo(clienteDTO.getSexo());
		}

		// Validaciones para la fecha de nacimiento del cliente
		// ...Validado....\\
		if (clienteDTO.getFechaNacimiento() == null) {
			throw new SQLException("La fecha de nacimiento del cliente no puede ser nula");
			// } else if (clienteDTO.getFechaNacimiento().compareTo(Constantes.FECHA_ACTUAL)
			// > 0) {
			// throw new SQLException( "La fecha de nacimiento de la persona que ingreso,
			// indica que aun no ha nacido");
		} else {
			cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
		}

		// Validaciones para la fecha de creacion de un cliente
		// ...Validado....\\
		if (clienteDTO.getFechaCreacion() == null) {
			throw new SQLException("La fecha de creacion de un cliente no puede ser nula");
		} else {
			cliente.setFechaCreacion(clienteDTO.getFechaCreacion());
		}

		// Validaciones para el nombre del usuario creador de un cliente
		// ...Validado....\\
		if (clienteDTO.getUsuCreador() == null || clienteDTO.getUsuCreador().trim().equals("")) {
			throw new SQLException("El nombre del creador de un cliente no puede ser nulo");
		}
		if (clienteDTO.getUsuCreador().length() > 10) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		} else {
			cliente.setUsuCreador(clienteDTO.getUsuCreador());
		}

		// Validacioones para el estado del cliente
		// ...Validado...\\
		if (clienteDTO.getEstado() == null || clienteDTO.getEstado().trim().equals("")) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}
		if (Utilities.isNumeric(clienteDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		}
		if (clienteDTO.getEstado().length() > 1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		} else {
			cliente.setEstado(clienteDTO.getEstado());
		}

		tipoIdentificacion = tipoIdentificacionService.findByCodigoAndEstado(clienteDTO.getCodigoTipoIdentificacion(),
				Constantes.ACTIVO);

		if (tipoIdentificacion == null) {
			throw new SQLException("El tipo identificacion" + clienteDTO.getCodigoTipoIdentificacion() + "No existe");
		}

		cliente.setTipoIdentificacion(tipoIdentificacion);

		clienteRepository.save(cliente);
		
		return cliente;

	}

	@Override
	public Cliente actualizarCliente(ClienteDTO clienteDTO) throws SQLException {
		Cliente cliente = null;
		TipoIdentificacion tipoIdentificacion = null;

		cliente = findById(clienteDTO.getIdClie());

		

		

		if (Utilities.isNull(clienteDTO.getIdClie())) {
			throw new SQLException("El id del cliente no puede ser nulo");
		} else {
			cliente.setIdClie(clienteDTO.getIdClie());
		}

		if (Utilities.isNull(clienteDTO.getNumeroIdentificacion())) {
			throw new SQLException("El numero de identificacion no puede ser nulo");
		}
		if (!Utilities.isNumeric(clienteDTO.getNumeroIdentificacion())) {
			throw new SQLException("El numero de identificacion no puede tener letras");
		}
		if (clienteDTO.getNumeroIdentificacion().length() > 15) {
			throw new SQLException("La cantidad de digitos del numero identificacion no pueden exceder el total de 15");
		} else {
			cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
		}

		if (Utilities.isNull(clienteDTO.getPrimerApellido())) {
			throw new SQLException("El primer apellido del cliente no puede ser nulo");
		}
		if (clienteDTO.getPrimerApellido().length() > 100) {
			throw new SQLException(
					"La cantidad de caracteres del primer apellido del cliente no pueden exceder el total de 100");
		}
		if (cliente.getPrimerApellido().equals(clienteDTO.getPrimerApellido())) {

		} else {
			cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
		}

		if (!Utilities.isNull(clienteDTO.getSegundoApellido())) {
			if (clienteDTO.getSegundoApellido().length() > 100) {
				throw new SQLException(
						"La cantidad de caracteres del segundo apellido del cliente no pueden exceder el total de 100");
			}
		} else {
			cliente.setSegundoApellido(clienteDTO.getSegundoApellido());
		}

		if (Utilities.isNull(clienteDTO.getNombre())) {
			throw new SQLException("El nombre del cliente no puede ser nulo");
		}
		if (clienteDTO.getNombre().length() > 100) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del cliente no pueden exceder el total de 100");
		}
		if (cliente.getNombre().equals(clienteDTO.getNombre())) {
			throw new SQLException("El nombre que ingresaste es igual al que ya existe");
		} else {
			cliente.setNombre(clienteDTO.getNombre());
		}

		if (Utilities.isNull(clienteDTO.getTelefono1())) {
			throw new SQLException("El telefono 1 del cliente no puede ser nulo");
		}
		if (!Utilities.isNumeric(clienteDTO.getTelefono1())) {
			throw new SQLException("El numero telefonico no debe contener letras");
		}
		if (clienteDTO.getTelefono1().length() > 15) {
			throw new SQLException("La cantidad de digitos del numero telefonico 1 no pueden exceder el total de 15");
		} else {
			cliente.setTelefono1(clienteDTO.getTelefono1());
		}

		if (!Utilities.isNull(clienteDTO.getTelefono2())) {
			if (clienteDTO.getTelefono2().length() > 15) {
				throw new SQLException(
						"La cantidad de digitos del numero telefonico 2 no pueden exceder el total de 15");
			}
		} else {
			cliente.setTelefono2(clienteDTO.getTelefono2());
		}

		if (!Utilities.isValidEmail(clienteDTO.getCorreo())) {
			throw new SQLException("El correo no es valido");

		}
		if (clienteDTO.getCorreo().length() > 100) {
			throw new SQLException("La cantidad de caracteres del correo no puede exceder el total de 100");
		} else {
			cliente.setCorreo(clienteDTO.getCorreo());
		}

		if (Utilities.isNull(clienteDTO.getSexo())) {
			throw new SQLException("El genero del cliente no puede ser nulo");
		} else if (Utilities.isNumeric(clienteDTO.getSexo())) {
			throw new SQLException("El genero no debe contener numeros");
		} else if (clienteDTO.getSexo().length() > 1) {
			throw new SQLException("La cantidad maxima de caracteres para el genero es de 1");
		} else {
			cliente.setSexo(clienteDTO.getSexo());
		}

		if (Utilities.isNull(clienteDTO.getFechaNacimiento())) {
			throw new SQLException("La fecha de nacimiento del cliente no puede ser nula");

		} else {
			cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
		}

		if (Utilities.isNull(clienteDTO.getFechaCreacion())) {
			throw new SQLException("La fecha de creacion del cliente no puede ser nula");
		} else {
			cliente.setFechaCreacion(clienteDTO.getFechaCreacion());
		}

		if (Utilities.isNull(clienteDTO.getUsuCreador())) {
			throw new SQLException("El nombre del creador de un cliente no puede ser nulo");
		}
		if (clienteDTO.getUsuCreador().length() > 10) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		} else {
			cliente.setUsuCreador(clienteDTO.getUsuCreador());
		}

		if (Utilities.isNull(clienteDTO.getEstado())) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}
		if (Utilities.isNumeric(clienteDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		}
		if (clienteDTO.getEstado().length() > 1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		} else {
			cliente.setEstado(clienteDTO.getEstado());
		}

		tipoIdentificacion = tipoIdentificacionService.findByCodigoAndEstado(clienteDTO.getCodigoTipoIdentificacion(),
				Constantes.ACTIVO);

		if (tipoIdentificacion == null) {
			throw new SQLException("El tipo identificacion" + clienteDTO.getCodigoTipoIdentificacion() + "No existe");
		}

		cliente.setTipoIdentificacion(tipoIdentificacion);

		clienteRepository.save(cliente);
		
		return cliente;
	}

	public Cliente findById(Long idCliente) throws SQLException {

		if (idCliente == null) {
			throw new SQLException("Debe ingresar un id destino");
		}

		return clienteRepository.findById(idCliente).get();

	}

	@Override
	public void eliminarCliente(Long idClie) throws SQLException {

		if (idClie == null) {
			throw new SQLException("El id cliente es obligatorio");
		}

		Optional<Cliente> cliente = clienteRepository.findById(idClie);

		if (cliente.isPresent()) {
			clienteRepository.delete(cliente.get());
		} else {
			throw new SQLException("El cliente no se encontro");
		}

	}
}
