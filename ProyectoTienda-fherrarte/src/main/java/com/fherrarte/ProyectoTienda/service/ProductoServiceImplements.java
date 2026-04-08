package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.Producto;
import com.fherrarte.ProyectoTienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImplements implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImplements(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto saveProducto(Producto producto) throws RuntimeException {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Integer id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);

        if (productoExistente != null) {
            producto.setCodigoProducto(id);
            return productoRepository.save(producto);
        }

        return null;
    }

    @Override
    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}
