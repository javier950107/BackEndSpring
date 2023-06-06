package com.example.backend.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.backend.models.UserModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AuthRepositoryImp implements AuthRespository{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void crateToken(Long idUser, String token){
        UserModel userModel = entityManager.find(UserModel.class, idUser);
        userModel.setToken(token);
    }

    @Override
    public List<UserModel> validateUser(String user, String password) {
        TypedQuery<UserModel> query = entityManager
            .createQuery("FROM UserModel WHERE user = :user AND password = :password ",UserModel.class); 
        
            List<UserModel> userFound = query
            .setParameter("user", user)
            .setParameter("password", password)
            .getResultList();
        
        if (userFound.isEmpty()){
            return null;
        }else{
            return userFound;
        }       
    }
}
