package com.ecommerce.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.restapi.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	
	User findUserByUserName(String userName);
}