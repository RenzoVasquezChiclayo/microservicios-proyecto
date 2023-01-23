package sistemaventas.ventas.servicios;

import org.springframework.stereotype.Component;

// import sistemaventas.ventas.entidad.Categoria;
import sistemaventas.ventas.entidad.Producto;

@Component
public interface ProductoServicio{

    public Producto guardar(Producto producto);

    public Producto actualizarProducto(Producto cliente);

    public Producto obtenerProductoById(Integer id_producto);
}
