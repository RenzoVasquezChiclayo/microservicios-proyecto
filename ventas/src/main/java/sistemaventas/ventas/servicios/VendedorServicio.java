package sistemaventas.ventas.servicios;

import org.springframework.stereotype.Component;

import sistemaventas.ventas.entidad.Vendedor;

@Component
public interface VendedorServicio {
    
    public Vendedor guardar(Vendedor vendedor);

    public Vendedor actualizarVendedor(Vendedor vendedor);
}
