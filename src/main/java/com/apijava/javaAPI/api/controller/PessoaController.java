package com.apijava.javaAPI.api.controller;

import com.apijava.javaAPI.api.assembler.PessoaAssembler;
import com.apijava.javaAPI.api.model.PessoaDTO;
import com.apijava.javaAPI.api.model.input.PessoaInputDTO;
import com.apijava.javaAPI.domain.model.Perfil;
import com.apijava.javaAPI.domain.model.Pessoa;
import com.apijava.javaAPI.domain.repository.PessoaRepository;
import com.apijava.javaAPI.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/cadastrar")
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        novaPessoa.setPerfil(Perfil.ROLE_USER);
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);

        return pessoaAssembler.toModel(pessoa);
    }

    @GetMapping
    public List<PessoaDTO> listar(){
        return pessoaService.listar();
    }

    @PutMapping("/editar/{codigo}")
    public ResponseEntity<PessoaDTO> editar(@Valid @PathVariable Long codigo,
                                            @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa pessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        pessoaService.editar(codigo,pessoa);
        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
    }

    @PutMapping("/editarPermissao/{codigo}")
    public ResponseEntity<PessoaDTO> editar(@Valid @PathVariable Long codigo){
        Pessoa pessoa = pessoaService.editarPerfil(codigo);
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
