package com.apijava.javaAPI.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProdutoDTO {

    private Long codigo;

    private String produto;

    private Long quantidade;

    private float valor_unitario;

    private BigDecimal valor_total_em_estoque;
}
