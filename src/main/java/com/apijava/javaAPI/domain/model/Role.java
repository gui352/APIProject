package com.apijava.javaAPI.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    private String nomeRole;

    @ManyToMany
    private List<Pessoa> pessoas;

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}