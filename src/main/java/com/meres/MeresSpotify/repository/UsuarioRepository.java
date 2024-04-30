package com.meres.MeresSpotify.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meres.MeresSpotify.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findUsuarioByEmail(String email);
    Optional<Usuario> findUsuarioByEmailAndSenha(String email, String senha);
}
