package com.example.backend.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.backend.models.UserModel;

@Repository
public interface AuthRespository {
    public List<UserModel> getUser(String user);
    public void crateToken(Long idUser, String token);
}
