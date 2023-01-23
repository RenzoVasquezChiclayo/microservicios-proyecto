package sistemaventas.ventas.daos;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistemaventas.ventas.entidad.DetalleVenta;
import sistemaventas.ventas.entidad.Venta;


@Repository
public interface DetalleVentaDAO extends JpaRepository<DetalleVenta, Integer>{
    
    public List<DetalleVenta> findByVenta(Venta venta);
}
