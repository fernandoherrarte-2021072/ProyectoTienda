package com.fherrarte.ProyectoTienda.controller;

import com.fherrarte.ProyectoTienda.entity.Cliente;
import com.fherrarte.ProyectoTienda.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping
    public ResponseEntity<Object> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente created = clienteService.saveCliente(cliente);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{dpi}")
    public ResponseEntity<Object> updateCliente(
            @PathVariable Integer dpi,
            @Valid @RequestBody Cliente clienteRequest) {
        try {
            Cliente updated = clienteService.updateCliente(dpi, clienteRequest);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{dpi}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer dpi) {
        clienteService.deleteCliente(dpi);
        return ResponseEntity.noContent().build();
    }
}