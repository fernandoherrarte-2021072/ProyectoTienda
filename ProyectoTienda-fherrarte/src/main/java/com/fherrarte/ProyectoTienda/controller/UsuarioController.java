package com.fherrarte.ProyectoTienda.controller;

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    //inicio login
    @GetMapping("/")
    public String inicio(){ return "redirect:/usuario";}

    // Mostrar Login
    @GetMapping("/usuario")
    public String mostrarLogin() { return "usuario";}

    //procesar Login
    @PostMapping("/login")
    public String login (@RequestParam String usuario,
                        @RequestParam String password,
                        HttpSession session,
                        Model model)    {
       String userCorrecto = "admin";
       String passCorrecto = "1234";

       if (usuario.equals(userCorrecto) && password.equals(passCorrecto)){
           // guardar sesion
           session.setAttribute("usuarioLogueado", usuario);
           return "redirect:/alumno";


       }else{
           model.addAttribute("error", "usuario y contrasena incorrecta");
           return "usuario";
       }
    }
    //proteger ruta sin SpringSecurity
    @GetMapping("/alumno")
    public String mostrarAlumno(HttpSession session){
        //Validar la sesion
        if(session.getAttribute("usuarioLogueado") ==null){
            return "redirect:/usuario";
        }
        return "alumno";
    }
}
