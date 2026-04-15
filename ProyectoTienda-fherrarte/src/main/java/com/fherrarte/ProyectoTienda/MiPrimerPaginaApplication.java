package com.fherrarte.ProyectoTienda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class
    MiPrimerPaginaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MiPrimerPaginaApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Mi primer Pagina");
    }
}
