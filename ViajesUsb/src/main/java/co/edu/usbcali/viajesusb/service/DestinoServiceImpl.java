/**  
 * @Title:  DestinoServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   17/09/2021 12:33:47 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.DestinoRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**
 * @ClassName: DestinoServiceImpl
 * @Description: TODO
 * @author: Alejandro Forero
 * @date: 17/09/2021 12:33:47 a. m.
 * @Copyright: USB
 */
@Scope("singleton")
@Service
public class DestinoServiceImpl implements DestinoService {

	@Autowired
	private DestinoRepository destinoRepository;

	@Autowired
	private TipoDestinoService tipoDestinoService;

	/**
	 * <p>
	 * Title: findByTipoDestinoCodigo
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param codigoTipoDestino
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.DestinoService#findByTipoDestinoCodigo(java.lang.String)
	 */

	@Override
	public List<Destino> findByTipoDestinoCodigo(String codigoTipoDestino) throws SQLException {

		List<Destino> lstDestino = null;

		if (codigoTipoDestino == null || codigoTipoDestino.trim().equals("")) {
			throw new SQLException("El codigo de tipo destino es obligatorio");
		}
		if (codigoTipoDestino.matches("[0-9]+")) {
			throw new SQLException("Solo son validas letras");
		}

		lstDestino = destinoRepository.findByTipoDestino_codigo(codigoTipoDestino.trim());

		return lstDestino;
	}

	/**
	 * <p>
	 * Title: findByEstado
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param estado
	 * @param pageable
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.DestinoService#findByEstado(java.lang.String,
	 *      org.springframework.data.domain.Pageable)
	 */

	@Override
	public Page<Destino> findByEstado(String estado, Pageable pageable) throws SQLException {

		Page<Destino> pageDestino = null;

		if (estado == null || estado.trim().equals("")) {
			throw new SQLException("El codigo de estado es obligatorio");
		}
		if (pageable == null || pageable.equals("")) {
			throw new SQLException("El paginado no puede ser nulo");
		}
		if (estado.matches("[^A|I]")) {
			throw new SQLException("Solo recibe las letras A e I");
		}

		pageDestino = destinoRepository.findByEstado(estado.trim(), pageable);

		return pageDestino;
	}

	/**
	 * 
	 * @Title: guardarDEstino
	 * @Description: metodo guarda el destino
	 * 
	 * @param: @param  destino
	 * @param: @throws SQLException
	 * @return: void
	 * @throws
	 */
	@Override
	public Destino guardarDestino(DestinoDTO destinoDTO) throws SQLException {
		Destino destino = null;
		TipoDestino tipoDestino = null;
		// TODO pendiete validadciones

		// se arma el destino
		destino = new Destino();

		// VALIDAR AIRE
		if (Utilities.isNull(destinoDTO.getAire())) {
			throw new SQLException("El parametro aire no puede ser nulo");
		}  if (Utilities.isNumeric(destinoDTO.getAire())) {
			throw new SQLException("El parametro aire no puede tener numeros");
		}  if (destinoDTO.getAire().length() > 1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		} else {
			destino.setAire(destinoDTO.getAire());
		}

		// VALIDAR TIERRA
		if (Utilities.isNull(destinoDTO.getTierra())) {
			throw new SQLException("El parametro tierra no puede ser nulo");
		}  if (Utilities.isNumeric(destinoDTO.getTierra())) {
			throw new SQLException("El parametro tierra no puede tener numeros");
		}  if (destinoDTO.getTierra().length() > 1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		} else {
			destino.setTierra(destinoDTO.getTierra());
		}

		// VALIDAR MAR
		if (Utilities.isNull(destinoDTO.getMar())) {
			throw new SQLException("El parametro mar no puede ser nulo");
		}  if (Utilities.isNumeric(destinoDTO.getMar())) {
			throw new SQLException("El parametro mar no puede tener numeros");
		}  if (destinoDTO.getMar().length() > 1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		} else {
			destino.setMar(destinoDTO.getMar());
		}

		// VALDIDAR CODIGO
		if (Utilities.isNull(destinoDTO.getCodigo())) {
			throw new SQLException("El codigo no puede ser nulo");
		}  if (!Utilities.isOnlyLetters(destinoDTO.getCodigo())) {
			throw new SQLException("El codigo no puede contener numeros");
		}  if (destinoDTO.getCodigo().length() > 5) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 5");
		} else {
			destino.setCodigo(destinoDTO.getCodigo());
		}

