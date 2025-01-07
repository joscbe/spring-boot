package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val usuario: Usuario
): UserDetails {

    override fun getAuthorities() = usuario.role

    override fun getPassword(): String {
        return usuario.password
    }

    override fun getUsername(): String {
        return usuario.email
    }
}