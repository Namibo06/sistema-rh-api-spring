package com.waitomo.sistema_rh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_employee")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20,nullable = false)
    private String firstName;
    @Column(length = 20)
    private String lastName;
    @Column(nullable = false)
    private LocalDate dateNasciment;
    @Column(nullable = false,length = 15)
    private String gender;
    @Column(name = "sector_id",nullable = false)
    private Long sector;
    @Column(name = "cep",nullable = false,columnDefinition = "char(8)")
    private String cep;
    @Column(name = "cnpj",nullable = false,columnDefinition = "char(14)")
    private String cnpjEnterprise;
    @Column(name = "user_level_id",nullable = false)
    private Long userLevel;
    @Column(unique = true,nullable = false,length = 120)
    private String login;
    @Column(nullable = false,length = 150)
    private String password;
    @Column(length = 150)
    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
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