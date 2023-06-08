package com.example.backend.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.backend.models.UserModel;

@Repository
public interface UserRepository{
    public void save(UserModel userModel);
    public List<UserModel> findAll();
    public boolean findById(Long id);
    public void deleteById(Long id);
    public boolean validateUserExists(String user, String email);
}