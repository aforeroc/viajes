/**  
 * @Title:  TipoDestinoImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Alejandro Forero     
 * @date:   7/09/2021 11:53:15 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.repository.DestinoRepository;
import co.edu.usbcali.viajesusb.repository.TipoDestinoRepository;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  TipoDestinoImpl   
  * @Description: TODO   
 * @author: Alejandro Forero     
 * @date:   7/09/2021 11:53:15 a. m.      
 * @Copyright:  USB
 */
@Scope("singleton")
@Service
public class TipoDestinoServiceImpl implements TipoDestinoService{

	@Autowired
	private TipoDestinoRepository tipoDestinoRepository;
	
	/**   
	 * <p>Title: findByCodigo</p>   
	 * <p>Description: </p>   
	 * @param codigo
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByCodigo(java.lang.String)   
	 */
	
	@Override
	public TipoDestino findByCodigo(String codigo) throws SQLException {
		
		TipoDestino tipoDestino =null;
		
		if(codigo == null || codigo.trim().equals("")) {
			throw new SQLException ("El codigo de tipo destino es obligatorio"); 	
		}
		if(codigo.matches("[0-9]+")) {
			throw new SQLException ("Solo son validas letras"); 
		}
	
		tipoDestino=tipoDestinoRepository.findByCodigo(codigo.trim());//playa no funciona
			
		return tipoDestino;
	}

	/**   
	 * <p>Title: findByCodigoAndEstado</p>   
	 * <p>Description: </p>   
	 * @param codigo
	 * @param estado
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByCodigoAndEstado(java.lang.String, java.lang.String)   
	 */
	
	@Override
	public TipoDestino findByCodigoAndEstado(String codigo, String estado) throws SQLException {
		
		TipoDestino tipoDestino =null;
		
		if(codigo == null || codigo.trim().equals("")) {
			throw new SQLException ("El codigo de tipo destino es obligatorio"); 	
		}
		if(codigo.matches("[0-9]+")) {
			throw new SQLException ("Solo son validas letras"); 
		}
		if(estado == null || estado.trim().equals("")) {
			throw new SQLException ("El codigo de estado es obligatorio"); 
		}
		if (estado.matches("[^A|I]" ) ) {
            throw new SQLException("Solo recibe las letras A e I");
        }
		
		tipoDestino=tipoDestinoRepository.findByCodigoAndEstado(codigo.trim(),estado);//playa no funciona
			
			
		return tipoDestino;
	}

	/**   
	 * <p>Title: findByEstadoOrderByNombre</p>   
	 * <p>Description: </p>   
	 * @param estado
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByEstadoOrderByNombre(java.lang.String)   
	 */
	
	@Override
	public List<TipoDestino> findByEstadoOrderByNombre(String estado) throws SQLException {
		
			List<TipoDestino> lstTipoDestino =null;
		
			
			if(estado == null || estado.trim().equals("")) {
				throw new SQLException ("El codigo de estado es obligatorio"); 
			}
			if (estado.matches("[^A|I]" ) ) {
	            throw new SQLException("Solo recibe las letras A e I");
	        }
			
			
			lstTipoDestino=tipoDestinoRepository.findByEstadoOrderByNombre(estado);//playa no funciona
			
		
			return lstTipoDestino;
		}

		@Override
		public TipoDestino guardarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws SQLException {

			TipoDestino tipoDestino = null;
			tipoDestino = new TipoDestino();
			// se arma el destino

			// tipoDestino.setIdTide(tipoDestinoDTO.getIdTide());
			//validacion codigo
			if (tipoDestinoDTO.getCodigo() == null || tipoDestinoDTO.getCodigo().trim().equals("")) {
				throw new SQLException("El tipo destino no puede ser nulo");
			} else if (!Utilities.isOnlyLetters(tipoDestinoDTO.getCodigo())) {
				throw new SQLException("El codigo solo recibe letras");
			} else if (tipoDestinoDTO.getCodigo().length() > 5) {
				throw new SQLException("El codigo de tipo destino solo puede tener longitud de 5");

			} else {
				tipoDestino.setCodigo(tipoDestinoDTO.getCodigo());
			}
			
			//validacion NOMBRE
			if (tipoDestinoDTO.getNombre() == null || tipoDestinoDTO.getNombre().trim().equals("")) {
				throw new SQLException("El tipo destino no puede ser nulo");
			} else if (!Utilities.isOnlyLetters(tipoDestinoDTO.getNombre())) {
				throw new SQLException("El nombre solo recibe letras");
			} else if (tipoDestinoDTO.getNombre().length() > 100) {
				throw new SQLException("El nombre solo puede tener una longitud de 100");

			} else {
				tipoDestino.setNombre(tipoDestinoDTO.getNombre());	
			}
			
			//VALIDACION DESCRIPCION
			if (tipoDestinoDTO.getDescripcion() == null || tipoDestinoDTO.getDescripcion().trim().equals("")) {
				throw new SQLException("El tipo destino no puede ser nulo");
			} else if (!Utilities.isOnlyLetters(tipoDestinoDTO.getDescripcion())) {
				throw new SQLException("El codigo solo recibe letras");
			} else if (tipoDestinoDTO.getDescripcion().length() > 300) {
				throw new SQLException("La descripcion solo puede tener una longitud de 300");

			} else {
				tipoDestino.setDescripcion(tipoDestinoDTO.getDescripcion());	
			}
			
			//VALIDACION FECHA CREACION
			if (tipoDestinoDTO.getFechaCreacion() == null) {
				throw new SQLException("La fecha de creacion de un cliente no puede ser nula");
			} else {
				tipoDestino.setFechaCreacion(tipoDestinoDTO.getFechaCreacion());
			}
			
			//VALIDACION DE USUCREADOR
			if (tipoDestinoDTO.getUsuCreador() == null || tipoDestinoDTO.getUsuCreador().trim().equals("")) {
				throw new SQLException("El nombre del creador de un cliente no puede ser nulo");
			} else if (tipoDestinoDTO.getUsuCreador().length() > 10) {
				throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
			} else {

				tipoDestino.setUsuCreador(tipoDestinoDTO.getUsuCreador());
			}

			// VALIDACION ESTADO
			if (tipoDestinoDTO.getEstado() == null || tipoDestinoDTO.getEstado().trim().equals("")) {
				throw new SQLException("El estado del cliente no puede ser nulo");
			} else if (Utilities.isNumeric(tipoDestinoDTO.getEstado())) {
				throw new SQLException("El estado no debe contener numeros");
			} else if (tipoDestinoDTO.getEstado().length() > 1) {
				throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
			} else {
				tipoDestino.setEstado(tipoDestinoDTO.getEstado());

			}

			tipoDestinoRepository.save(tipoDestino);
			
			return tipoDestino;
		}

