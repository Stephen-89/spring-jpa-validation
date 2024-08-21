package com.example.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.test.model.User;

@Repository
public interface UserAnnotationRepository extends JpaRepository<User, Long> {
	
    @Query("SELECT u FROM User u WHERE u.firstName = ?1 AND u.lastName = ?2")
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT * FROM users WHERE firstName = ?1 AND lastName = ?2", nativeQuery = true)
    List<User> findByFirstNameAndLastNameNative(String firstName, String lastName);
	
}
