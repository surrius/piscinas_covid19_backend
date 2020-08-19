package com.surrius.beans;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase generica a todas las respuestas de la API para informar del estado de 
 * la peticion en cuanto a tiempo, resultado y errores.
 * 
 */
@Data
@AllArgsConstructor
public class StatusResponse {
	private long elapsedTime;
	private String status;
	private int errorCode;
	private String message;
	private JsonNode data;
	
	public StatusResponse() {
		this.elapsedTime = 0;
		this.status = "";
		this.errorCode = 0;
		this.message = "";
		this.data = null;
	}
	
}
