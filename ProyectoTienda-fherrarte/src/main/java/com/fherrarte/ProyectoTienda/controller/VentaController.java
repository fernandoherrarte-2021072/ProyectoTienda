package com.fherrarte.ProyectoTienda.controller;

import com.fherrarte.ProyectoTienda.entity.Venta;
import com.fherrarte.ProyectoTienda.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @PostMapping
    public ResponseEntity<Object> createVenta(@Valid @RequestBody Venta venta) {
        try {
            Venta created = ventaService.saveVenta(venta);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVenta(
            @PathVariable Integer id,
            @Valid @RequestBody Venta ventaRequest) {
        try {
            Venta updated = ventaService.updateVenta(id, ventaRequest);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Integer id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
