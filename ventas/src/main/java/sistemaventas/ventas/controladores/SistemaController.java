package sistemaventas.ventas.controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import sistemaventas.ventas.daos.CategoriaDAO;
import sistemaventas.ventas.daos.ClienteDAO;
import sistemaventas.ventas.daos.DetalleVentaDAO;
import sistemaventas.ventas.daos.ProductoDAO;
import sistemaventas.ventas.daos.UsuarioDAO;
import sistemaventas.ventas.daos.VendedorDAO;
import sistemaventas.ventas.daos.VentaDAO;
// import sistemaventas.ventas.entidad.Categoria;
import sistemaventas.ventas.entidad.Cliente;
import sistemaventas.ventas.entidad.DetalleVenta;
import sistemaventas.ventas.entidad.Producto;
import sistemaventas.ventas.entidad.ProductosFeign;
import sistemaventas.ventas.entidad.Usuario;
import sistemaventas.ventas.entidad.Vendedor;
import sistemaventas.ventas.entidad.Venta;
// import sistemaventas.ventas.servicios.CategoriaServicio;
import sistemaventas.ventas.servicios.ClienteServicio;
import sistemaventas.ventas.servicios.DetalleVentaServicio;
import sistemaventas.ventas.servicios.ProductoServicio;
import sistemaventas.ventas.servicios.UsuarioServicio;
import sistemaventas.ventas.servicios.VendedorServicio;
import sistemaventas.ventas.servicios.VentaServicio;



@Controller
// @RequestMapping("productos")
public class SistemaController {

    @Autowired
    private ClienteDAO clienteDAO;
    @Autowired
    private UsuarioDAO usuarioDAO;

    // @Autowired
    // private ProductoDAO productoDAO;

    @Autowired
    ProductosFeign productosFeign;

    @Autowired
    private VentaDAO ventaDAO;


    @Autowired
    private VendedorDAO vendedorDAO;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;


    @Autowired
    private VentaServicio ventaServicio;

    @Autowired
    private DetalleVentaServicio detalleVentaServicio;

    @Autowired
    private VendedorServicio vendedorServicio;
    // -----------------------------CLIENTES---------------------
    
    @GetMapping("/listar-clientes")
    public String listarClientes(Model model,RedirectAttributes redirectAttrs){
        int count = 0;
        Iterable<Cliente> clienteFind = clienteDAO.findAll();
        if (clienteFind instanceof Collection) {
            count = ((Collection<Cliente>) clienteFind).size();
        }
        model.addAttribute("clientes",clienteFind);
        model.addAttribute("countCli",count);

        return "sistema/clientes/listaClientes";
        
    }

    @GetMapping("/agregar-cliente")
    public String agregarCliente(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("cliente",cliente);
        return "sistema/clientes/agregarCliente";
    }

