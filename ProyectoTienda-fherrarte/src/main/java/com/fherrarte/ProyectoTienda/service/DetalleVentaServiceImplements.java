package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.DetalleVenta;
import com.fherrarte.ProyectoTienda.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImplements implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImplements(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> getAllDetalleVentas() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta getDetalleVentaById(Integer id) {
        return detalleVentaRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleVenta saveDetalleVenta(DetalleVenta detalleVenta) throws RuntimeException {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta updateDetalleVenta(Integer id, DetalleVenta detalleVenta) {
        DetalleVenta detalleExistente = detalleVentaRepository.findById(id).orElse(null);

        if (detalleExistente != null) {
            detalleVenta.setCodigoDetalleVenta(id);
            return detalleVentaRepository.save(detalleVenta);
        }

        return null;
    }

    @Override
    public void deleteDetalleVenta(Integer id) {
        detalleVentaRepository.deleteById(id);
    }
}