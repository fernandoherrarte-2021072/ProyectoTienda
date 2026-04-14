package com.fherrarte.ProyectoTienda.controller;

import  jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteViewController {

    @GetMapping("/clientes")
    public String clientes(HttpSession session) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        return "clientes"; // clientes.html
    }
}
