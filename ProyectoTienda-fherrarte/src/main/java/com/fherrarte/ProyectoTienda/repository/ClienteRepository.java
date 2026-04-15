package com.fherrarte.ProyectoTienda.repository;

import com.fherrarte.ProyectoTienda.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

}