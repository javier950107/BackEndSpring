package com.example.backend.utils;

import org.springframework.stereotype.Component;

import com.password4j.Hash;
import com.password4j.Password;

@Component
public class Encrypt {
    public String encode(String password){
        Hash hash = Password.hash(password).addRandomSalt(12).withScrypt();
        return hash.getResult();
    }

    public boolean verify(String rawPassword, String passwordUser){
        return Password.check(rawPassword, passwordUser).withScrypt();
    }
}
