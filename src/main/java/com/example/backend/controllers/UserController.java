package com.example.backend.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.backend.models.UserModel;
import com.example.backend.services.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Map<String,Object>> setNewUser(@ModelAttribute UserModel user){
        return userService.setNewUser(user);
    }

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody Iterable<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users/delete", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Map<String,Object>> deleteUserById(@RequestParam("id") Long id){
        return userService.deleteUserById(id);
    }
}
