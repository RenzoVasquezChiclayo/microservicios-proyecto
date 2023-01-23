package sistemaventas.ventas.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistemaventas.ventas.entidad.Venta;

@Repository
public interface VentaDAO  extends JpaRepository<Venta, Integer>{
    

}
