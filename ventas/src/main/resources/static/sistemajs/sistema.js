var cont=0;
total=0;
subtotal=[];

function llenarDatosProducto(){
    datos = [];
    datosProd = document.getElementById('cboProducto').value;
    
    datos = datosProd.split("_");

    stock = datos[1];
    precio = datos[2];

    document.getElementById('txtStock').value = stock;
    document.getElementById('txtPrecioVenta').value = precio;
}

function validarCboCliente(id){

    if (id.value != "") {
        document.getElementById('btnAgregar').disabled = false;
        
    }else{
        document.getElementById('btnAgregar').disabled = true;
    }
}

let listProductos = [];
datos = [];
datos = datosProd.split("_");


function agregar()
  {
    id_producto = datos[0];
    cantidadProd = datos[1];
    nombreProd = datos[3];
    
    datosProd = document.getElementById('cboProducto').value;
    cantidad = document.getElementById('txtCantidad').value;
    descuento = document.getElementById('txtDescuento').value;
    stock = document.getElementById('txtStock').value;
    precio = document.getElementById('txtPrecioVenta').value;

    listaAux = {"id":id_producto};
    

    productosExistentes = listProductos.filter( (producto) => producto.id == id_producto );

    if (id_producto != "" && cantidad != "" && cantidad>0 && descuento != "" && precio != "") {
        
        if (productosExistentes.length > 0) {
            alert ('El producto ya se encuentra agregado');
        }else{
            
            // alert(listProductos);
            if (parseInt(stock) >= parseInt(cantidad)) {
                listProductos.push(listaAux);
                subtotal[cont] = (cantidad * precio - descuento);
    
                if ( subtotal[cont] < 0 ) {
                    alert ('El descuento supera el subtotal a pagar');
                }else{
    
                    total = total + subtotal[cont];
                
                    var fila='<tr class="selected" id="fila'+cont+'"><td><button type="button" class="btn btn-danger" onclick="eliminar('+cont+','+id_producto+','+cantidad+');">X</button></td><td><input type="hidden" name="idproducto[]" value="'+id_producto+'">'+nombreProd+'</td><td><input type="hidden" name="cantidad[]" value="'+cantidad+'">'+cantidad+'</td><td><input type="hidden" name="precio[]" value="'+parseFloat(precio).toFixed(2)+'">'+parseFloat(precio).toFixed(2)+'</td><td><input type="hidden" name="descuento[]" value="'+parseFloat(descuento).toFixed(2)+'">'+parseFloat(descuento).toFixed(2)+'</td><td align="right">S/. '+parseFloat(subtotal[cont]).toFixed(2)+'</td></tr>';
                    cont++;
                    cantidadProd = cantidadProd - cantidad;
                    limpiar();
                    totales();
                    validar();
                    $('#detalles').append(fila);
                    document.getElementById("txtStock").value = cantidadProd;
                }
                
            }else{
                alert ('La cantidad a vender supera el stock');
            }
        }
        
    }else{
        alert("Error al ingresar el detalle de la venta, revise los datos del artículo");
    }
  }

function limpiar(){
    document.getElementById('txtCantidad').value = 0;
    document.getElementById('txtDescuento').value = 0;
  }
function totales(){
    impuesto = 18;

    document.getElementById('total').innerHTML = "S/. "+total.toFixed(2);
    document.getElementById('total_venta').value = total.toFixed(2);

    total_impuesto = total * impuesto/100;
    total_pagar = total + total_impuesto;

    document.getElementById('total_impuesto').innerHTML = "S/. "+total_impuesto.toFixed(2);
    document.getElementById('total_pagar').value = total_pagar.toFixed(2);
}

function validar(){
    if (total>0) {
        document.getElementById('guardar').hidden = false;
    }else{
        document.getElementById('guardar').hidden = true;
    }
    
}

function eliminar(index,id_producto,cantidad){
    total=total-subtotal[index]; 
    totales();  
    $("#fila" + index).remove();
    
    const producto = listProductos.filter(
        (product) => product.id == id_producto,
    );
    const newlist = listProductos.filter(
        (product) => product.id != id_producto,
    );
    listProductos = newlist;
    cantidadProd = document.getElementById('txtStock').value;
    cantidadProd = parseInt(cantidadProd) + parseInt(cantidad);
    document.getElementById('txtStock').value = cantidadProd;
    validar();
}
