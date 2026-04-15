package com.fherrarte.ProyectoTienda.repository;

import com.fherrarte.ProyectoTienda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Para LOGIN
    Optional<Usuario> findByUsernameAndPassword(String username, String password);

    // Para REGISTRO (verifica si ya existe)
    Optional<Usuario> findByUsername(String username);
}