		// VALIDAR NOMBRE
		if (Utilities.isNull(destinoDTO.getNombre())) {
			throw new SQLException("El nombre del destino no puede ser nulo");
		}  if (destinoDTO.getNombre().length() > 100) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del destino no pueden exceder el total de 100");
		} else {
			destino.setNombre(destinoDTO.getNombre());
		}

		// VALIDAR DESCRIPCION
		if (Utilities.isNull(destinoDTO.getDescripcion())) {
			throw new SQLException("La descripcion del destino no puede ser nulo");
		}  if (destinoDTO.getDescripcion().length() > 300) {
			throw new SQLException(
					"La cantidad de caracteres de la descripcion del destino no pueden exceder el total de 300");
		} else {
			destino.setDescripcion(destinoDTO.getDescripcion());
		}

		// VALIDAR ESTADO
		if (Utilities.isNull(destinoDTO.getEstado())) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}  if (Utilities.isNumeric(destinoDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		}  if (destinoDTO.getEstado().length() > 1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		} else {
			destino.setEstado(destinoDTO.getEstado());
		}

		// VALIDAR FECHA CREACION
		if (destinoDTO.getFechaCreacion() == null) {
			throw new SQLException("La fecha de creacion de un destino no puede ser nula");
		} else {
			destino.setFechaCreacion(destinoDTO.getFechaCreacion());
		}

		// VALIDAR USUCREADOR
		if (Utilities.isNull(destinoDTO.getUsoCreador())) {
			throw new SQLException("El nombre del creador de un destino no puede ser nulo");
		}  if (destinoDTO.getUsoCreador().length() > 10) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		} else {
			destino.setUsoCreador(destinoDTO.getUsoCreador());
		}

		// se consulta el tipo destino dado su id
		tipoDestino = tipoDestinoService.findByCodigoAndEstado(destinoDTO.getCodigoTipoDestino(), Constantes.ACTIVO);
		// validadmos que el tipo destino exista y este activo

		if (tipoDestino == null) {
			throw new SQLException("EL tipo destino" + destinoDTO.getCodigoTipoDestino() + "No existe");
		}

		destino.setTipoDestino(tipoDestino);

		destinoRepository.save(destino);
		
		return destino;
	}

	@Override
	public Destino actualizarDestino(DestinoDTO destinoDTO) throws SQLException {
		Destino destino = null;
		TipoDestino tipoDestino = null;
		// TODO pendiete validadciones

		// Se arma el destino
		destino = findById(destinoDTO.getIdDest());

		//VALIDA ID
		if (Utilities.isNull(destinoDTO.getIdDest())) {
			throw new SQLException("El id del cliente no puede ser nulo");
		}else {
			destino.setIdDest(destinoDTO.getIdDest());
		}
		
		// VALIDAR AIRE
		if (Utilities.isNull(destinoDTO.getAire())) {
			throw new SQLException("El parametro aire no puede ser nulo");
		} else if (Utilities.isNumeric(destinoDTO.getAire())) {
			throw new SQLException("El parametro aire no puede tener numeros");
		} else if (destinoDTO.getAire().length() > 1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		} else {
			destino.setAire(destinoDTO.getAire());
		}

		// VALIDAR TIERRA
		if (Utilities.isNull(destinoDTO.getTierra())) {
			throw new SQLException("El parametro tierra no puede ser nulo");
		} else if (Utilities.isNumeric(destinoDTO.getTierra())) {
			throw new SQLException("El parametro tierra no puede tener numeros");
		} else if (destinoDTO.getTierra().length() > 1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		} else {
			destino.setTierra(destinoDTO.getTierra());
		}

		// VALIDAR MAR
		if (Utilities.isNull(destinoDTO.getMar())) {
			throw new SQLException("El parametro mar no puede ser nulo");
		} else if (Utilities.isNumeric(destinoDTO.getMar())) {
			throw new SQLException("El parametro mar no puede tener numeros");
		} else if (destinoDTO.getMar().length() > 1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		} else {
			destino.setMar(destinoDTO.getMar());
		}

		// VALDIDAR CODIGO
		if (Utilities.isNull(destinoDTO.getCodigo())) {
			throw new SQLException("El codigo no puede ser nulo");
		} else if (!Utilities.isOnlyLetters(destinoDTO.getCodigo())) {
			throw new SQLException("El codigo no puede contener numeros");
		} else if (destinoDTO.getCodigo().length() > 5) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 5");
		} else {
			destino.setCodigo(destinoDTO.getCodigo());
		}

		// VALIDAR NOMBRE
		if (Utilities.isNull(destinoDTO.getNombre())) {
			throw new SQLException("El nombre del destino no puede ser nulo");
		} else if (destinoDTO.getNombre().length() > 100) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del destino no pueden exceder el total de 100");
		} else {
			destino.setNombre(destinoDTO.getNombre());
		}

		// VALIDAR DESCRIPCION
		if (Utilities.isNull(destinoDTO.getDescripcion())) {
			throw new SQLException("La descripcion del destino no puede ser nulo");
		} else if (destinoDTO.getDescripcion().length() > 300) {
			throw new SQLException(
					"La cantidad de caracteres de la descripcion del destino no pueden exceder el total de 300");
		} else {
			destino.setDescripcion(destinoDTO.getDescripcion());
		}

		// VALIDAR ESTADO
		if (Utilities.isNull(destinoDTO.getEstado())) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		} else if (Utilities.isNumeric(destinoDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		} else if (destinoDTO.getEstado().length() > 1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		} else {
			destino.setEstado(destinoDTO.getEstado());
		}

		// VALIDAR FECHA CREACION
		if (destinoDTO.getFechaCreacion() == null) {
			throw new SQLException("La fecha de creacion de un destino no puede ser nula");
		} else {
			destino.setFechaCreacion(destinoDTO.getFechaCreacion());
		}

		// VALIDAR USUCREADOR
		if (Utilities.isNull(destinoDTO.getUsoCreador())) {
			throw new SQLException("El nombre del creador de un destino no puede ser nulo");
		} else if (destinoDTO.getUsoCreador().length() > 10) {
			throw new SQLException(
					"La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		} else {
			destino.setUsoCreador(destinoDTO.getUsoCreador());
		}

		// se consulta el tipo destino dado su id
		tipoDestino = tipoDestinoService.findByCodigoAndEstado(destinoDTO.getCodigoTipoDestino(), Constantes.ACTIVO);
		// validadmos que el tipo destino exista y este activo

		if (tipoDestino == null) {
			throw new SQLException("EL tipo destino" + destinoDTO.getCodigoTipoDestino() + "No existe");
		}

		destino.setTipoDestino(tipoDestino);

		destinoRepository.save(destino);
		
		return destino;

	}

	
	
	
	
	
	public Destino findById(Long idDest) throws SQLException {
		// validamoe el idedest con info
		if (idDest == null) {
			throw new SQLException("Debe ingresar un id destino");
		}

		if (!destinoRepository.findById(idDest).isPresent()) {
			throw new SQLException("El destino con id" + idDest + "no existe");
		}
		return destinoRepository.findById(idDest).get();

	}

	@Override
	public void eliminarDestino(Long idDest) throws SQLException {

		// validar que se ingrese el id destino a eliminar
		if (idDest == null ) {
			throw new SQLException("El id destino es obligatorio");
		}
		Optional<Destino> destinoBD = destinoRepository.findById(idDest);

		if (destinoBD.isPresent()) {
			destinoRepository.delete(destinoBD.get());
		} else {
			throw new SQLException("El destino no se encontro");
		}
	}

}
