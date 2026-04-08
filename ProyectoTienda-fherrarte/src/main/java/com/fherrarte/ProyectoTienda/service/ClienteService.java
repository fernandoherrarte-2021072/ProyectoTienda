package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> getAllClientes();
    Cliente getClienteById(Integer dpi);
    Cliente saveCliente(Cliente cliente) throws RuntimeException;
    Cliente updateCliente(Integer dpi, Cliente cliente);
    void deleteCliente(Integer dpi);

}