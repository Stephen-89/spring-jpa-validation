package com.example.test.repository.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.example.test.enums.RolesEnum;
import com.example.test.filter.UserFilter;
import com.example.test.model.Role;
import com.example.test.model.User;

public class UserSpecification {

	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";

	private UserSpecification() {
	}

	public static Specification<User> hasFirstName(String firstName) {
		return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> cb.equal(root.get(FIRST_NAME),
				firstName);
	}

	public static Specification<User> hasLastName(String lastName) {
		return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> cb.equal(root.get(LAST_NAME),
				lastName);
	}

	public static Specification<User> findByFirstNameAndLastName(UserFilter userFilter) {
        return (root, query, criteriaBuilder) -> {
        	List<Predicate> predicates = new ArrayList<>();
        	query.orderBy(criteriaBuilder.desc(root.get("username")));
            if(StringUtils.isNotEmpty(userFilter.getFirstName())) {
            	predicates.add(criteriaBuilder.equal(root.get(FIRST_NAME), userFilter.getFirstName()));
            }
            if(StringUtils.isNotEmpty(userFilter.getLastName())) {
            	predicates.add(criteriaBuilder.equal(root.get(LAST_NAME), userFilter.getLastName()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	public static Specification<User> findByFirstNameAndLastNameAndRole(UserFilter userFilter) {
        return (root, query, criteriaBuilder) -> {
        	Join<User, Role> rolesJoin = root.join("roles");
        	List<Predicate> predicates = new ArrayList<>();
        	query.orderBy(criteriaBuilder.asc(root.get("username")));
            if(StringUtils.isNotEmpty(userFilter.getFirstName())) {
            	predicates.add(criteriaBuilder.equal(root.get(FIRST_NAME), userFilter.getFirstName()));
            }
            if(StringUtils.isNotEmpty(userFilter.getLastName())) {
            	predicates.add(criteriaBuilder.equal(root.get(LAST_NAME), userFilter.getLastName()));
            }
            predicates.add(criteriaBuilder.equal(rolesJoin.get("name"), RolesEnum.USER.toString()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
