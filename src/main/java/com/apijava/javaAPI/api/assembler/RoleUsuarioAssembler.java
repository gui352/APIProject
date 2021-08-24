package com.apijava.javaAPI.api.assembler;

import com.apijava.javaAPI.api.model.RoleUsuarioDTO;
import com.apijava.javaAPI.api.model.input.RoleUsuarioInputDTO;
import com.apijava.javaAPI.domain.model.RolePessoa;
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

    public RolePessoa toEntity(RoleUsuarioInputDTO roleUsuarioInputDTO){
        return modelMapper.map(roleUsuarioInputDTO, RolePessoa.class);
    }

    public List<RoleUsuarioDTO> toCollectionModel(List<RolePessoa> rolePessoas) {
        return rolePessoas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public RoleUsuarioDTO toModel(RolePessoa rolePessoa){
        return modelMapper.map(rolePessoa, RoleUsuarioDTO.class);
    }

}