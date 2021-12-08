package br.com.security.TokenGenerator.dto;

import lombok.Data;

@Data
public class TokenDTO {

    private String token;

    public String getToken(){
        return this.token;
    }
    public void setToken(String token){
        this.token = token;
    }

}
