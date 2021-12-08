package br.com.security.TokenGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.TokenGenerator.dto.TokenDTO;
import br.com.security.TokenGenerator.dto.TokenGeneratorDTO;
import br.com.security.TokenGenerator.service.TokenGeneratorService;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0
 * @author Lucas da Silva Novais
 */
@CrossOrigin(origins = "http://localhost:8090/TokenGenerator")
@RestController
@AllArgsConstructor
@RequestMapping("/**")
public class TokenGeneratorController {

    private static Logger logger = LoggerFactory.getLogger(TokenGeneratorController.class);

    @Autowired
    TokenGeneratorService tokenGeneratorService;

    TokenDTO tokenDTO;

    TokenGeneratorDTO tokenGeneratorDTO;


    @PostMapping(path = "/GerarToken")
    public ResponseEntity<?> gerarToken(@RequestBody TokenGeneratorDTO tokenGeneratorDTO){
        try{

            tokenDTO = tokenGeneratorService.compactToken(tokenGeneratorDTO);
            logger.info("TokenGeneratorController.gerarToken - Token gerado com sucesso!\n"+tokenDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(tokenDTO);

        }catch(NullPointerException npe){
            logger.error("PessoaController.save - Retorno nulo. Falha ao gerar Token!", npe);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tokenGeneratorDTO);
        }catch(Exception e){
            logger.error("PessoaController.save - Falha ao gerar Token.", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tokenGeneratorDTO);
        }
    }

    @GetMapping(path = "/DescompactarToken/{token}")
    public ResponseEntity<?> descompactarToken(@PathVariable String token){
        try{

            tokenGeneratorDTO = tokenGeneratorService.descompactToken(token);
            logger.info("TokenGeneratorController.gerarToken - Token descompactado com sucesso!\n"+tokenGeneratorDTO);

            return ResponseEntity.ok(tokenGeneratorDTO);

        }catch(NullPointerException npe){
            logger.error("PessoaController.save - Retorno nulo. Falha ao descompactar Token!", npe);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tokenGeneratorDTO);
        }catch(Exception e){
            logger.error("PessoaController.save - Falha ao descompactar Token.", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tokenGeneratorDTO);
        }
    }


    
}
