package com.surrius.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Table(name = "occupation")
@DynamicInsert
@DynamicUpdate
@Data
public class Occupation {

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name = "dateControl", nullable = false)
	private Date dateControl;

	@Column(name = "smallPool", length = 100)
	private Integer smallPool;
	
	@Column(name = "mediumPool", length = 100)
	private Integer mediumPool;
	
	@Column(name = "bigPool", length = 100)
	private Integer bigPool;
	
	@Column(name = "solarium", length = 1000)
	private Integer solarium;
	
	@Column(name = "waterTemp", length = 100)
	private Float waterTemp;
	
	@Column(name = "updateUser", length = 1000)
	private String updateUser;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updateDate", length = 1000)
	private Date updateDate;

}
