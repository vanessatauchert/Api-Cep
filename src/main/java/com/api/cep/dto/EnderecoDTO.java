package com.api.cep.dto;

import com.api.cep.model.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoDTO {

    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Double frete;

    public EnderecoDTO(Endereco endereco) {}
}
