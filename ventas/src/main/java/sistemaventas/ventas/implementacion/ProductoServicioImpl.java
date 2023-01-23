package sistemaventas.ventas.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemaventas.ventas.daos.ProductoDAO;
// import sistemaventas.ventas.entidad.Categoria;
import sistemaventas.ventas.entidad.Producto;
import sistemaventas.ventas.servicios.ProductoServicio;

@Service("ProductoServiceImpl")
public class ProductoServicioImpl implements ProductoServicio{

    @Autowired
    private ProductoDAO productoDAO;
    
    @Override
	public Producto guardar(Producto producto) {
		Producto newproducto;
		newproducto = new Producto(producto.getDescripcion(),producto.getStock(),producto.getPrecio());
		return productoDAO.save(newproducto);
	}

	@Override
    public Producto obtenerProductoById(Integer id_producto){
        return productoDAO.findById(id_producto).get();
    }

    @Override
    public Producto actualizarProducto(Producto producto){
        return productoDAO.save(producto);
    }
}
