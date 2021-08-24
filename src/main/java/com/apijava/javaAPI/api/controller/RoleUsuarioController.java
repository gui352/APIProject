package com.apijava.javaAPI.api.controller;

import com.apijava.javaAPI.api.assembler.RoleUsuarioAssembler;
import com.apijava.javaAPI.api.model.RoleUsuarioDTO;
import com.apijava.javaAPI.api.model.input.RoleUsuarioInputDTO;
import com.apijava.javaAPI.domain.model.RolePessoa;
import com.apijava.javaAPI.domain.repository.RoleUsuarioRepository;
import com.apijava.javaAPI.domain.service.RoleUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleUsuarioController {

    RoleUsuarioAssembler roleUsuarioAssembler;
    RoleUsuarioRepository roleUsuarioRepository;
    RoleUsuarioService roleUsuarioService;

    @PostMapping
    public RoleUsuarioDTO cadastrar(@Valid @RequestBody RoleUsuarioInputDTO roleUsuarioInputDTO){
        RolePessoa novaRole = roleUsuarioAssembler.toEntity(roleUsuarioInputDTO);
        RolePessoa rolePessoa = roleUsuarioService.cadastrar(novaRole);
        return roleUsuarioAssembler.toModel(rolePessoa);
    }

    @GetMapping
    public List<RoleUsuarioDTO> listar() {
        return roleUsuarioService.listar();
    }

    @GetMapping("/buscar/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> buscarPorId(@PathVariable Long roleId) {
        return roleUsuarioService.buscarId(roleId);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleUsuarioDTO> editar(@Valid @PathVariable Long roleId, @RequestBody RoleUsuarioInputDTO roleUsuarioInputDTO) {
        RolePessoa rolePessoa1 = roleUsuarioAssembler.toEntity(roleUsuarioInputDTO);
        roleUsuarioService.editar(roleId, rolePessoa1);
        return ResponseEntity.ok(roleUsuarioAssembler.toModel(rolePessoa1));
    }

}