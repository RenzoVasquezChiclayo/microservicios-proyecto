package sistemaventas.ventas.servicios;

import org.springframework.stereotype.Component;

import sistemaventas.ventas.entidad.Venta;

@Component
public interface VentaServicio {
    
    public Venta guardar(Venta venta);

    public Venta obtenerUltimoRegistro();
}
