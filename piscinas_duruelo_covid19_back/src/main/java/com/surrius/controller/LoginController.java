package com.surrius.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surrius.beans.LoginInput;
import com.surrius.beans.StatusResponse;
import com.surrius.config.PiscinasDurueloLoginException;
import com.surrius.dao.UserDAO;
import com.surrius.entity.User;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RequestMapping("/api/v1")
@RestController
@Slf4j
public class LoginController {

	@Autowired
	UserDAO userDAO;

	private static final String ERROR = "error";

	/**
	 * Metodo de control del login de usuario.
	 * 
	 * @param user - Credenciales de acceso
	 * @return Respuesta tras comprobación de usuario
	 */
	@PostMapping("/auth")
	public StatusResponse loginValidation(@RequestBody LoginInput loginInput) {
		long start = System.currentTimeMillis();
		StatusResponse statusResponse = new StatusResponse();
		statusResponse.setStatus("success");

		log.info("Intento de Login del usuario: " + loginInput.getUid());

		try {
			User user = userDAO.findByUser(loginInput.getUid());

			// Validaciones previas
			checkAcces(loginInput.getPwd(), user);
			statusResponse.setData(null);

		} catch (PiscinasDurueloLoginException e) {
			statusResponse.setErrorCode(20);
			statusResponse.setStatus(ERROR);
			statusResponse.setMessage(e.getMessage());
		} catch (Exception e2) {
			log.error(Arrays.toString(e2.getStackTrace()));
			statusResponse.setErrorCode(90);
			statusResponse.setStatus(ERROR);
			statusResponse.setMessage(
					"Error de la aplicación. Si el problema persiste, notifique a un responsable de las piscinas");
		}

		long finish = System.currentTimeMillis();
		statusResponse.setElapsedTime(finish - start);

		return statusResponse;
	}

	/**
	 * Control de usuario. Determina si es un usuario valido con acceso al producto.
	 * 
	 * @param pwd  - Contraseña facilitada por el usuario
	 * @param user - Usuario recuperado de la BBDD
	 * @return
	 * @throws PiscinasDurueloLoginException
	 */
	private void checkAcces(String pwd, User user) throws PiscinasDurueloLoginException {

		// Control de usuario introducido existente
		if (null == user) {
			log.error("Usuario no encontrado en BBDD");
			throw new PiscinasDurueloLoginException("Usuario no encontrado o credenciales incorrectas.");
		}

		// Control de contraseña correcta
		if (!user.getSecret().equals(pwd)) {
			log.error("Contraseña introducida por el usuario incorrecta");
			throw new PiscinasDurueloLoginException("Usuario no encontrado o credenciales incorrectas.");
		}

	}

}
