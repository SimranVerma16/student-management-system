package com.guruji.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guruji.studentmanagement.dto.LoginRequest;
import com.guruji.studentmanagement.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        if ("admin".equals(request.getUsername()) &&
            "password".equals(request.getPassword())) {

            return jwtUtil.generateToken(request.getUsername());
        }

        return "Invalid credentials";
    }
}