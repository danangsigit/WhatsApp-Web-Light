package com.back_end.whatsapp_web_light.service;

import com.back_end.whatsapp_web_light.dto.request.UserRequestDTO;
import com.back_end.whatsapp_web_light.dto.response.UserResponseDTO;
import com.back_end.whatsapp_web_light.entity.UserEntity;
import com.back_end.whatsapp_web_light.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO createUser (UserRequestDTO userRequestDTO) {
        if (userRequestDTO != null) {
            UserEntity userEntity = new UserEntity();
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

}
