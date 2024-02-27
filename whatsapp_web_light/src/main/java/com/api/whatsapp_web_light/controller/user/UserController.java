package com.api.whatsapp_web_light.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.whatsapp_web_light.dto.request.user.UserRequestDTO;
import com.api.whatsapp_web_light.dto.request.user.UserRequestLoginDTO;
import com.api.whatsapp_web_light.dto.request.user.UserRequestUpdateDTO;
import com.api.whatsapp_web_light.dto.response.user.TokenResponseDTO;
import com.api.whatsapp_web_light.dto.response.user.UserResponseDTO;
import com.api.whatsapp_web_light.service.user.TokenService;
import com.api.whatsapp_web_light.service.user.UserService;

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
