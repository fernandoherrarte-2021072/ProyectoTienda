package com.fherrarte.ProyectoTienda.controller;

import com.fherrarte.ProyectoTienda.entity.Usuario;
import com.fherrarte.ProyectoTienda.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // REDIRECCIÓN INICIAL
    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    // MOSTRAR LOGIN
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // login.html
    }

    // PROCESAR LOGIN (BASE DE DATOS)
    @PostMapping("/login")
    public String login(@RequestParam String usuario,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Usuario u = usuarioService.login(usuario, password);

        if (u != null && u.getEstado() == 1) {
            session.setAttribute("usuarioLogueado", u.getUsername());
            session.setAttribute("codigoUsuario", u.getCodigoUsuario());
            session.setAttribute("rol", u.getRol());
            return "redirect:/home";
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }

    // MOSTRAR HOME
    @GetMapping("/home")
    public String mostrarHome(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "home";
    }

    // MOSTRAR REGISTRO
    @GetMapping("/registrarse")
    public String mostrarRegistro() {
        return "registrarse"; // registrarse.html
    }

    // PROCESAR REGISTRO (BASE DE DATOS)
    @PostMapping("/registrarse")
    public String registrar(@RequestParam String usuario,
                            @RequestParam String password,
                            Model model) {

        Usuario nuevo = usuarioService.registrar(usuario, password);

        if (nuevo == null) {
            model.addAttribute("error", "El usuario ya existe");
            return "registrarse";
        }

        model.addAttribute("success", "Usuario creado correctamente");
        return "redirect:/login";
    }

    // CERRAR SESIÓN
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // ===== VISTAS DEL SISTEMA (PROTEGIDAS) =====

    @GetMapping("/clientes")
    public String mostrarClientes(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "clientes";
    }

    @GetMapping("/ventas")
    public String mostrarVentas(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "ventas";
    }

    @GetMapping("/usuarios")
    public String mostrarUsuarios(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "usuarios";
    }

    @GetMapping("/productos")
    public String mostrarProductos(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "productos";
    }

    @GetMapping("/detalles")
    public String mostrarDetalles(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "detalles";
    }
}


