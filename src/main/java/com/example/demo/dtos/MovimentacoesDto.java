package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovimentacoesDto(@NotBlank String ativo,
                               @NotBlank String categoria,
                               @NotBlank String segmento,
                               @NotBlank String operacao,
                               @NotNull Double valorUnit,
                               @NotNull int quant,
                               @NotBlank String corretora){
}
