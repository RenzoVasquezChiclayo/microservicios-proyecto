package sistemaventas.ventas.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id_cliente;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado")
    private Integer estado;

    public Cliente(){
        super();
    }
    
    public Cliente(Integer id,String ruc,String nombres,String direccion,Integer estado){
        super();
        this.id_cliente = id;
        this.ruc = ruc;
        this.nombres = nombres;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Cliente(String ruc,String nombres,String direccion,Integer estado){
        super();
        this.ruc = ruc;
        this.nombres = nombres;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Integer getId_Cliente(){
        return id_cliente;
    }

    public String getRuc(){
        return ruc;
    }

    public String getNombres(){
        return nombres;
    }

    public String getDireccion(){
        return direccion;
    }


    public Integer getEstado(){
        return estado;
    }

    public void setId_Cliente(Integer id_cliente){
        this.id_cliente = id_cliente;
    }

    public void setRuc(String ruc){
        this.ruc = ruc;
    }

    public void setNombres(String nombres){
        this.nombres = nombres;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public void setEstado(Integer estado){
        this.estado = estado;
    }

}
