package com.surrius.config;

/**
 * Excepción para controlar posibles errores ocasionados durante el proceso
 * de loggeo del usuario a la aplicación.
 * 
 * @author crubio
 *
 */
public class PiscinasDurueloLoginException extends Exception {

	private static final long serialVersionUID = 1L;

	public PiscinasDurueloLoginException(String mensaje) {
		super(mensaje);
	}

	public PiscinasDurueloLoginException() {
		super();
	}

}
