package com.surrius.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@Column(name = "user", length = 10, nullable = false)
	private String user;

	@Column(name = "secret", length = 10, nullable = false)
	private String secret;

}
