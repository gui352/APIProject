package com.apijava.javaAPI.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleUsuarioDTO {

    private Long id;

    private Long usuarios_id;

    private  String role_nome_role;
}
