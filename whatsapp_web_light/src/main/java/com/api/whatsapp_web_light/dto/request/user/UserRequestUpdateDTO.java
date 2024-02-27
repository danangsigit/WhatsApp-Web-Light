package com.api.whatsapp_web_light.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public class UserRequestUpdateDTO {
    
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
