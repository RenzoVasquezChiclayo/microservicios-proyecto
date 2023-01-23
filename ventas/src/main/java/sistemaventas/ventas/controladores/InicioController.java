package sistemaventas.ventas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sistemaventas.ventas.daos.ClienteDAO;
import sistemaventas.ventas.daos.UsuarioDAO;
import sistemaventas.ventas.entidad.Cliente;
import sistemaventas.ventas.entidad.Usuario;
import sistemaventas.ventas.servicios.ClienteServicio;
import sistemaventas.ventas.servicios.UsuarioServicio;





@Controller
public class InicioController {
    
    /**
     * @return
     */


    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    // @GetMapping("/")
    // public String verInicio(){
    //     return "inicio/inicio";
    // }

    @GetMapping("/login")
    public String verLogin(Model model){
        // Usuario usu = new Usuario();
        // model.addAttribute("usuario", usu);
        return "login/login";
    }

    // @GetMapping("/registrar")
    // public String registrarCliente(Model model){
    //     Cliente cli = new Cliente();
    //     Usuario usu = new Usuario();
    //     model.addAttribute("cliente", cli);
    //     model.addAttribute("usuario", usu);
    //     return "login/registro";
    // }

    // @PostMapping("/validar-registro")
    // public String saveRegistro(
    //     @RequestParam(value = "user") String user,
    //     @RequestParam(value = "password") String password,
    //     @ModelAttribute("usuario") @Validated Usuario usuario,
    //     @ModelAttribute("cliente") @Validated Cliente cliente,
    //     BindingResult bindingResult,
    //     RedirectAttributes redirectAttrs){                       
    //     usuario.setUser(user);
    //     Usuario usuarioFind = usuarioDAO.findByUser(usuario.getUser());
    //     Cliente clienteFind = clienteDAO.findByDni(cliente.getDni());
    //     if (bindingResult.hasErrors()) {
    //         return "redirect:/registrar";
    //     }
    //     if (usuarioFind!=null || clienteFind!=null) {
    //         redirectAttrs
    //             .addFlashAttribute("mensaje", "Ese usuario ya se encuentra registrado")
    //             .addFlashAttribute("clase", "warning");
    //         return "redirect:/registrar";
    //     }
    //     usuario.setPassword(password);
    //     cliente.setEstado(1);
    //     clienteServicio.guardar(cliente);
    //     Cliente lastclienteFind = clienteDAO.findByDni(cliente.getDni());
    //     if (lastclienteFind != null) {
    //         usuario.setIdcliente(lastclienteFind.getId_Cliente());
    //     }
    //     usuarioServicio.guardar(usuario);
    //     redirectAttrs
    //             .addFlashAttribute("mensaje", "Agregado correctamente")
    //             .addFlashAttribute("clase", "success");
    //     return "redirect:/registrar";
    // }
    
    @PostMapping("/validar-login")
    public String validarLogin(@RequestParam(value = "password") String password,@ModelAttribute("usuario") Usuario usuario,Model model,RedirectAttributes redirectAttrs){
        return "/login/login";
        // $2a$10$7mvpIHLl.TYGxPDYgp39EO7fAGmHNgcnlyfcdaa2mI2.pyrkKY0YG
    }
    @GetMapping("/inicio-sistema")
    public String verSistema(){
        return "sistema/inicio";
    }
}
