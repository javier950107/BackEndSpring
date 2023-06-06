package com.example.backend.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.models.UserModel;
import com.example.backend.repositories.AuthRespository;
import com.example.backend.utils.Encrypt;

@Service
public class AuthService {
    @Autowired
    private AuthRespository authRespository;
    @Autowired
    private Encrypt encrypt;

    public ResponseEntity<Map<String, Object>> onAuthUser(String user, String password){
        try{
            String encryptPassword = encrypt.encode(password);
            System.out.println(encryptPassword);
            List<UserModel> listUser = authRespository.validateUser(user, encryptPassword);
            //TODO: Map to utils
            if(listUser == null){
                Map<String,Object> response = new HashMap<>();
                response.put("status", 0);
                response.put("message", "User doesn't exists");
                response.put("token", null );
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }else{
                Long idUser = listUser.get(0).getId();
                String token = "Token";
                authRespository.crateToken(idUser, token);
    
                Map<String,Object> response = new HashMap<>();
                response.put("status", 1);
                response.put("message", "Here is your token!");
                response.put("token", token);
    
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            }
        }catch(Exception err){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
