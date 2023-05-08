package com.eksamen.intec_project;

import org.springframework.data.repository.CrudRepository;
import com.eksamen.intec_project.User;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    Optional<User> findById(int id);
}