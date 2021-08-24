package com.apijava.javaAPI.domain.service;

import com.apijava.javaAPI.api.assembler.RoleUsuarioAssembler;
import com.apijava.javaAPI.api.model.RoleUsuarioDTO;
import com.apijava.javaAPI.domain.exception.NegocioException;
import com.apijava.javaAPI.domain.model.RolePessoa;
import com.apijava.javaAPI.domain.repository.RoleUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleUsuarioService {

    private RoleUsuarioRepository roleUsuarioRepository;
    private RoleUsuarioAssembler roleUsuarioAssembler;

    @Transactional
    public RolePessoa cadastrar(RolePessoa rolePessoa){return roleUsuarioRepository.save(rolePessoa);}

    public List<RoleUsuarioDTO> listar(){
        return roleUsuarioAssembler.toCollectionModel(roleUsuarioRepository.findAll());
    }

    public RolePessoa buscar(Long roleId){
        return roleUsuarioRepository.findById(roleId)
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<RoleUsuarioDTO> buscarId(Long roleId){
        return roleUsuarioRepository.findById(roleId)
                .map( roleUsuario ->
                        ResponseEntity.ok(roleUsuarioAssembler.toModel(roleUsuario))
                )
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<RoleUsuarioDTO> editar(Long roleId, RolePessoa rolePessoa){
        if (!roleUsuarioRepository.existsById(roleId)){
            throw new NegocioException("Pessoa inexistente.");
        }
        RolePessoa rolePessoa1 = this.buscar(roleId);
        rolePessoa.setId(roleId);
        rolePessoa.setPessoas_codigo(rolePessoa.getPessoas_codigo());
        rolePessoa = roleUsuarioRepository.save(rolePessoa);
        return ResponseEntity.ok(roleUsuarioAssembler.toModel(rolePessoa));
    }

}
