package sistemaventas.ventas.servicios;



import org.springframework.stereotype.Component;

import sistemaventas.ventas.entidad.Cliente;



@Component
public interface ClienteServicio {
    public Cliente guardar(Cliente cliente);

    public Cliente saveCliente(Cliente cliente);

    public Cliente findByRuc(String ruc);

    public Cliente findById(Integer id);

    public Cliente actualizarCliente(Cliente cliente);

    public Cliente obtenerClienteById(Integer id_cliente);


}
