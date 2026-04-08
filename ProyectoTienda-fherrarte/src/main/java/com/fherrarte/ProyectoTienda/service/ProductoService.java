package com.fherrarte.ProyectoTienda.service;

import com.fherrarte.ProyectoTienda.entity.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> getAllProductos();
    Producto getProductoById(Integer id);
    Producto saveProducto(Producto producto) throws RuntimeException;
    Producto updateProducto(Integer id, Producto producto);
    void deleteProducto(Integer id);

}
