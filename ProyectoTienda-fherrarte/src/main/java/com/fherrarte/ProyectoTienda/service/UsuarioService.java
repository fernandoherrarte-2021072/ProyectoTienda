package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    // CRUD existente (NO se toca)
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Integer id);
    Usuario saveUsuario(Usuario usuario) throws RuntimeException;
    Usuario updateUsuario(Integer id, Usuario usuario);
    void deleteUsuario(Integer id);

    // 🔐 LOGIN
    Usuario login(String username, String password);

    // 📝 REGISTRO
    Usuario registrar(String username, String password);
}