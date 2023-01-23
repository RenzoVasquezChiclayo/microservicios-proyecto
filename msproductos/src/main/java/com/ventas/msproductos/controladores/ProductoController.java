package com.ventas.msproductos.controladores;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.msproductos.entidad.Producto;
import com.ventas.msproductos.repositorio.ProductoRepository;

@RestController
@RequestMapping(value="productos",method={RequestMethod.GET,RequestMethod.POST})
public class ProductoController {
    
    @Autowired
    ProductoRepository productoDAO;

    @GetMapping("/lista")
    public List<Producto> listaProductos(){
        return (List<Producto>) productoDAO.findAll();
    }

    @PostMapping("/agregar")
    public Producto agregarProducto(@RequestBody Producto producto){
        Producto newproducto;
        System.out.println(producto.getDescripcion());
		newproducto = new Producto(producto.getDescripcion(),producto.getStock(),producto.getPrecio());
        return productoDAO.save(newproducto);
    }

    @GetMapping("/buscar/{id_producto}")
    public Producto findProducto(@PathVariable Integer id_producto){
        System.out.print(id_producto);
        Producto findProducto = productoDAO.findById(id_producto).get();
        return findProducto;
    }

    @PostMapping("/actualizar")
    public Producto updateProducto(@RequestBody Producto producto){
        return productoDAO.save(producto);
    }

    @PostMapping("/eliminar")
    public void deleteProducto(@RequestBody Integer id_producto){
        productoDAO.deleteById(id_producto);
    }
}
