package sistemaventas.ventas.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



// create table rol(id_rol int AUTO_INCREMENT not null, descripcion varchar(20) not null, primary key(id_rol))
@Entity
@Table(name = "rol")
public class Rol {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id_rol;

    @Column(name = "descripcion")
    private String descripcion;

    public Rol(){
        super();
    }

    public Rol(Integer id_rol, String descripcion){
        super();
        this.id_rol = id_rol;
        this.descripcion = descripcion;
    }

    public Rol(String descripcion){
        super();
        this.descripcion = descripcion;
    }

    public Integer getId_Rol(){
        return id_rol;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setId_Rol(Integer id_rol){
        this.id_rol = id_rol;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
}
