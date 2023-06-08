package com.example.backend.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Response {
    public Map<String,Object> getResponse(String reason, Object data){
        Map<String, Object> response = new HashMap<>();
        response.put("reason", reason);
        response.put("data", data);
        return response;
    }
}
