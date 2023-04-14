package com.api.cep.dto;

import com.api.cep.ExcludeFromJacocoGeneratedReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class Cep {

    @NotBlank(message = "O campo Cep é obrigatório")
    @Pattern(regexp="^\\d{5}\\-\\d{3}$|^\\d{8}$")
    private String cep;



}
