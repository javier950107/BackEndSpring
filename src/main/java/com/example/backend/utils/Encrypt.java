package com.example.backend.utils;

import org.springframework.stereotype.Component;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Component
public class Encrypt {
    public String encode(String password){
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        String hash = argon.hash(1,1024,1,password);
        return hash;
    }
}
