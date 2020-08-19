package com.surrius.util;

import com.surrius.config.PiscinasDurueloLoginException;
import com.surrius.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilityClass {
	
	private UtilityClass() {}
	
	/**
	 * Control de usuario. Determina si es un usuario valido con acceso al producto.
	 * 
	 * @param pwd  - Contraseña facilitada por el usuario
	 * @param user - Usuario recuperado de la BBDD
	 * @return
	 * @throws PiscinasDurueloLoginException
	 */
	public static void checkAcces(String pwd, User user) throws PiscinasDurueloLoginException {

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
