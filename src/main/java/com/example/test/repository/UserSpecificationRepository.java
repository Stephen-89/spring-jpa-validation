package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.test.model.User;

@Repository
public interface UserSpecificationRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
}
