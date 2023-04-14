package com.api.cep.controller;

import com.api.cep.dto.Cep;
import com.api.cep.dto.EnderecoDTO;
import com.api.cep.exceptions.EnderecoNaoEncontradoException;
import com.api.cep.model.Endereco;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class EnderecoController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/consulta-endereco")
    @Operation(summary = "Consulta o Endereço e valor do frete pelo Cep digitado")
    public ResponseEntity<EnderecoDTO> consultaEndereco(@RequestBody @Valid Cep cep){
        String url = "https://viacep.com.br/ws/" + cep.getCep() + "/json";
        Endereco endereco = restTemplate.getForObject(url, Endereco.class);

        if(endereco == null || endereco.getCep() == null){
            throw new EnderecoNaoEncontradoException("Endereço não encontrado para o CEP informado.");
        }

        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        Double frete = enderecoDTO.calcularFrete();
        enderecoDTO.setFrete(frete);

        return ResponseEntity.ok(enderecoDTO);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
