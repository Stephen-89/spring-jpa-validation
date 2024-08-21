package com.example.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.model.User;

@Repository
public interface UserMethodRepository extends JpaRepository<User, Long> {
	
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
	
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
    
}
