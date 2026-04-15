package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Cliente getClienteById(String dpi); // Cambiado a String
    Cliente saveCliente(Cliente cliente) throws RuntimeException;
    Cliente updateCliente(String dpi, Cliente cliente); // Cambiado a String
    void deleteCliente(String dpi); // Cambiado a String
}