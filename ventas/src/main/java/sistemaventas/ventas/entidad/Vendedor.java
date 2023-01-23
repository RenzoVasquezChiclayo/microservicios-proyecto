package sistemaventas.ventas.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendedor")
public class Vendedor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id_vendedor;


    @Column(name = "nombres")
    private String nombres;

    @Column(name = "correo")
    private String correo;

    @Column(name = "estado")
    private Integer estado;


    public Vendedor(){
        super();
    }
    
    public Vendedor(Integer id,String nombres, String correo, Integer estado){
        super();
        this.id_vendedor = id;
        // this.dni = dni;
        this.nombres = nombres;
        this.correo = correo;
        // this.celular = celular;
        this.estado = estado;
    }

    public Vendedor(String nombres, String correo, Integer estado){
        super();
        // this.dni = dni;
        this.nombres = nombres;
        this.correo = correo;
        // this.celular = celular;
        this.estado = estado;
    }

    public Integer getId_Vendedor(){
        return id_vendedor;
    }

    // public String getDni(){
    //     return dni;
    // }

    public String getNombres(){
        return nombres;
    }

    public String getCorreo(){
        return correo;
    }

    // public String getCelular(){
    //     return celular;
    // }

    public Integer getEstado(){
        return estado;
    }

    public void setId_Vendedor(Integer id_vendedor){
        this.id_vendedor = id_vendedor;
    }

    // public void setDni(String dni){
    //     this.dni = dni;
    // }

    public void setNombres(String nombres){
        this.nombres = nombres;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    // public void setCelular(String celular){
    //     this.celular = celular;
    // }

    public void setEstado(Integer estado){
        this.estado = estado;
    }
}
