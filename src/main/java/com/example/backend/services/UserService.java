package com.example.backend.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.models.UserModel;
import com.example.backend.repositories.UserRepository;
import com.example.backend.utils.Encrypt;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Encrypt encrypt;

    
    // set the a new user
    public ResponseEntity<UserModel> setNewUser(UserModel user){
        try {
            user.setPassword(encrypt.encode(user.getPassword()));
            UserModel newUser = userRepository.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);   
        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public Iterable<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<Map<String,Object>> deleteUserById(Long id){
        Optional<UserModel> foundUser = userRepository.findById(id);

        if (foundUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exists!");
        }else{
            userRepository.deleteById(id);
            Map<String, Object> map = new HashMap<>();
            map.put("status", 1);
            map.put("msg", "Success deleted");
            map.put("data", null);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }
}


// Login jwt
// Datatype middleware
// joins