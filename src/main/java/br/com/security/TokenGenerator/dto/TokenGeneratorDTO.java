package br.com.security.TokenGenerator.dto;

import lombok.Data;

@Data
public class TokenGeneratorDTO {

    private String usuario;
    private String email;
    private int codPerfil;


    public String getUsuario(){
        return this.usuario;
    }
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public int getCodPerfil(){
        return this.codPerfil;
    }
    public void setCodPerfil(String usucodPerfilario){
        this.codPerfil = codPerfil;
    }
    
}
