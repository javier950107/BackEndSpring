package com.example.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.backend.models.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Long>{
}