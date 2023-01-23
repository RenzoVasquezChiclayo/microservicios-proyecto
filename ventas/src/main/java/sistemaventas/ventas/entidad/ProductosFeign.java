package sistemaventas.ventas.entidad;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="cliente", url="http://localhost:0000/productos")
public interface ProductosFeign {
    
    @GetMapping("lista")
    public List<Producto> listar();

    @PostMapping("agregar")
    public Producto agregarProducto(Producto producto);

    @GetMapping("buscar/{id_producto}")
    public Producto findProducto(@PathVariable Integer id_producto);

    @PostMapping("actualizar")
    public Producto updateProducto(Producto producto);

    @PostMapping("eliminar")
    public void deleteProducto(Integer id_producto);
}