    @PostMapping("/validar-cliente")
    public String validarCliente(
            @ModelAttribute("cliente") Cliente cliente,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs){

        if (bindingResult.hasErrors()) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/agregar-cliente";
        }
        Cliente clienteFind = clienteDAO.findByRuc(cliente.getRuc());
        if (clienteFind != null) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Ese cliente ya se encuentra registrado")
                .addFlashAttribute("clase", "warning");
            return "redirect:/agregar-cliente";
        }
        cliente.setEstado(1);
        clienteServicio.guardar(cliente);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        
        return "redirect:/listar-clientes";
    }

    @GetMapping("/editar-cliente/{id_cliente}")
    public String editarCliente(@PathVariable Integer id_cliente,Model model){
        Optional<Cliente> clienteFind = clienteDAO.findById(id_cliente);
        
        model.addAttribute("cliente",clienteFind.get());
        return "sistema/clientes/editarCliente";
    }

    @PostMapping("/validar-edicion/{id_cliente}")
    public String saveRegistro(
        @RequestParam(value = "txtid_cliente") Integer id_cliente,
        @RequestParam(value = "txtruc") String ruc,
        @ModelAttribute("cliente") @Validated Cliente cliente,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
        
        if (bindingResult.hasErrors()) {
            if (cliente.getId_Cliente() != null) {
                redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
                return "redirect:/editar-cliente/" + id_cliente;
            }
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/editar-cliente/" + id_cliente;
        }
        Cliente clienteFind = clienteServicio.obtenerClienteById(id_cliente);
        clienteFind.setId_Cliente(id_cliente);
        clienteFind.setRuc(cliente.getRuc());
        clienteFind.setNombres(cliente.getNombres());
        clienteFind.setDireccion(cliente.getDireccion());


        clienteServicio.actualizarCliente(clienteFind);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/listar-clientes";

    }

    @PostMapping("/eliminar-cliente")
    public String eliminarCliente(RedirectAttributes redirectAttrs,
                    @RequestParam(value = "txtid_cliente") Integer idcliente){
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        
        Cliente clienteFind = clienteServicio.obtenerClienteById(idcliente);
        // clienteDAO.deleteById(idcliente);
        clienteFind.setEstado(0);
        clienteServicio.actualizarCliente(clienteFind);
        usuarioDAO.deleteByIdcliente(1);
        return "redirect:/listar-clientes";
    }

    // -----------------------------USUARIOS---------------------

    @GetMapping("/listar-usuarios")
    public String listarUsuario(Model model){
        model.addAttribute("usuarios", usuarioServicio.listarUsuarios());
        return "sistema/usuarios/listaUsuario";
    }

    // -----------------------------VENDEDORES---------------------

    @GetMapping("/listar-vendedores")
    public String listarVendedores(Model model){
        int count = 0;
        Iterable<Vendedor> allVendedores = vendedorDAO.findAll();
        if (allVendedores instanceof Collection) {
            count = ((Collection<Vendedor>) allVendedores).size();
        }
        model.addAttribute("vendedores",allVendedores);
        model.addAttribute("countVen",count);
        return "sistema/vendedores/listarVendedores";
    }

    @GetMapping("/agregar-vendedor")
    public String agregarVendedor(Model model){
        Vendedor vendedor = new Vendedor();
        model.addAttribute("vendedor", vendedor);
        return "sistema/vendedores/agregarVendedor";
    }

    @PostMapping("/validar-vendedor")
    public String validarVendedor(
            @RequestParam("correo") String correo,
            @ModelAttribute("vendedor") @Validated Vendedor vendedor,
            @ModelAttribute("usuario") @Validated Usuario usuario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs){

        if (bindingResult.hasErrors()) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/agregar-vendedor";
        }
        // Vendedor vendedorFind = vendedorDAO.findByDni(vendedor.getDni());
        Vendedor vendedorFind = vendedorDAO.findByCorreo(vendedor.getCorreo());
        if (vendedorFind != null) {
            redirectAttrs
                .addFlashAttribute("mensaje", "El vendedor ya se encuentra registrado")
                .addFlashAttribute("clase", "warning");
            return "redirect:/agregar-vendedor";
        }
        vendedor.setEstado(1);
        vendedorServicio.guardar(vendedor);
        
        Vendedor lastVendedorFind = vendedorDAO.findByCorreo(vendedor.getCorreo());
        usuario.setIdvendedor(lastVendedorFind.getId_Vendedor());
        usuario.setUser(correo);
        usuario.setPassword("123456");

        usuarioServicio.guardar(usuario);
        redirectAttrs
                .addFlashAttribute("mensaje", "El vendedor se registro correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/listar-vendedores";
    }

    @GetMapping("/editar-vendedor/{id_vendedor}")
    public String editarVendedor(Model model, @PathVariable Integer id_vendedor){
        
        Optional<Vendedor> vendedorFind = vendedorDAO.findById(id_vendedor);
        model.addAttribute("vendedor", vendedorFind.get());
        return "sistema/vendedores/editarVendedor";
    }

    @PostMapping("/validar-vend-edicion/{id_vendedor}")
    public String validarVendedor(
            @RequestParam("txtid_vendedor") Integer id_vendedor,
            @ModelAttribute("vendedro") @Validated Vendedor vendedor,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs){

        if (bindingResult.hasErrors()) {
            if (vendedor.getId_Vendedor() != null) {
                redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
                return "redirect:/editar-vendedor/" + id_vendedor;
            }
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/editar-vendedor/" + id_vendedor;
        }

        Optional<Vendedor> vendedorFind = vendedorDAO.findById(id_vendedor);
        Vendedor vendedorAux = vendedorFind.get();

        vendedorAux.setId_Vendedor(id_vendedor);
        // vendedorAux.setDni(vendedor.getDni());
        vendedorAux.setNombres(vendedor.getNombres());
        vendedorAux.setCorreo(vendedor.getCorreo());
        // vendedorAux.setCelular(vendedor.getCelular());

        vendedorServicio.actualizarVendedor(vendedorAux);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/listar-vendedores";
    }
    
    @PostMapping("/eliminar-vendedor")
    public String eliminarVendedor(RedirectAttributes redirectAttrs,
                    @RequestParam(value = "txtid_vendedor") Integer idvendedor){
        
        // Optional<Vendedor> vendedorFind = vendedorDAO.findById(idvendedor);
        // Vendedor vendedorAux = vendedorFind.get();
        // vendedorAux.setId_Vendedor(idvendedor);
        // vendedorAux.setEstado(0);
        // vendedorServicio.actualizarVendedor(vendedorAux);
        vendedorDAO.deleteById(idvendedor);
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        return "redirect:/listar-vendedores";
    }



    // --------------------------------VENTAS---------------------------

    @GetMapping("/listar-ventas")
    public String listarVentas(Model model){
        int count = 0;
        Iterable<Venta> allVentas = ventaDAO.findAll();
        if (allVentas instanceof Collection) {
            count = ((Collection<Venta>) allVentas).size();
        }
        model.addAttribute("ventas", allVentas);
        model.addAttribute("cantVentas", count);
        return "/sistema/ventas/listarVentas";
    }

    @GetMapping("/agregar-venta")
    public String agregarVenta(Model model){
        List<Cliente> clientes = clienteDAO.findAll();
        List<Producto> productos = productosFeign.listar();
        model.addAttribute("clientes", clientes);
        model.addAttribute("productos", productos);
        return "/sistema/ventas/agregarVenta";
    }

    @PostMapping("/validar-venta")
    public String guardarVenta(
                @RequestParam(value = "cboCliente") Integer idcliente,
                @RequestParam(value = "idproducto[]") Integer[] idproducto,
                @RequestParam(value = "cantidad[]") Integer[] cantidad,
                @RequestParam(value = "precio[]") Float[] precio,
                @RequestParam(value = "descuento[]") Float[] descuento,
                @RequestParam(value = "total_pagar") Float total_pagar,
                @ModelAttribute("venta") @Validated Venta venta,
                @ModelAttribute("detalle_venta") @Validated DetalleVenta detalle_venta,
                RedirectAttributes redirectAttrs){
        Authentication auth = SecurityContextHolder
                    .getContext()
                    .getAuthentication();
        Usuario usuarioFind = usuarioDAO.findByUser(auth.getName());
        Optional<Cliente> clienteFind = clienteDAO.findById(idcliente);
        Cliente cliente = clienteFind.get();
        Date date = new Date();
        venta.setCliente(cliente);
        venta.setEstado(1);
        venta.setUsuario(usuarioFind);
        venta.setFecha_venta(date);
        venta.setTotal(total_pagar);
        ventaServicio.guardar(venta);
        for (int i = 0; i < idproducto.length; i++) {
            Producto productoFind = productosFeign.findProducto(idproducto[i]);
            Venta ventaFind = ventaServicio.obtenerUltimoRegistro();
            detalle_venta.setId_Producto(productoFind.getId_Producto());
            detalle_venta.setVenta(ventaFind);
            detalle_venta.setCantidad(cantidad[i]);
            detalle_venta.setDescuento(descuento[i]);
            detalleVentaServicio.guardar(detalle_venta);
            Producto updateProducto = productosFeign.findProducto(idproducto[i]);
            updateProducto.setStock(updateProducto.getStock()-cantidad[i]);
            productosFeign.updateProducto(updateProducto);
        }
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/listar-ventas";
    }

    @GetMapping("/detalle-venta/{id_venta}")
    public String detalleVenta(Model model,
            @RequestParam(value = "txtid_venta") Integer id_venta){
        
        
        Optional<Venta> ventaFind = ventaDAO.findById(id_venta);
        Venta ventaAux = ventaFind.get();
        List<DetalleVenta> listDetalle = detalleVentaServicio.findByVenta(ventaFind.get());
        List<Producto> listProducto = new ArrayList<Producto>();
        listProducto.clear();
        for (DetalleVenta detalleVenta : listDetalle) {
            Producto findprod = productosFeign.findProducto(detalleVenta.getId_Producto());
            listProducto.add(findprod);
        }

        model.addAttribute("venta", ventaAux);
        model.addAttribute("detalle", listDetalle);
        model.addAttribute("productos", listProducto);
        return "sistema/ventas/detalleVenta";
    }
}
