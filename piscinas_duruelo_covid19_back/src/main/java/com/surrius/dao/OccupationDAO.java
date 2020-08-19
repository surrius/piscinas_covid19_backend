package com.surrius.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.surrius.entity.Occupation;

@Repository
public interface OccupationDAO extends CrudRepository<Occupation, Long> {

	public Occupation findByDateControl(Date dateControl);

}
