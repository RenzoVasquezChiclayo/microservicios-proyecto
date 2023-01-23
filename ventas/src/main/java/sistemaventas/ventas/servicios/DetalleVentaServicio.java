package sistemaventas.ventas.servicios;

import java.util.List;

import org.springframework.stereotype.Component;

import sistemaventas.ventas.entidad.DetalleVenta;
import sistemaventas.ventas.entidad.Venta;

@Component
public interface DetalleVentaServicio {
    
    public DetalleVenta guardar(DetalleVenta detalleventa);

    public List<DetalleVenta> findByVenta(Venta Venta);
}
