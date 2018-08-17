package com.prashant.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prashant.spring.common.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>{

	public ApplicationUser findByUserName(String userName);
}


