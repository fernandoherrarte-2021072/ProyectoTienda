package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.Usuario;

import java.util.List;

public interface UsuarioService {


    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Integer id);
    Usuario saveUsuario(Usuario usuario) throws RuntimeException;
    Usuario updateUsuario(Integer id, Usuario usuario);
    void deleteUsuario(Integer id);


    Usuario login(String username, String password);


    Usuario registrar(String username, String password);
}