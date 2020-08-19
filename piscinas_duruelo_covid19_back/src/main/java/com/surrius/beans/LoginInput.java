package com.surrius.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO correspondiente a los campos de entrada del Login.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInput {

	// Usuario
	private String uid;
	// Password
	private String pwd;

}
