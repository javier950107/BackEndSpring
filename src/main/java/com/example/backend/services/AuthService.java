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
import com.example.backend.utils.Response;

@Service
public class AuthService {
    @Autowired
    private AuthRespository authRespository;
    @Autowired
    private Encrypt encrypt;
    @Autowired
    private Response response;

    public ResponseEntity<Map<String, Object>> onAuthUser(String user, String password){
        try{
            List<UserModel> listUser = authRespository.getUser(user);

            if(listUser == null){
                return new ResponseEntity<>(response.getResponse("User doesn't exists", null), HttpStatus.NOT_FOUND);
            }else{
                // check if is the user
                String passwordUser = listUser.get(0).getPassword();
                if(encrypt.verify(password, passwordUser)){
                    // TODO Create token
                    Long idUser = listUser.get(0).getId();
                    String token = "Token";
                    authRespository.crateToken(idUser, token);
        
                    return new ResponseEntity<>(response.getResponse("Here is your token", token), HttpStatus.ACCEPTED);
                }else{        
                    return new ResponseEntity<>(response.getResponse("Wrong password!", null), HttpStatus.UNAUTHORIZED);
                }
            }
        }catch(Exception err){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
