package sistemaventas.ventas.implementacion;




import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import sistemaventas.ventas.daos.UsuarioDAO;
import sistemaventas.ventas.entidad.Rol;
import sistemaventas.ventas.entidad.Usuario;
import sistemaventas.ventas.servicios.UsuarioServicio;


@Service("UsuarioServicieImpl")
public class UsuarioServicieImpl implements UsuarioServicio {
    @Autowired
    private UsuarioDAO usuarioDAO;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Override
	public Usuario findByUser(String user) {
		Usuario usuarioresponse = usuarioDAO.findByUser(user);
		return usuarioresponse;
	}

	@Override
	public Usuario guardar(Usuario usuario) {
		List<Usuario> UserExists = usuarioDAO.findAll();
		Usuario newusuario;
		if (UserExists.size() == 0) {
			newusuario = new Usuario(usuario.getIdcliente(),usuario.getIdvendedor(),usuario.getUser(), passwordEncoder.encode(usuario.getPassword()),Arrays.asList(new Rol("ROLE_ADMIN")));
			
		}else{
			newusuario = new Usuario(usuario.getIdcliente(),usuario.getIdvendedor(),usuario.getUser(), passwordEncoder.encode(usuario.getPassword()),Arrays.asList(new Rol("ROLE_USER")));
		}
		return usuarioDAO.save(newusuario);
	}


	@Override
	public void deleteByIdcliente(Integer idcliente){
		System.out.println(idcliente);
		usuarioDAO.deleteByIdcliente(idcliente);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDAO.findByUser(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getUser(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRol()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> rol){
		return rol.stream().map(role -> new SimpleGrantedAuthority(role.getDescripcion())).collect(Collectors.toList());
	}

	public List<Usuario> listarUsuarios(){
		return usuarioDAO.findAll();
	}
}
