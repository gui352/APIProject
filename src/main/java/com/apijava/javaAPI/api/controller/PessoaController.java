package com.apijava.javaAPI.api.controller;

import com.apijava.javaAPI.api.assembler.PessoaAssembler;
import com.apijava.javaAPI.api.model.PessoaDTO;
import com.apijava.javaAPI.api.model.input.PessoaInputDTO;
import com.apijava.javaAPI.domain.model.Pessoa;
import com.apijava.javaAPI.domain.model.RoleUsuario;
import com.apijava.javaAPI.domain.repository.PessoaRepository;
import com.apijava.javaAPI.domain.service.PessoaService;
import com.apijava.javaAPI.domain.service.RoleUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;
    private RoleUsuarioService roleUsuarioService;

    @PostMapping("/cadastrar")
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        novaPessoa.setSenha(new BCryptPasswordEncoder()
                .encode(pessoaInputDTO.getSenha()));
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);
        RoleUsuario novaRole = new RoleUsuario();
        novaRole.setUsuarios_id(novaPessoa.getCodigo());
        novaRole.setRole_nome_role("ROLE_USER");
        roleUsuarioService.cadastrar(novaRole);

        return pessoaAssembler.toModel(pessoa);
    }

    @GetMapping
    public List<PessoaDTO> listar(){
        return pessoaService.listar();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<PessoaDTO> editar(@Valid @PathVariable Long codigo,
                                            @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa pessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        pessoa.setSenha(new BCryptPasswordEncoder()
                .encode(pessoaInputDTO.getSenha()));
        pessoaService.editar(codigo,pessoa);
        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Pessoa> remover(@PathVariable Long codigo){
        if(!pessoaRepository.existsById(codigo)) {
            return ResponseEntity.notFound().build();
        }
        pessoaService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }

}
