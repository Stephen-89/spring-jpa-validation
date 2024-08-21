package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
