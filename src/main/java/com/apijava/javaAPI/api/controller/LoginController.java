package com.apijava.javaAPI.api.controller;

import com.apijava.javaAPI.api.assembler.PessoaAssembler;
import com.apijava.javaAPI.api.model.LoginDTO;
import com.apijava.javaAPI.domain.model.AuthenticationResponse;
import com.apijava.javaAPI.domain.model.Pessoa;
import com.apijava.javaAPI.security.ImplementsUserDetailsService;
import com.apijava.javaAPI.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;
    private ImplementsUserDetailsService implementsUserDetailsService;
    private JWTUtil jwtUtil;
    private PessoaAssembler pessoaAssembler;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO loginDTO) throws Exception {
        Pessoa pessoa = pessoaAssembler.toEntityLogin(loginDTO);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha())
            );
        }catch (BadCredentialsException ex){
            throw new Exception("Usuário ou senha inválidos.", ex);
        }
        final UserDetails userDetails = implementsUserDetailsService.loadUserByUsername(pessoa.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}