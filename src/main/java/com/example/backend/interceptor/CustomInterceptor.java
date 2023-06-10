package com.example.backend.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.backend.utils.JWTUtil;
import com.example.backend.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    Response responseUtil;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        // This method is called before the actual handler is executed.
        // You can perform pre-processing here.
        String token = request.getHeader("Authorization");
        
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseUtil.getResponse("Unauthorized ", null)));
            return false;
        }
        
        String userId = jwtUtil.getKey(token);
        if(userId == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseUtil.getResponse("Invalidad token or session expired! ", null)));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // This method is called after the handler is executed but before the view is rendered.
        // You can perform post-processing here.
        System.out.println("Post-handle method is called");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // This method is called after the complete request has finished and the view has been rendered (if applicable).
        // You can perform cleanup or logging here.
        System.out.println("After-completion method is called");
    }
}
