package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.Cliente;
import com.fherrarte.ProyectoTienda.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImplements implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImplements(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(String dpi) { // Cambiado a String
        return clienteRepository.findById(dpi).orElse(null);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) throws RuntimeException {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(String dpi, Cliente cliente) { // Cambiado a String
        Cliente clienteExistente = clienteRepository.findById(dpi).orElse(null);

        if (clienteExistente != null) {
            // Aseguramos que el DPI del objeto cliente sea el que viene por parámetro
            cliente.setDpiCliente(dpi);
            return clienteRepository.save(cliente);
        }

        return null;
    }

    @Override
    public void deleteCliente(String dpi) { // Cambiado a String
        clienteRepository.deleteById(dpi);
    }
}