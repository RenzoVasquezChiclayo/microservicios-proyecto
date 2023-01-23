package sistemaventas.ventas.entidad;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



// create table usuario(id_usuario int AUTO_INCREMENT, user varchar(15) not null, password varchar(15) not null, rol varchar(15) not null, primary key(id_usuario))
@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_usuario;
    
    @Column(name = "idcliente")
    private Integer idcliente;

    @Column(name = "idvendedor")
    private Integer idvendedor;

    @Column(name = "user")
    private String user;

    @Column(name = "password")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
        name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id_rol")
    )
    private Collection<Rol> rol;

    // @OneToOne(mappedBy = "usuario",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    // private Cliente cliente;


    public Usuario(){
        super();
    }
    
    public Usuario(Integer id,Integer idcliente,Integer idvendedor, String user,String password,Collection<Rol> rol){
        super();
        this.id_usuario = id;
        this.idcliente = idcliente;
        this.idvendedor = idvendedor;
        this.user = user;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(Integer idcliente, Integer idvendedor, String user,String password, Collection<Rol> rol){
        super();
        this.idcliente = idcliente;
        this.idvendedor = idvendedor;
        this.user = user;
        this.password = password;
        this.rol = rol;
    }

    public Integer getId_Usuario(){
        return id_usuario;
    }

    public Integer getIdcliente(){
        return idcliente;
    }

    public Integer getIdvendedor(){
        return idvendedor;
    }

    public String getUser(){
        return user;
    }

    public String getPassword(){
        return password;
    }

    public Collection<Rol> getRol(){
        return rol;
    }

    public void setId_Usuario(Integer id_usuario){
        this.id_usuario = id_usuario;
    }

    public void setIdcliente(Integer idcliente){
        this.idcliente = idcliente;
    }

    public void setIdvendedor(Integer idvendedor){
        this.idvendedor = idvendedor;
    }

    public void setUser(String user){
        this.user = user;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRol(Collection<Rol> rol){
        this.rol = rol;
    }
}
