package sistemaventas.ventas.daos;

import org.springframework.data.jpa.repository.JpaRepository;


import sistemaventas.ventas.entidad.Rol;



public interface RolDAO extends JpaRepository<Rol, Integer>{
    
}
