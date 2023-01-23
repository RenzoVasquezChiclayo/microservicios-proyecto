package sistemaventas.ventas.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalle_venta")
public class DetalleVenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_venta;

    @ManyToOne(fetch = FetchType.EAGER)
    private Venta venta;

    private Integer id_producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "descuento")
    private Float descuento;

    public DetalleVenta(){
        super();
    }

    public DetalleVenta(Integer id_detalle_venta, Venta venta, Integer id_producto, Float descuento, Integer cantidad){
        super();
        this.id_detalle_venta = id_detalle_venta;
        this.venta = venta;
        this.id_producto = id_producto;
        this.descuento = descuento;
        this.cantidad = cantidad;
    }

    public DetalleVenta(Venta venta, Integer id_producto, Float descuento, Integer cantidad){
        super();
        this.venta = venta;
        this.id_producto = id_producto;
        this.descuento = descuento;
        this.cantidad = cantidad;
    }

    public Integer getId_detalle_venta(){
        return id_detalle_venta;
    }

    public Venta getVenta(){
        return venta;
    }

    public Integer getId_Producto(){
        return id_producto;
    }

    public Float getDescuento(){
        return descuento;
    }

    public Integer getCantidad(){
        return cantidad;
    }

    public void setId_detalle_venta(Integer id_detalle_venta){
        this.id_detalle_venta = id_detalle_venta;
    }

    public void setVenta(Venta venta){
        this.venta = venta;
    }

    public void setId_Producto(Integer id_producto){
        this.id_producto = id_producto;
    }

    public void setDescuento(Float descuento){
        this.descuento = descuento;
    }

    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }

}
