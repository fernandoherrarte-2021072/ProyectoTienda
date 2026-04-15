package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.Usuario;
import com.fherrarte.ProyectoTienda.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplements implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImplements(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // ===== CRUD EXISTENTE (NO SE TOCA) =====

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) throws RuntimeException {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Integer id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente != null) {
            usuario.setCodigoUsuario(id);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    // ===== NUEVA LÓGICA: LOGIN =====

    @Override
    public Usuario login(String username, String password) {
        return usuarioRepository
                .findByUsernameAndPassword(username.trim(), password.trim())
                .orElse(null);
    }

    // ===== NUEVA LÓGICA: REGISTRO =====

    @Override
    public Usuario registrar(String username, String password) {

        String user = username.trim();

        if (usuarioRepository.findByUsername(user).isPresent()) {
            return null;
        }

        Usuario nuevo = new Usuario();
        nuevo.setUsername(user);
        nuevo.setPassword(password.trim());
        nuevo.setRol("VENDEDOR");
        nuevo.setEstado(1);

        return usuarioRepository.save(nuevo);
    }
}