package sistemaventas.ventas.servicios;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import sistemaventas.ventas.entidad.Usuario;



@Component
public interface UsuarioServicio extends UserDetailsService{

    public Usuario guardar(Usuario usuario);

    public Usuario findByUser(String user);

    // public Usuario findAllByRol(Integer rol);

    public void deleteByIdcliente(Integer idcliente);

    public List<Usuario> listarUsuarios();

}
