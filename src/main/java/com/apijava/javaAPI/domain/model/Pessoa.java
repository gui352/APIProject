package com.apijava.javaAPI.domain.model;

import com.apijava.javaAPI.domain.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = PRIVATE)
@Table(name = "pessoas")
public class Pessoa implements UserDetails {


    @NotNull(groups = ValidationGroups.codigo.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    private String nome;

    @NotBlank
    @Size(max = 60)
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @ManyToMany
    @JoinTable(name = "role_usuarios",
            joinColumns = @JoinColumn(name = "usuarios_id",referencedColumnName = "codigo"),
            inverseJoinColumns = @JoinColumn(name = "role_nome_role", referencedColumnName = "nomeRole"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
