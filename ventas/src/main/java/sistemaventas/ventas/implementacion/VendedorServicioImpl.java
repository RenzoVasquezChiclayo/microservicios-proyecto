package sistemaventas.ventas.implementacion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemaventas.ventas.daos.VendedorDAO;
import sistemaventas.ventas.entidad.Vendedor;
import sistemaventas.ventas.servicios.VendedorServicio;

@Service("VendedorServicieImpl")
public class VendedorServicioImpl implements VendedorServicio{
    
    @Autowired
    private VendedorDAO vendedorDAO;

    @Override
    public Vendedor guardar(Vendedor vendedor){
        Vendedor newVendedor;
        newVendedor = new Vendedor(vendedor.getNombres(),vendedor.getCorreo(),vendedor.getEstado());
        return vendedorDAO.save(newVendedor);
    }

    @Override
    public Vendedor actualizarVendedor(Vendedor vendedor){
        return vendedorDAO.save(vendedor);
    }
}
