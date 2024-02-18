package com.back_end.whatsapp_web_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.whatsapp_web_light.dto.request.UserRequestDTO;
import com.back_end.whatsapp_web_light.dto.response.UserResponseDTO;
import com.back_end.whatsapp_web_light.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public UserResponseDTO createUser (@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }
}
