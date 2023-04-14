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

    public EnderecoDTO(Endereco endereco) {
        cep = endereco.getCep();
        rua = endereco.getLogradouro();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        cidade = endereco.getLocalidade();
        estado = endereco.getUf();

    }

    public Double calcularFrete() {
        if (estado.equals("SP") || estado.equals("RJ") || estado.equals("MG") || estado.equals("ES")) {
            return 7.85;
        } else if (estado.equals("DF") || estado.equals("GO") || estado.equals("MT") || estado.equals("MS")) {
            return 12.50;
        } else if (estado.equals("AL") || estado.equals("BA") || estado.equals("CE") || estado.equals("MA") ||
                estado.equals("PB") || estado.equals("PE") || estado.equals("PI") || estado.equals("RN") ||
                estado.equals("SE")) {
            return 15.98;
        } else if (estado.equals("PR") || estado.equals("SC") || estado.equals("RS")) {
            return 17.30;
        } else if (estado.equals("AC") || estado.equals("AP") || estado.equals("AM") || estado.equals("PA") ||
                estado.equals("RO") || estado.equals("RR") || estado.equals("TO")) {
            return 20.83;
        } else {
            return null;
        }
    }
}
