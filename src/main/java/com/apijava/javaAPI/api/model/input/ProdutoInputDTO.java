package com.apijava.javaAPI.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInputDTO {

    private Long codigo;

    private String produto;

    private float valor_unitario;

    private Long quantidade;
}
