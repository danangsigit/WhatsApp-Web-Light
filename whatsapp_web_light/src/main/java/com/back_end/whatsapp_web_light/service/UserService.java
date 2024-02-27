package com.back_end.whatsapp_web_light.service;

import com.back_end.whatsapp_web_light.dto.request.UserRequestDTO;
import com.back_end.whatsapp_web_light.dto.request.UserRequestLoginDTO;
import com.back_end.whatsapp_web_light.dto.request.UserRequestUpdateDTO;
import com.back_end.whatsapp_web_light.dto.response.TokenResponseDTO;
import com.back_end.whatsapp_web_light.dto.response.UserResponseDTO;
import com.back_end.whatsapp_web_light.entity.UserEntity;
import com.back_end.whatsapp_web_light.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserResponseDTO createUser (UserRequestDTO userRequestDTO) {
        if (userRequestDTO != null) {
            UserEntity userEntity = new UserEntity();
            
            String encryptPassword = new BCryptPasswordEncoder().encode(userRequestDTO.getPassword());
            userRequestDTO.setPassword(encryptPassword);
            
            BeanUtils.copyProperties(userRequestDTO, userEntity);
            UserResponseDTO userResponseDTO = new UserResponseDTO(); 
            BeanUtils.copyProperties(userRepository.save(userEntity), userResponseDTO);
            return userResponseDTO;
        }
        return null;
    }
    
    public UserResponseDTO findByName (String name) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        UserEntity user = userRepository.findByName(name);

        if (user != null) {
            BeanUtils.copyProperties(user, userResponseDTO);
            return userResponseDTO;
        }
        return null;
    }

    public UserResponseDTO findById (Long id_user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        UserEntity user = userRepository.findById(id_user).orElse(null);
        
        if (user != null) {
            BeanUtils.copyProperties(user, userResponseDTO);
            return userResponseDTO;
        }
        return null;
    }

    public UserResponseDTO update (String user_name, UserRequestUpdateDTO userRequestUpdateDTO) {
        UserEntity user = userRepository.findByName(user_name);
        if (userRequestUpdateDTO != null && user != null) {
            String encryptPassword = new BCryptPasswordEncoder().encode(userRequestUpdateDTO.getPassword());
            userRequestUpdateDTO.setPassword(encryptPassword);

            BeanUtils.copyProperties(userRequestUpdateDTO, user);
            UserResponseDTO userResponseDTO = new UserResponseDTO(); 
            BeanUtils.copyProperties(userRepository.save(user), userResponseDTO);
            return userResponseDTO;
        }
        return null;
    }

    public TokenResponseDTO login (UserRequestLoginDTO userRequestLoginDTO) {
        
        var usernamePassword = new UsernamePasswordAuthenticationToken(userRequestLoginDTO.getName(), userRequestLoginDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO(token); 
        return tokenResponseDTO;
    }

}
