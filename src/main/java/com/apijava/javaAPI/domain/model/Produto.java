package com.apijava.javaAPI.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Valid
    @NotNull
    private float valor_total_em_estoque;

    @Valid
    @NotNull
    private float valor_unitario;

    @Valid
    @NotNull
    private Long quantidade;

    @Valid
    @NotNull
    private  String produto;
}
