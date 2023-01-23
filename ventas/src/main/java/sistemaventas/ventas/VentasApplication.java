package sistemaventas.ventas;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


import sistemaventas.ventas.entidad.Rol;
import sistemaventas.ventas.entidad.Usuario;
import sistemaventas.ventas.servicios.UsuarioServicio;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class VentasApplication {

	@Autowired
    private UsuarioServicio usuarioServicio;

	public static void main(String[] args) {
		SpringApplication.run(VentasApplication.class, args);

		
	}

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			String user = "admin@gmail.com";
			Usuario userExist = usuarioServicio.findByUser(user);
			System.out.print(userExist);
			if(userExist == null) {
				Usuario usuario = new Usuario();
				usuario.setIdcliente(null);
				usuario.setIdvendedor(null);
				usuario.setUser(user);
				usuario.setPassword("123456");
				usuario.setRol(Arrays.asList(new Rol("ROLE_ADMIN")));
				usuarioServicio.guardar(usuario);
			}else{
				System.out.print("error GAAAAAAAAA");
			}
		};
	}

}
