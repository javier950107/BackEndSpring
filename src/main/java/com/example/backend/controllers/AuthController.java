package com.example.backend.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.backend.services.AuthService;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Map<String,Object>> onAuthUser(@RequestParam("user") String user, @RequestParam("password") String password){
        return authService.onAuthUser(user, password);
    }
}
