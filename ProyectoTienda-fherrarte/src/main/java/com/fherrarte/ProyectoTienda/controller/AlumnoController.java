package com.fherrarte.ProyectoTienda.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AlumnoController {

    @GetMapping("/alumno")
    public String mostrarAlumno(){
        return "alumno";

    }

}
