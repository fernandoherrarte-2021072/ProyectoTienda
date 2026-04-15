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

    //login
    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // home
    @GetMapping("/home")
    public String mostrarHome(HttpSession session) {
        if (!usuarioLogueado(session)) {
            return "redirect:/login";
        }
        return "home";
    }

    // registro
    @GetMapping("/registrarse")
    public String mostrarRegistro() {
        return "registrarse";
    }

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

    // vistas por rol

    @GetMapping("/usuarios")
    public String mostrarUsuarios(HttpSession session) {
        if (!usuarioLogueado(session)) return "redirect:/login";
        if (!esAdmin(session)) return "redirect:/home";
        return "usuarios";
    }

    @GetMapping("/clientes")
    public String mostrarClientes(HttpSession session) {
        if (!usuarioLogueado(session)) return "redirect:/login";
        return "clientes";
    }

    @GetMapping("/productos")
    public String mostrarProductos(HttpSession session) {
        if (!usuarioLogueado(session)) return "redirect:/login";
        return "productos";
    }


    @GetMapping("/ventas")
    public String mostrarVentas(HttpSession session) {
        if (!usuarioLogueado(session)) return "redirect:/login";
        return "ventas";
    }


    @GetMapping("/detalles")
    public String mostrarDetalles(HttpSession session) {
        if (!usuarioLogueado(session)) return "redirect:/login";
        return "detalles";
    }

    private boolean usuarioLogueado(HttpSession session) {
        return session.getAttribute("usuarioLogueado") != null;
    }

    private boolean esAdmin(HttpSession session) {
        return "ADMIN".equals(session.getAttribute("rol"));
    }
}



