package sistemaventas.ventas.daos;


import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import sistemaventas.ventas.entidad.Usuario;



@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{
    
    public Usuario findByUser(String user);

    public Iterable<Usuario> findAllByRol(Integer rol);

    public void deleteByIdcliente(Integer idcliente);

}
