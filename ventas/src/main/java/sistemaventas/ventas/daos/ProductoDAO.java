package sistemaventas.ventas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import sistemaventas.ventas.entidad.Producto;

public interface ProductoDAO extends JpaRepository<Producto, Integer> {
    
}
