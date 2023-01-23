package sistemaventas.ventas.implementacion;


import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistemaventas.ventas.daos.ClienteDAO;
import sistemaventas.ventas.daos.UsuarioDAO;
import sistemaventas.ventas.entidad.Cliente;
import sistemaventas.ventas.entidad.Usuario;
import sistemaventas.ventas.servicios.ClienteServicio;



@Service("ClienteServiceImpl")
public class ClienteServiceImpl implements ClienteServicio {
    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Transactional
    public Cliente saveCliente(Cliente cliente) {
		Cliente clienteresponse = clienteDAO.save(cliente);
		return clienteresponse;
	}

    @Transactional
	public Cliente findByRuc(String ruc) {
		Cliente clienteresponse = clienteDAO.findByRuc(ruc);
		return clienteresponse;
	}

    @Transactional
    public Cliente findById(Integer id){
        Optional<Cliente> clienteresponse = clienteDAO.findById(id);
        if (clienteresponse.isPresent()) {
            return clienteresponse.get();
        }else{
            return null;
        }
        
    }

    @Override
	public Cliente guardar(Cliente cliente) {
		List<Usuario> UserExists = usuarioDAO.findAll();
		Cliente newcliente;
		if (UserExists.size() < 1) {
			return null;
		}else{
			newcliente = new Cliente(cliente.getRuc(),cliente.getNombres(),cliente.getDireccion(),cliente.getEstado());
		}
		return clienteDAO.save(newcliente);
	}

    @Override
    public Cliente obtenerClienteById(Integer id_cliente){
        return clienteDAO.findById(id_cliente).get();
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente){
        return clienteDAO.save(cliente);
    }


}