		@Override
		public TipoDestino actualizarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws SQLException {

			TipoDestino tipoDestino = null;

			tipoDestino = findById(tipoDestinoDTO.getIdTide());

			//validacion
			if (tipoDestinoDTO==null || tipoDestinoDTO.getIdTide()==null) {
				throw new SQLException("El codigo tipo destino es obligatorio");
			}
			
			
			//validacion codigo
			if (tipoDestinoDTO.getCodigo() == null || tipoDestinoDTO.getCodigo().trim().equals("")) {
				throw new SQLException("El tipo destino no puede ser nulo");
			} else if (!Utilities.isOnlyLetters(tipoDestinoDTO.getCodigo())) {
				throw new SQLException("El codigo solo recibe letras");
			} else if (tipoDestinoDTO.getCodigo().length() > 5) {
				throw new SQLException("El codigo de tipo destino solo puede tener longitud de 5");

			} else {
				tipoDestino.setCodigo(tipoDestinoDTO.getCodigo());
			}
			
			//validacion NOMBRE
			if (tipoDestinoDTO.getNombre() == null || tipoDestinoDTO.getNombre().trim().equals("")) {
				throw new SQLException("El tipo destino no puede ser nulo");
			} else if (!Utilities.isOnlyLetters(tipoDestinoDTO.getNombre())) {
				throw new SQLException("El nombre del tipo destino solo recibe letras");
			} else if (tipoDestinoDTO.getNombre().length() > 100) {
				throw new SQLException("El nombre solo puede tener una longitud de 100");

			} else {
				tipoDestino.setNombre(tipoDestinoDTO.getNombre());	
			}
			
			
			//VALIDACION DESCRIPCION
			if (tipoDestinoDTO.getDescripcion() == null || tipoDestinoDTO.getDescripcion().trim().equals("")) {
				throw new SQLException("El tipo destino no puede ser nulo");
			} else if (!Utilities.isOnlyLetters(tipoDestinoDTO.getDescripcion())) {
				throw new SQLException("El codigo solo recibe letras");
			} else if (tipoDestinoDTO.getDescripcion().length() > 300) {
				throw new SQLException("La descripcion solo puede tener una longitud de 300");

			} else {
				tipoDestino.setDescripcion(tipoDestinoDTO.getDescripcion());	
			}
			
			
			//VALIDACION FECHA CREACION
			if (tipoDestinoDTO.getFechaCreacion() == null) {
				throw new SQLException("La fecha de creacion de un cliente no puede ser nula");
			} else {
				tipoDestino.setFechaCreacion(tipoDestinoDTO.getFechaCreacion());
			}
			
			
			// VALIDACION DE USUCREADOR
			if (tipoDestinoDTO.getUsuCreador() == null || tipoDestinoDTO.getUsuCreador().trim().equals("")) {
				throw new SQLException("El nombre del creador de un cliente no puede ser nulo");
			} else if (tipoDestinoDTO.getUsuCreador().length() > 10) {
				throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
			} else {

				tipoDestino.setUsuCreador(tipoDestinoDTO.getUsuCreador());
			}
			// VALIDACION ESTADO
			if (tipoDestinoDTO.getEstado() == null || tipoDestinoDTO.getEstado().trim().equals("")) {
				throw new SQLException("El estado del cliente no puede ser nulo");
			} else if (Utilities.isNumeric(tipoDestinoDTO.getEstado())) {
				throw new SQLException("El estado no debe contener numeros");
			} else if (tipoDestinoDTO.getEstado().length() > 1) {
				throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
			} else {
				tipoDestino.setEstado(tipoDestinoDTO.getEstado());

			}
			tipoDestinoRepository.save(tipoDestino);
			return tipoDestino;

		}

			public TipoDestino findById(Long idTide) throws SQLException{
				//validamoe el idedest con info
				if (idTide == null) {
					throw new SQLException("Debe ingresar un id destino");
				}
				
				if (!tipoDestinoRepository.findById(idTide).isPresent()) {
					throw new SQLException("El destino con id"+idTide+"no existe");
				}
				return tipoDestinoRepository.findById(idTide).get();
				
			}
		
			
			
			
			
			
		@Override
		public void eliminarTipoDestino(Long id)throws SQLException{
			
			if (id==null ) {
				throw new SQLException("El tipo destino es obligatorio");
			}
			Optional<TipoDestino>tipoDestinoBD = tipoDestinoRepository.findById(id);
			if (tipoDestinoBD.isPresent()) {
				tipoDestinoRepository.delete(tipoDestinoBD.get());
			}else {
				throw new SQLException("El destino no se encontro");
			}
	
		}
		

	}
	

