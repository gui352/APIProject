package com.apijava.javaAPI.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleUsuarioInputDTO {

    @NotNull
    private Long usuarios_id;

    @NotBlank
    private  String role_nome_role;


}
