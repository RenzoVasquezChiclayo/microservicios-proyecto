package sistemaventas.ventas.entidad;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="venta")
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_venta;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
    
    @Column(name = "fecha_venta")
    private Date fecha_venta;

    @Column(name = "total")
    private Float total;

    @Column(name = "estado")
    private Integer estado;

    public Venta(){
        super();
    }

    public Venta(Integer Id_venta, Usuario usuario, Cliente cliente, Date fecha_venta, Float total, Integer estado){
        super();
        this.Id_venta = Id_venta;
        this.usuario = usuario;
        this.cliente = cliente;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.estado = estado;
    }

    public Venta(Usuario usuario, Cliente cliente, Date fecha_venta, Float total, Integer estado){
        super();
        this.usuario = usuario;
        this.cliente = cliente;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.estado = estado;
    }

    public Integer getId_venta(){
        return Id_venta;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public Date getFecha_venta(){
        return fecha_venta;
    }
    
    public Float getTotal(){
        return total;
    }

    public Integer getEstado(){
        return estado;
    }

    public void setId_venta(Integer Id_venta){
        this.Id_venta = Id_venta;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void setFecha_venta(Date fecha_venta){
        this.fecha_venta = fecha_venta;
    }

    public void setTotal(Float total){
        this.total = total;
    }

    public void setEstado(Integer estado){
        this.estado = estado;
    }
}
