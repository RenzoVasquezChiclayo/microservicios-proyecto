package sistemaventas.ventas.implementacion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import sistemaventas.ventas.daos.DetalleVentaDAO;
import sistemaventas.ventas.entidad.DetalleVenta;
import sistemaventas.ventas.entidad.Venta;
import sistemaventas.ventas.servicios.DetalleVentaServicio;

@Service("DetalleVentaServicioImpl")
public class DetalleVentaServicioImpl implements DetalleVentaServicio{
    
    @Autowired
    DetalleVentaDAO detalleVentaDAO;

    @Override
    public DetalleVenta guardar(DetalleVenta detalleventa){
        DetalleVenta newdetalleVenta;
        newdetalleVenta = new DetalleVenta(detalleventa.getVenta(),detalleventa.getId_Producto(),detalleventa.getDescuento(),detalleventa.getCantidad());
        return detalleVentaDAO.save(newdetalleVenta);
    }

    @Override
    public List<DetalleVenta> findByVenta(Venta Venta){
        return detalleVentaDAO.findByVenta(Venta);

    }
}
