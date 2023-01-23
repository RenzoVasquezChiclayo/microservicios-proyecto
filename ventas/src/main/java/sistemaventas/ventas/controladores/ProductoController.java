package sistemaventas.ventas.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sistemaventas.ventas.entidad.Producto;
import sistemaventas.ventas.entidad.ProductosFeign;

@Controller
@RequestMapping(value="productos",method={RequestMethod.GET,RequestMethod.POST})
public class ProductoController {

    @Autowired
    ProductosFeign productoFeign;
    

    @GetMapping("/listar-producto")
    public String listarProductos(Model model){
        model.addAttribute("productos",productoFeign.listar());
        return "sistema/productos/listarProductos";
        
    }

    @GetMapping("/agregar-producto")
    public String agregarProducto(Model model){
        Producto prod = new Producto();
        model.addAttribute("producto", prod);
        return "sistema/productos/agregarProducto";
    }

    @PostMapping("/validar-producto")
    public String guardarProducto(
        @ModelAttribute("producto") @Validated Producto producto,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:agregar-producto/";
        }
        System.out.print(producto.getDescripcion());
        productoFeign.agregarProducto(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:listar-producto";
    }

    @GetMapping("/editar-producto/{id_producto}")
    public String editarProducto(@PathVariable Integer id_producto,Model model){
        Producto productoFind = productoFeign.findProducto(id_producto);
        System.out.print(productoFind);
        model.addAttribute("producto",productoFind);
        return "sistema/productos/editarProducto";
    }

    @PostMapping("/validar-prod-edicion")
    public String saveProducto(
        @RequestParam(value = "txtid_producto") Integer id_producto,
        @ModelAttribute("producto") @Validated Producto producto,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            if (producto.getId_Producto() != null) {
                redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
                return "redirect:editar-producto/" + id_producto;
            }
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:editar-producto/" + id_producto;
        }
        Producto productoFind = productoFeign.findProducto(id_producto);
        productoFind.setId_Producto(id_producto);
        productoFind.setDescripcion(producto.getDescripcion());
        productoFind.setPrecio(producto.getPrecio());
        productoFind.setStock(producto.getStock());
        productoFeign.updateProducto(productoFind);
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:listar-producto";
    }

    @PostMapping("/eliminar-producto")
    public String eliminarProducto(RedirectAttributes redirectAttrs,
                    @RequestParam(value = "txtid_producto") Integer idproducto){
        
        productoFeign.deleteProducto(idproducto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        return "redirect:listar-producto";
    }
}
