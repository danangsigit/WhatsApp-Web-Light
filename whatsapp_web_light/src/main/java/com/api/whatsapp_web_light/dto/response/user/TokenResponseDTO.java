package com.api.whatsapp_web_light.dto.response.user;

public class TokenResponseDTO {
    
    private String token;

    public TokenResponseDTO (String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
