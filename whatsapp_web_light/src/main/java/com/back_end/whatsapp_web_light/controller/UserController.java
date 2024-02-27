package com.back_end.whatsapp_web_light.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_end.whatsapp_web_light.dto.request.UserRequestDTO;
import com.back_end.whatsapp_web_light.dto.request.UserRequestLoginDTO;
import com.back_end.whatsapp_web_light.dto.request.UserRequestUpdateDTO;
import com.back_end.whatsapp_web_light.dto.response.TokenResponseDTO;
import com.back_end.whatsapp_web_light.dto.response.UserResponseDTO;
import com.back_end.whatsapp_web_light.service.TokenService;
import com.back_end.whatsapp_web_light.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/user")
public class UserController {
    
    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public UserResponseDTO createUser (@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @GetMapping("/name/{name}")
    public UserResponseDTO findByName (@PathVariable String name) {
        return userService.findByName(name);
    }

    @GetMapping("/id/{id}")
    public UserResponseDTO findById (@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/update")
    public UserResponseDTO update (HttpServletRequest request, @RequestBody @Valid UserRequestUpdateDTO userRequestUpdateDTO) {
        
        // Extraindo nome do usuario logado
        var token = tokenService.recoverToken(request);
        var user_name = tokenService.validateToken(token);
    
        return userService.update(user_name, userRequestUpdateDTO);
    }

    @PostMapping("/login")
    public TokenResponseDTO loginUser (@RequestBody @Valid UserRequestLoginDTO userRequesLogintDTO) {
        return userService.login(userRequesLogintDTO);
    }

}
