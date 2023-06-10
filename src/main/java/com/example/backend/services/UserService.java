package com.example.backend.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.models.UserModel;
import com.example.backend.repositories.UserRepository;
import com.example.backend.utils.Encrypt;
import com.example.backend.utils.Response;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Encrypt encrypt;
    @Autowired
    private Response response;

    
    // set the a new user
    public ResponseEntity<Map<String,Object>> setNewUser(UserModel user){
        try {
            if (!userRepository.validateUserExists(user.getUser(), user.getEmail())){
                user.setPassword(encrypt.encode(user.getPassword()));
                userRepository.save(user);
                return new ResponseEntity<>(response.getResponse("User registered with success!", null), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(response
                    .getResponse("User register with the same user or email", null), HttpStatus.CONFLICT);
            }
   
        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<Map<String,Object>> deleteUserById(Long id){
        boolean foundUser = userRepository.findById(id);

        try {
            if (!foundUser){
                return new ResponseEntity<>(response.getResponse("User doesn't exists", null), HttpStatus.NOT_FOUND);
            }else{
                userRepository.deleteById(id);
                return new ResponseEntity<>(response.getResponse("Success deleted", null), HttpStatus.OK);
            }   
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}


// Login jwt -> Validation
// Microservices
// READY TO THE PLAN!!