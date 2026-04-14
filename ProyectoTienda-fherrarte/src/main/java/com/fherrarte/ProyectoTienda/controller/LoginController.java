package com.fherrarte.ProyectoTienda.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


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

        String userCorrecto = "admin";
        String passCorrecto = "1234";

        if (usuario.equals(userCorrecto) && password.equals(passCorrecto)) {
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }


    @GetMapping("/home")
    public String mostrarHome(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "home";
    }


    @GetMapping("/registrarse")
    public String mostrarRegistro() {
        return "registrarse";
    }

    // Cerrar sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


    @GetMapping("/ventas")
    public String mostrarVentas(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "ventas";
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
    @GetMapping("/usuarios")
    public String mostrarUsuarios(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        return "usuarios";
    }
}

