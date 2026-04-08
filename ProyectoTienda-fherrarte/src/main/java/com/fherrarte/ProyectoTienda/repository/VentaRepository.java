package com.fherrarte.ProyectoTienda.repository;

import com.fherrarte.ProyectoTienda.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
