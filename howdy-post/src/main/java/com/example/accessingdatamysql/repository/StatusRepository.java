package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.model.Status;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface StatusRepository extends CrudRepository<Status, Integer> {

}