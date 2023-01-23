package com.ventas.msproductos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ventas.msproductos.entidad.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto,Integer> {
    
    
}
