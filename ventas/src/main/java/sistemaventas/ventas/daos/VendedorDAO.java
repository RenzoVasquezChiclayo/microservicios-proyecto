package sistemaventas.ventas.daos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistemaventas.ventas.entidad.Vendedor;

@Repository
public interface VendedorDAO extends JpaRepository<Vendedor,Integer>{
    
    // public Vendedor findByDni(String dni);
    public Vendedor findByCorreo(String correo);
}
