package br.com.security.TokenGenerator.service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import br.com.security.TokenGenerator.dto.TokenDTO;
import br.com.security.TokenGenerator.dto.TokenGeneratorDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenGeneratorService {

    final String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
    final Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS256.getJcaName());
    final Long daysToExpire = 7L;
    final Date expirationDate = Date.from(LocalDateTime.now().plusDays(daysToExpire).atZone(ZoneOffset.systemDefault()).toInstant()); // O token irá expirar daqui 7 dias



    public TokenDTO compactToken(TokenGeneratorDTO tokenGeneratorDTO){
        TokenDTO tokenDTO = new TokenDTO();

        String jwtToken = Jwts.builder()
        .claim("usuario", tokenGeneratorDTO.getUsuario())
        .claim("email", tokenGeneratorDTO.getEmail())
        .claim("codPerfil", tokenGeneratorDTO.getCodPerfil())
        .setSubject("TokenGenerator")
        .setId(UUID.randomUUID().toString())
        .setIssuedAt(expirationDate)
        .setExpiration(expirationDate)
        .signWith(hmacKey)
        .compact();

        tokenDTO.setToken(jwtToken);


        return tokenDTO;

    }

    public TokenGeneratorDTO descompactToken(String token){

        TokenGeneratorDTO descompact = new TokenGeneratorDTO();

        Jws<Claims> jwt = Jwts.parserBuilder()
        .setSigningKey(hmacKey)
        .build()
        .parseClaimsJws(token);

        descompact.setUsuario(jwt.getBody().get("usuario").toString());
        descompact.setEmail(jwt.getBody().get("email").toString());
        descompact.setCodPerfil(jwt.getBody().get("codPerfil").toString());

        return descompact;

    }
    
}
