package com.apijava.javaAPI.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "role_pessoas")
public class RolePessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role_nome_role;

    private Long pessoas_codigo;

}
