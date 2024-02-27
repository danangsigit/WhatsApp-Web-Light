package com.api.whatsapp_web_light.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public class UserRequestLoginDTO {
    
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
