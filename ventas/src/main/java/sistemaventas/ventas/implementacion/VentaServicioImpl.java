package sistemaventas.ventas.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistemaventas.ventas.daos.VentaDAO;
import sistemaventas.ventas.entidad.Venta;
import sistemaventas.ventas.servicios.VentaServicio;

@Service("VentaServicioImpl")
public class VentaServicioImpl implements VentaServicio{
    
    @Autowired
    VentaDAO ventaDAO;

    @Override
    public Venta obtenerUltimoRegistro(){
        List<Venta> allVentas = ventaDAO.findAll();
        return allVentas.get(allVentas.size()-1);
    }

    @Override
    public Venta guardar(Venta venta){
        Venta newVenta;
        newVenta = new Venta(venta.getUsuario(),venta.getCliente(),venta.getFecha_venta(), venta.getTotal(),venta.getEstado());
        return ventaDAO.save(newVenta);
    }
}
