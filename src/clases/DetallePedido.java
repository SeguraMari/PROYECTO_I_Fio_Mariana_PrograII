
package clases;

/**
 * Clase que representa el detalle de un producto en un pedido de la cafetería.
 * Almacena el producto y la cantidad solicitada.
 * 
 * @author Graciela
 */
public class DetallePedido {
    
    private Producto producto;
    private int cantidad;

    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    
    /**
     * Calcula el subtotal del detalle multiplicando el precio
     * del producto por la cantidad solicitada.
     * 
     * @return subtotal del producto.
     */
    
    public int getSubtotal(){
        return producto.getPrecio()* cantidad;
    }
    
    
    
}
