package com.apijava.javaAPI.domain.repository;

import com.apijava.javaAPI.domain.model.RolePessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUsuarioRepository extends JpaRepository<RolePessoa, Long> {

}