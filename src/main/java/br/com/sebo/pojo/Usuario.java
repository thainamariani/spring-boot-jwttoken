package br.com.sebo.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {
    @Id
    private String login;
    private String nome;
    private String senha;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "login"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role"))
    private List<Role> roles;

    public Usuario(String login, String nome, String senha, List<Role> roles) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.roles = roles;
    }

    public Usuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
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
