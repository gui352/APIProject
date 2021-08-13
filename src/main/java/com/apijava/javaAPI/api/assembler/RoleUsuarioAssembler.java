package com.apijava.javaAPI.api.assembler;

import com.apijava.javaAPI.api.model.RoleUsuarioDTO;
import com.apijava.javaAPI.api.model.input.RoleUsuarioInputDTO;
import com.apijava.javaAPI.domain.model.RoleUsuario;
import com.apijava.javaAPI.domain.repository.RoleUsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RoleUsuarioAssembler {

    private ModelMapper modelMapper;
    private RoleUsuarioRepository roleUsuarioRepository;

    public RoleUsuario toEntity(RoleUsuarioInputDTO roleUsuarioInputDTO){
        return modelMapper.map(roleUsuarioInputDTO, RoleUsuario.class);
    }

    public List<RoleUsuarioDTO> toCollectionModel(List<RoleUsuario> roleUsuarios) {
        return roleUsuarios.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public RoleUsuarioDTO toModel(RoleUsuario roleUsuario){
        return modelMapper.map(roleUsuario, RoleUsuarioDTO.class);
    }

}