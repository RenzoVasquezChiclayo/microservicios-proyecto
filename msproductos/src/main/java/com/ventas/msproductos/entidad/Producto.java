package com.ventas.msproductos.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "precio")
    private Float precio;

    public Producto(){
        super();
    }
    
    public Producto(Integer id,String descripcion,Integer stock, Float precio){
        super();
        this.id_producto = id;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    public Producto(String descripcion,Integer stock, Float precio){
        super();
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    public Integer getId_Producto(){
        return id_producto;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public Integer getStock(){
        return stock;
    }

    public Float getPrecio(){
        return precio;
    }

    public void setId_Producto(Integer id_producto){
        this.id_producto = id_producto;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setStock(Integer stock){
        this.stock = stock;
    }

    public void setPrecio(Float precio){
        this.precio = precio;
    }

}
