package com.api.cep.model;

import com.api.cep.ExcludeFromJacocoGeneratedReport;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class Endereco {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

}
