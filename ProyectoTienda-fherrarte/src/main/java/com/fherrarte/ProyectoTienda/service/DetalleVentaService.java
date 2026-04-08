package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {

    List<DetalleVenta> getAllDetalleVentas();
    DetalleVenta getDetalleVentaById(Integer id);
    DetalleVenta saveDetalleVenta(DetalleVenta detalleVenta) throws RuntimeException;
    DetalleVenta updateDetalleVenta(Integer id, DetalleVenta detalleVenta);
    void deleteDetalleVenta(Integer id);

}