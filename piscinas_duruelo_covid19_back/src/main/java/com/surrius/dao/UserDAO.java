package com.surrius.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.surrius.entity.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
 
    public User findByUser(String user);
 
}
