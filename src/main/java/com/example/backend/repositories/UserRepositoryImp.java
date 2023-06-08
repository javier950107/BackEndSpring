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
public class UserRepositoryImp implements UserRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(UserModel userModel){
        entityManager.merge(userModel);
    }

    @Override
    public List<UserModel> findAll(){
        TypedQuery<UserModel> query = entityManager
        .createQuery("FROM UserModel",UserModel.class);
        return query.getResultList();
    }

    @Override
    public boolean findById(Long id){
        UserModel userModel = entityManager.find(UserModel.class, id);
        return (userModel != null)? true : false;
    }

    @Override
    public void deleteById(Long id){
        UserModel userModel = entityManager.find(UserModel.class, id);
        entityManager.remove(userModel);
    }

    @Override
    public boolean validateUserExists(String user, String email){
        TypedQuery<UserModel> query = entityManager
        .createQuery("FROM UserModel WHERE user = :user OR email = :email", UserModel.class)
        .setParameter("user", user)
        .setParameter("email", email);

        if (!query.getResultList().isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}

