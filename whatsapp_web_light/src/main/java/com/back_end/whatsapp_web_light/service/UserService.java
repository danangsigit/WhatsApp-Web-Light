package com.back_end.whatsapp_web_light.service;

import com.back_end.whatsapp_web_light.dto.request.UserRequestDTO;
import com.back_end.whatsapp_web_light.dto.request.UserRequestUpdateDTO;
import com.back_end.whatsapp_web_light.dto.response.UserResponseDTO;
import com.back_end.whatsapp_web_light.entity.UserEntity;
import com.back_end.whatsapp_web_light.repository.UserRepository;

import java.util.Optional;

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
            BeanUtils.copyProperties(userRequestUpdateDTO, user);
            UserResponseDTO userResponseDTO = new UserResponseDTO(); 
            BeanUtils.copyProperties(userRepository.save(user), userResponseDTO);
            return userResponseDTO;
        }
        return null;
    }

}
