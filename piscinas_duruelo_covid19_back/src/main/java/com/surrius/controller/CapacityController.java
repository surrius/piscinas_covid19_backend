package com.surrius.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surrius.beans.LoginInput;
import com.surrius.beans.StatusResponse;
import com.surrius.config.PiscinasDurueloLoginException;
import com.surrius.dao.OccupationDAO;
import com.surrius.dao.UserDAO;
import com.surrius.entity.Occupation;
import com.surrius.entity.User;
import com.surrius.util.UtilityClass;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CapacityController {

	@Autowired
	OccupationDAO occupationDAO;

	@Autowired
	UserDAO userDAO;

	private static final String ERROR = "error";

	/**
	 * Recoge el Aforo total de la psicina tanto para: - La zona de Baño de cada
	 * piscina. - El área de ámbito general de la piscina.
	 * 
	 * @return
	 */
	@GetMapping("capacity")
	public StatusResponse getCapacity(@RequestParam String dateControl) {
		long start = System.currentTimeMillis();
		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatus("success");
		log.info("Endpoint: capacity:" + dateControl);

		try {
			ObjectMapper oMapper = new ObjectMapper();

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(dateControl);

			// Recuperamos para la fecha facilitada la información de ocupación
			Occupation occupation = occupationDAO.findByDateControl(date);
			if (null == occupation) {
				statusResponse.setErrorCode(33);
				statusResponse.setStatus("warning");
				statusResponse.setMessage("No se han encontrado datos para la fecha indicada: " + dateControl
						+ ". Se procederá a crear un nuevo registro.");
			} else {
				statusResponse.setData(oMapper.valueToTree(occupation));
			}

			log.info(oMapper.valueToTree(statusResponse).toPrettyString());
		} catch (Exception e) {
			log.error(Arrays.toString(e.getStackTrace()));
			statusResponse.setErrorCode(30);
			statusResponse.setStatus(ERROR);
			statusResponse.setMessage("Error en servidor al recuperar capacidad de las piscinas");
		}

		long finish = System.currentTimeMillis();
		statusResponse.setElapsedTime(finish - start);

		return statusResponse;
	}

	/**
	 * Graba los datos de ocupacion de la zona de baño para cada una de las
	 * piscinas.
	 * 
	 * @return
	 */
	@PostMapping("capacity/pools")
	public StatusResponse setPoolsCapacity(@RequestBody Occupation occupation, @RequestParam String uid,
			@RequestParam String pwd) {
		long start = System.currentTimeMillis();
		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatus("success");
		log.info("Endpoint: capacity/pools: " + occupation);

		try {
			// Checkea si el usuario tiene acceso a la petición
			LoginInput loginInput = new LoginInput(uid, pwd);
			User user = userDAO.findByUser(loginInput.getUid());
			UtilityClass.checkAcces(pwd, user);

			// Salvamos el objeto en BBDD con el contador de las piscinas.
			Occupation occupationToSave = occupationDAO.findByDateControl(occupation.getDateControl());

			if (null == occupationToSave) {
				occupationToSave = occupation;
			} else {
				occupationToSave.setBigPool(occupation.getBigPool());
				occupationToSave.setMediumPool(occupation.getMediumPool());
				occupationToSave.setSmallPool(occupation.getSmallPool());
			}

			// Seteamos usuario y fecha de modifiación
			occupationToSave.setUpdateUser(user.getUser());
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			Date now = new Date(stamp.getTime());
			occupationToSave.setUpdateDate(now);

			occupationDAO.save(occupationToSave);

			// Devolver el dato actualizado
			ObjectMapper oMapper = new ObjectMapper();
			statusResponse.setData(oMapper.valueToTree(occupationToSave));
			statusResponse.setMessage("Los datos se han guardado correctamente.");
		} catch (PiscinasDurueloLoginException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			statusResponse.setErrorCode(32);
			statusResponse.setStatus(ERROR);
			statusResponse.setMessage(e.getMessage());
		} catch (Exception e2) {
			log.error(Arrays.toString(e2.getStackTrace()));
			statusResponse.setErrorCode(31);
			statusResponse.setStatus(ERROR);
			statusResponse.setMessage("Error en servidor al salvar ocupación. Inténtelo de nuevo mas tarde.");
		}

		long finish = System.currentTimeMillis();
		statusResponse.setElapsedTime(finish - start);

		log.info(statusResponse.toString());

		return statusResponse;
	}

	/**
	 * Graba los datos de ocupacion del total de las piscinas.
	 * 
	 * @return
	 */
	@PostMapping("capacity/solarium")
	public StatusResponse setGeneralCapacity(@RequestBody Occupation occupation, @RequestParam String uid,
			@RequestParam String pwd) {
		long start = System.currentTimeMillis();
		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatus("success");

		log.info("Endpoint: capacity/solarium: " + occupation);
		log.info("Endpoint: capacity/solarium: user" + uid + "-" + pwd);

		try {
			// Checkea si el usuario tiene acceso a la petición
			LoginInput loginInput = new LoginInput(uid, pwd);
			User user = userDAO.findByUser(loginInput.getUid());
			UtilityClass.checkAcces(pwd, user);

			// Salvamos el objeto en BBDD con el contador del solarium.
			Occupation occupationToSave = occupationDAO.findByDateControl(occupation.getDateControl());

			if (null == occupationToSave) {
				occupationToSave = occupation;
			} else {
				occupationToSave.setSolarium(occupation.getSolarium());
				occupationToSave.setWaterTemp(occupation.getWaterTemp());
			}

			// Seteamos usuario y fecha de modifiación
			occupationToSave.setUpdateUser(user.getUser());
			Timestamp stamp = new Timestamp(System.currentTimeMillis());
			Date now = new Date(stamp.getTime());
			occupationToSave.setUpdateDate(now);

			occupationDAO.save(occupationToSave);

			// Devolver el dato actualizado
			ObjectMapper oMapper = new ObjectMapper();
			statusResponse.setData(oMapper.valueToTree(occupationToSave));
			statusResponse.setMessage("Los datos se han guardado correctamente.");
		} catch (PiscinasDurueloLoginException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			statusResponse.setErrorCode(32);
			statusResponse.setStatus(ERROR);
			statusResponse.setMessage(e.getMessage());
		} catch (Exception e2) {
			log.error(Arrays.toString(e2.getStackTrace()));
			statusResponse.setErrorCode(32);
			statusResponse.setStatus(ERROR);
			statusResponse.setMessage("Error en servidor al salvar ocupación. Inténtelo de nuevo mas tarde.");
		}

		long finish = System.currentTimeMillis();
		statusResponse.setElapsedTime(finish - start);

		log.info(statusResponse.toString());

		return statusResponse;
	}

}
