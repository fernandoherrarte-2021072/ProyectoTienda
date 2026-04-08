package com.fherrarte.ProyectoTienda.controller;

import com.fherrarte.ProyectoTienda.entity.DetalleVenta;
import com.fherrarte.ProyectoTienda.service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-ventas")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public List<DetalleVenta> getAllDetalleVentas() {
        return detalleVentaService.getAllDetalleVentas();
    }

    @PostMapping
    public ResponseEntity<Object> createDetalleVenta(@Valid @RequestBody DetalleVenta detalleVenta) {
        try {
            DetalleVenta created = detalleVentaService.saveDetalleVenta(detalleVenta);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDetalleVenta(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleVenta detalleVentaRequest) {
        try {
            DetalleVenta updated = detalleVentaService.updateDetalleVenta(id, detalleVentaRequest);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleVenta(@PathVariable Integer id) {
        detalleVentaService.deleteDetalleVenta(id);
        return ResponseEntity.noContent().build();
    }
}