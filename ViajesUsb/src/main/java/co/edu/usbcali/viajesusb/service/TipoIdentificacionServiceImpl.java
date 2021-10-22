/**  
 * @Title:  TipoIdentificacionServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   17/09/2021 5:49:34 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;



import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.repository.TipoDestinoRepository;
import co.edu.usbcali.viajesusb.repository.TipoIdentificacionRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**
 * @ClassName: TipoIdentificacionServiceImpl
 * @Description: TODO
 * @author: Alejandro Forero
 * @date: 17/09/2021 5:49:34 p. m.
 * @Copyright: USB
 */

@Scope("singleton")
@Service
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService {

	@Autowired
	private TipoIdentificacionRepository tipoIdentificacionRepository;

	/**
	 * <p>
	 * Title: findByEstadoOrderByNombre
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param estado
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.TipoIdentificacionService#findByEstadoOrderByNombre(java.lang.String)
	 */

	@Override
	public List<TipoIdentificacion> findByEstadoOrderByNombre(String estado) throws SQLException {
		List<TipoIdentificacion> lsttipoIdentificacion = null;

		if (estado == null || estado.trim().equals("")) {
			throw new SQLException("El codigo de estado es obligatorio");
		}
		if (estado.matches("[^A|I]")) {
			throw new SQLException("Solo recibe las letras A e I");
		}

		lsttipoIdentificacion = tipoIdentificacionRepository.findByEstadoOrderByNombre(estado);

		return lsttipoIdentificacion;
	}

	/**
	 * <p>
	 * Title: findByCodigoAndEstado
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param codigo
	 * @param estado
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.TipoIdentificacionService#findByCodigoAndEstado(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public TipoIdentificacion findByCodigoAndEstado(String codigo, String estado) throws SQLException {

		TipoIdentificacion tipoIdentificacion = null;

		if (codigo == null || codigo.trim().equals("")) {
			throw new SQLException("El codigo tipo identificacion es obligatorio");
		}

		if (codigo.length() > 2) {
			throw new SQLException("La longitud maxima es de dos  ");
		}

		if (estado == null || estado.trim().equals("")) {
			throw new SQLException("El codigo de estado es obligatorio");
		}

		if (estado.matches("[^A|I]")) {
			throw new SQLException("Solo recibe las letras A e I");
		}

		tipoIdentificacion = tipoIdentificacionRepository.findByCodigoAndEstado(codigo.trim(), estado.trim());

		return tipoIdentificacion;
	}

	@Override
	public TipoIdentificacion guardarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws SQLException {

		TipoIdentificacion tipoIdentificacion = null;

		tipoIdentificacion = new TipoIdentificacion();

		// validacion codigo
		if (tipoIdentificacionDTO.getCodigo() == null || tipoIdentificacionDTO.getCodigo().trim().equals("")) {
			throw new SQLException("El tipo identificacion no puede ser nulo");
		}
		if (tipoIdentificacionDTO.getCodigo().length() > 2) {
			throw new SQLException("El codigo solo puede tener una longitud de 2");

		} else {

			tipoIdentificacion.setCodigo(tipoIdentificacionDTO.getCodigo());

		}

		// validacion NOMBRE
		if (tipoIdentificacionDTO.getNombre() == null || tipoIdentificacionDTO.getNombre().trim().equals("")) {
			throw new SQLException("El tipo destino no puede ser nulo");
		}

		 if (tipoIdentificacionDTO.getNombre().length() > 5) {
			throw new SQLException("La cantidad de digitos maxima de nombre codigo es 5");

		} else {
			tipoIdentificacion.setNombre(tipoIdentificacionDTO.getNombre());

		}

		// VALIDACION FECHA CREACION
		if (tipoIdentificacionDTO.getFechaCreacion() == null) {
			throw new SQLException("La fecha de creacion de un cliente no puede ser nula");
		} else {
			tipoIdentificacion.setFechaCreacion(tipoIdentificacionDTO.getFechaCreacion());
		}
		

		// VALIDACION DE USUCREADOR
		if (tipoIdentificacionDTO.getUsuCreador() == null || tipoIdentificacionDTO.getUsuCreador().trim().equals("")) {
			throw new SQLException("El nombre del creador de un cliente no puede ser nulo");
		} else if (tipoIdentificacionDTO.getUsuCreador().length() > 10) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		} else {

			tipoIdentificacion.setUsuCreador(tipoIdentificacionDTO.getUsuCreador());
		}

		// VALIDACION ESTADO
		if (tipoIdentificacionDTO.getEstado() == null || tipoIdentificacionDTO.getEstado().trim().equals("")) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		} else if (Utilities.isNumeric(tipoIdentificacionDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		} else if (tipoIdentificacionDTO.getEstado().length() > 1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		} else {
			tipoIdentificacion.setEstado(tipoIdentificacionDTO.getEstado());

		}

		tipoIdentificacionRepository.save(tipoIdentificacion);
		
		return tipoIdentificacion;

	}

	@Override
	public TipoIdentificacion actualizarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws SQLException {

		TipoIdentificacion tipoIdentificacion = null;

		tipoIdentificacion = findById(tipoIdentificacionDTO.getIdTiid());
		
		if (tipoIdentificacionDTO == null || tipoIdentificacionDTO.getIdTiid() == null) {
			throw new SQLException("El codigo de tipo identificacion es obligatorio");
		}
		
		
		// validacion codigo
		if (tipoIdentificacionDTO.getCodigo() == null || tipoIdentificacionDTO.getCodigo().trim().equals("")) {
			throw new SQLException("El tipo identificacion no puede ser nulo");
		}
		 if (!Utilities.isOnlyLetters(tipoIdentificacionDTO.getCodigo())) {
			throw new SQLException("El codigo solo recibe letras");
		 }
		 if (tipoIdentificacionDTO.getCodigo().length() > 2) {
			throw new SQLException("El codigo solo puede tener una longitud de 2");

		} else {

			tipoIdentificacion.setCodigo(tipoIdentificacionDTO.getCodigo());

		}

		// validacion NOMBRE
		if (tipoIdentificacionDTO.getNombre() == null || tipoIdentificacionDTO.getNombre().trim().equals("")) {
			throw new SQLException("El tipo destino no puede ser nulo");
		}
		if (!Utilities.isOnlyLetters(tipoIdentificacionDTO.getNombre())) {
			throw new SQLException("El codigo solo recibe letras");
		}
		
		 if (tipoIdentificacionDTO.getNombre().length() > 5) {
			throw new SQLException("La cantidad de digitos maxima de nombre codigo es 5");
		 } else {
			tipoIdentificacion.setNombre(tipoIdentificacionDTO.getNombre());

		}

		// VALIDACION FECHA CREACION
		if (tipoIdentificacionDTO.getFechaCreacion() == null) {
			throw new SQLException("La fecha de creacion de un cliente no puede ser nula");
		} else {
			tipoIdentificacionDTO.setFechaCreacion(tipoIdentificacionDTO.getFechaCreacion());
		}

		// VALIDACION DE USUCREADOR
		if (tipoIdentificacionDTO.getUsuCreador() == null || tipoIdentificacionDTO.getUsuCreador().trim().equals("")) {
			throw new SQLException("El nombre del creador de un cliente no puede ser nulo");
		}
		 if (tipoIdentificacionDTO.getUsuCreador().length() > 10) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		} else {

			tipoIdentificacion.setUsuCreador(tipoIdentificacionDTO.getUsuCreador());
		}

		
		// VALIDACION ESTADO
		if (tipoIdentificacionDTO.getEstado() == null || tipoIdentificacionDTO.getEstado().trim().equals("")) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}
		 if (Utilities.isNumeric(tipoIdentificacionDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		 }
		if (tipoIdentificacionDTO.getEstado().length() > 1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		} else {
			tipoIdentificacion.setEstado(tipoIdentificacionDTO.getEstado());
			
		}
		tipoIdentificacionRepository.save(tipoIdentificacion);
		return tipoIdentificacion;
	}
		

	public TipoIdentificacion findById(Long idTiid) throws SQLException {
		// validamoe el idedest con info
		if (idTiid == null) {
			throw new SQLException("Debe ingresar un id destino");
		}

		if (!tipoIdentificacionRepository.findById(idTiid).isPresent()) {
			throw new SQLException("El destino con id" + idTiid + "no existe");
		}
		return tipoIdentificacionRepository.findById(idTiid).get();
		
		

	}

	@Override
	public void eliminarTipoIdentificacion(Long id) throws SQLException {

		if (id == null ) {
			throw new SQLException("El tipo destino es obligatorio");
		}
		Optional<TipoIdentificacion> tipoIdentificacionBD = tipoIdentificacionRepository
				.findById(id);
		if (tipoIdentificacionBD.isPresent()) {
			tipoIdentificacionRepository.delete(tipoIdentificacionBD.get());
		} else {
			throw new SQLException("El destino no se encontro");
		}

	}
}
